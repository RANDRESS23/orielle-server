package com.orielle.orielle_server.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class OrderItemDto {
    private Long productId;
    private String name;
    private Long quantity;
    private BigDecimal totalPrice;
}
