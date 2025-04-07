package com.orielle.orielle_server.adapters.driven.jpa.mysql.entity;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.util.DrivenConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = DrivenConstants.CATEGORY_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotBlank(message = DrivenConstants.FIELD_NAME_NOT_BLANK_MESSAGE)
    private String name;

    @NotBlank(message = DrivenConstants.FIELD_DESCRIPTION_NOT_BLANK_MESSAGE)
    private String description;

    @Column(name = DrivenConstants.COLUMN_CART_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = DrivenConstants.COLUMN_CART_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;
}
