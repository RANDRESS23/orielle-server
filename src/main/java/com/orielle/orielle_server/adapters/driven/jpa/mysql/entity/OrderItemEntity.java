package com.orielle.orielle_server.adapters.driven.jpa.mysql.entity;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.util.DrivenConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = DrivenConstants.ORDER_ITEM_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DrivenConstants.COLUMN_ORDER_ITEM_ID)
    private Long orderItemId;

    @Column(name = DrivenConstants.COLUMN_ORDER_ID, nullable = false)
    private Long orderId;

    @Column(name = DrivenConstants.COLUMN_ORDER_ITEM_PRODUCT_ID, nullable = false)
    private Long productId;

    @Column(name = DrivenConstants.COLUMN_ORDER_ITEM_QUANTITY, nullable = false)
    private Long quantity;

    @Column(name = DrivenConstants.COLUMN_ORDER_ITEM_TOTAL_PRICE, nullable = false)
    private BigDecimal totalPrice;

    @Column(name = DrivenConstants.COLUMN_CART_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = DrivenConstants.COLUMN_CART_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;
}
