package com.engine.api;

import com.engine.dto.DocumentMetaDto;
import com.engine.mapper.DocumentMetaMapper;
import com.engine.metadata.DocumentMeta;
import com.engine.metadata.MetadataService;
import com.engine.search.LuceneService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/index")
public class IndexController {

    private record IndexReq(@NotBlank String title,
                            @NotBlank String content,
                            List<String> tags) {}

    private final LuceneService lucene;
    private final MetadataService metadata;
    private final DocumentMetaMapper mapper;

    public IndexController(LuceneService lucene, MetadataService metadata, DocumentMetaMapper mapper) {
        this.lucene = lucene;
        this.metadata = metadata;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<DocumentMetaDto> index(@RequestBody IndexReq req) throws Exception {
        DocumentMeta m = metadata.saveWithTags(req.title(), req.content(), req.tags());
        lucene.indexDoc(String.valueOf(m.getId()), req.title(), req.content(), req.tags());
        return ResponseEntity.ok(mapper.toDto(m));
    }
}
