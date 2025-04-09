package com.orielle.orielle_server.domain.model;

import com.orielle.orielle_server.domain.enums.StatusEnum;
import com.orielle.orielle_server.domain.util.Constants;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Order {
    private Long orderId;
    private Long clientId;
    private Long totalQuantity;
    private BigDecimal totalPrice;
    private List<OrderItem> products;
    private StatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order() { }

    public Order(Order.OrderBuilder builder) {
        this.orderId = builder.orderId;
        this.clientId = builder.clientId;
        this.totalQuantity = builder.totalQuantity;
        this.totalPrice = builder.totalPrice;
        this.products = builder.products;
        this.status = builder.status;
        this.createdAt = requireNonNull(builder.createdAt, Constants.FIELD_CREATED_AT_NULL_MESSAGE);
        this.updatedAt = requireNonNull(builder.updatedAt, Constants.FIELD_UPDATED_AT_NULL_MESSAGE);
    }

    public static class OrderBuilder {
        private Long orderId;
        private Long clientId;
        private Long totalQuantity;
        private BigDecimal totalPrice;
        private List<OrderItem> products;
        private StatusEnum status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Order.OrderBuilder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Order.OrderBuilder clientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public Order.OrderBuilder totalQuantity(Long totalQuantity) {
            this.totalQuantity = totalQuantity;
            return this;
        }

        public Order.OrderBuilder totalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Order.OrderBuilder products(List<OrderItem> products) {
            this.products = products;
            return this;
        }

        public Order.OrderBuilder status(StatusEnum status) {
            this.status = status;
            return this;
        }

        public Order.OrderBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Order.OrderBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItem> products) {
        this.products = products;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public StatusEnum getStatus() { return status; }

    public void setStatus(StatusEnum status) { this.status = status; }
}
