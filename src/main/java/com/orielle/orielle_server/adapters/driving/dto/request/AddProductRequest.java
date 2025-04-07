package com.orielle.orielle_server.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class AddProductRequest {
    private final String name;
    private final String description;
    private final Long quantity;
    private final BigDecimal price;
    private final String image;
    private Long categoryId;
}
