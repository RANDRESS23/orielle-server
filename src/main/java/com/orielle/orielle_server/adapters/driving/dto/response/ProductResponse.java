package com.orielle.orielle_server.adapters.driving.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ProductResponse {
    private final Long productId;
    private final String name;
    private final String description;
    private final Long quantity;
    private final BigDecimal price;
    private final String image;
    private CategoryResponse category;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
