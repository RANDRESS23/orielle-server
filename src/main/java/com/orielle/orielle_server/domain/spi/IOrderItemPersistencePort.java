package com.orielle.orielle_server.domain.spi;

import com.orielle.orielle_server.domain.model.OrderItem;

public interface IOrderItemPersistencePort {
    void saveOrderItem(OrderItem orderItem);
    void removeAllCartOrderItems(Long orderId);
}
