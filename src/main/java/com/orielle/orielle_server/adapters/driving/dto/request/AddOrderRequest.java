package com.orielle.orielle_server.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class AddOrderRequest {
    private Long clientId;
    private Long totalQuantity;
    private BigDecimal totalPrice;
    private List<OrderItemDto> products;
}
