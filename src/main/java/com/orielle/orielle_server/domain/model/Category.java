package com.orielle.orielle_server.domain.model;

import com.orielle.orielle_server.domain.util.Constants;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class Category {
    private Long categoryId;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Category() { }

    public Category(Long categoryId, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.categoryId = categoryId;
        this.name = requireNonNull(name, Constants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, Constants.FIELD_DESCRIPTION_NULL_MESSAGE);
        this.createdAt = requireNonNull(createdAt, Constants.FIELD_CREATED_AT_NULL_MESSAGE);
        this.updatedAt = requireNonNull(updatedAt, Constants.FIELD_UPDATED_AT_NULL_MESSAGE);
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
