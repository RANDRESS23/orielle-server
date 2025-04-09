package com.orielle.orielle_server.domain.api;

import com.orielle.orielle_server.domain.model.Order;

public interface IOrderServicePort {
    Long saveOrder(Order order);
    void deleteOrder(Long orderId);
}
