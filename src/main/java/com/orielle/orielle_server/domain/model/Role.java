package com.orielle.orielle_server.domain.model;

import com.orielle.orielle_server.domain.enums.RoleEnum;
import com.orielle.orielle_server.domain.util.Constants;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class Role {
    private final Long roleId;
    private final RoleEnum name;
    private final String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Role(String description, RoleEnum name, Long roleId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.roleId = roleId;
        this.name = requireNonNull(name, Constants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, Constants.FIELD_DESCRIPTION_NULL_MESSAGE);
        this.createdAt = requireNonNull(createdAt, Constants.FIELD_CREATED_AT_NULL_MESSAGE);
        this.updatedAt = requireNonNull(updatedAt, Constants.FIELD_UPDATED_AT_NULL_MESSAGE);
    }

    public Long getRoleId() { return roleId; }

    public RoleEnum getName() { return name; }

    public String getDescription() { return description; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
