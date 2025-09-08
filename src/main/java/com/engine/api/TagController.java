package com.engine.api;

import com.engine.metadata.Tag;
import com.engine.metadata.TagRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagRepository tagRepo;

    public TagController(TagRepository tagRepo) {
        this.tagRepo = tagRepo;
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        return ResponseEntity.ok(tagRepo.findAll());
    }
}
