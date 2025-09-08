package com.engine.dto;

import java.time.Instant;
import java.util.List;

public class DocumentMetaDto {
    private Long id;
    private String title;
    private String content;
    private Instant createdAt;
    private List<TagDto> tags;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public List<TagDto> getTags() { return tags; }
    public void setTags(List<TagDto> tags) { this.tags = tags; }
}
