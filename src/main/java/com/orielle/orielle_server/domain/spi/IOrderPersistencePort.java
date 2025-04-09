package com.orielle.orielle_server.domain.spi;

import com.orielle.orielle_server.domain.model.Order;

public interface IOrderPersistencePort {
    Order saveOrder(Order order);
    void deleteOrder(Long orderId);
}
