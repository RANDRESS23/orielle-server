package com.orielle.orielle_server.adapters.driven.jpa.mysql.entity;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.util.DrivenConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = DrivenConstants.CART_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DrivenConstants.COLUMN_CART_ID)
    private Long cartId;

    @Column(name = DrivenConstants.COLUMN_CART_CLIENT_ID, nullable = false)
    private Long clientId;

    @Column(name = DrivenConstants.COLUMN_CART_TOTAL_QUANTITY, nullable = false)
    private Long totalQuantity;

    @Column(name = DrivenConstants.COLUMN_CART_TOTAL_PRICE, nullable = false)
    private BigDecimal totalPrice;

    @Column(name = DrivenConstants.COLUMN_CART_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = DrivenConstants.COLUMN_CART_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;
}
