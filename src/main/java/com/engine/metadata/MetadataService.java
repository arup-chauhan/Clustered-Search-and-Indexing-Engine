package com.engine.metadata;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MetadataService {
    private final DocumentMetaRepository repo;
    private final TagRepository tagRepo;

    public MetadataService(DocumentMetaRepository repo, TagRepository tagRepo) {
        this.repo = repo;
        this.tagRepo = tagRepo;
    }

    @CachePut(value = "metadata", key = "#result.id")
    public DocumentMeta save(DocumentMeta m) {
        return repo.save(m);
    }

    @Cacheable(value = "metadata", key = "#id")
    public Optional<DocumentMeta> get(Long id) {
        return repo.findById(id);
    }

    public List<DocumentMeta> getAll() {
        return repo.findAll();
    }

    @CacheEvict(value = "metadata", key = "#id")
    public boolean delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @CachePut(value = "metadata", key = "#result.id")
    public DocumentMeta saveWithTags(String title, String content, List<String> tags) {
        DocumentMeta m = new DocumentMeta();
        m.setTitle(title);
        m.setContent(content);

        Set<Tag> tagEntities = new HashSet<>();
        for (String tagName : tags) {
            Tag tag = tagRepo.findByName(tagName).orElseGet(() -> {
                Tag newTag = new Tag();
                newTag.setName(tagName);
                return tagRepo.save(newTag);
            });
            tagEntities.add(tag);
        }
        m.setTags(tagEntities);

        return repo.save(m);
    }
}
