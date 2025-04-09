package com.orielle.orielle_server.adapters.driven.jpa.mysql.entity;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.util.DrivenConstants;
import com.orielle.orielle_server.domain.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = DrivenConstants.ROLES_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DrivenConstants.COLUMN_ROLE_ID)
    private Long roleId;

    @Column(name = DrivenConstants.COLUMN_NAME, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @Column(name = DrivenConstants.COLUMN_ROLE_DESCRIPTION, nullable = false, length = DrivenConstants.MAXIMUM_ROLE_DESCRIPTION_CHARACTERS)
    private String description;

    @Column(name = DrivenConstants.COLUMN_CART_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = DrivenConstants.COLUMN_CART_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;
}
