package com.orielle.orielle_server.adapters.driving.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CartDto {
    private final Long cartId;
    private final Long clientId;
    private final Long totalQuantity;
    private final BigDecimal totalPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
