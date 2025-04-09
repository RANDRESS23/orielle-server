package com.orielle.orielle_server.adapters.driven.jpa.mysql.entity;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.util.DrivenConstants;
import com.orielle.orielle_server.domain.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = DrivenConstants.ORDER_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DrivenConstants.COLUMN_ORDER_ID)
    private Long orderId;

    @Column(name = DrivenConstants.COLUMN_ORDER_CLIENT_ID, nullable = false)
    private Long clientId;

    @Column(name = DrivenConstants.COLUMN_ORDER_TOTAL_QUANTITY, nullable = false)
    private Long totalQuantity;

    @Column(name = DrivenConstants.COLUMN_ORDER_TOTAL_PRICE, nullable = false)
    private BigDecimal totalPrice;

    @Column(name = DrivenConstants.STATE_TABLE_NAME)
    private StatusEnum status;

    @Column(name = DrivenConstants.COLUMN_CART_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = DrivenConstants.COLUMN_CART_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;
}
