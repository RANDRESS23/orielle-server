package com.orielle.orielle_server.domain.model;

import com.orielle.orielle_server.domain.util.Constants;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long productId;
    private Long quantity;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderItem() { }

    public OrderItem(Long orderItemId, Long orderId, Long productId, Long quantity, BigDecimal totalPrice, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = requireNonNull(productId, Constants.FIELD_PRODUCT_ID_NULL_MESSAGE);
        this.quantity = requireNonNull(quantity, Constants.FIELD_QUANTITY_NULL_MESSAGE);
        this.totalPrice = totalPrice;
        this.createdAt = requireNonNull(createdAt, Constants.FIELD_CREATED_AT_NULL_MESSAGE);
        this.updatedAt = requireNonNull(updatedAt, Constants.FIELD_UPDATED_AT_NULL_MESSAGE);
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setProductId(Long productId) { this.productId = productId; }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
