package com.orielle.orielle_server.domain.model;

import com.orielle.orielle_server.domain.util.Constants;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class Product {
    private final Long productId;
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
    private String image;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(Product.ProductBuilder builder) {
        this.productId = builder.productId;
        this.name = requireNonNull(builder.name, Constants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(builder.description, Constants.FIELD_DESCRIPTION_NULL_MESSAGE);
        this.price = requireNonNull(builder.price, Constants.FIELD_PRICE_NULL_MESSAGE);
        this.image = requireNonNull(builder.image, Constants.FIELD_IMAGE_NULL_MESSAGE);
        this.quantity = requireNonNull(builder.quantity, Constants.FIELD_QUANTITY_NULL_MESSAGE);
        this.category = requireNonNull(builder.category, Constants.FIELD_CATEGORY_NULL_MESSAGE);
        this.createdAt = requireNonNull(builder.createdAt, Constants.FIELD_CREATED_AT_NULL_MESSAGE);
        this.updatedAt = requireNonNull(builder.updatedAt, Constants.FIELD_UPDATED_AT_NULL_MESSAGE);
    }

    public static class ProductBuilder {
        private Long productId;
        private String name;
        private String description;
        private Long quantity;
        private BigDecimal price;
        private String image;
        private Category category;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Product.ProductBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Product.ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Product.ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Product.ProductBuilder quantity(Long quantity) {
            this.quantity = quantity;
            return this;
        }

        public Product.ProductBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Product.ProductBuilder image(String image) {
            this.image = image;
            return this;
        }

        public Product.ProductBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public Product.ProductBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Product.ProductBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImage() { return image; }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setQuantity(Long quantity) { this.quantity = quantity; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public void setImage(String image) { this.image = image; }

    public void setCategory(Category category) { this.category = category; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
