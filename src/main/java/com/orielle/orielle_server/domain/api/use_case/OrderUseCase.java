package com.orielle.orielle_server.domain.api.use_case;

import com.orielle.orielle_server.domain.api.IOrderServicePort;
import com.orielle.orielle_server.domain.model.Order;
import com.orielle.orielle_server.domain.model.OrderItem;
import com.orielle.orielle_server.domain.spi.IOrderItemPersistencePort;
import com.orielle.orielle_server.domain.spi.IOrderPersistencePort;

import java.util.List;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IOrderItemPersistencePort orderItemPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IOrderItemPersistencePort orderItemPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.orderItemPersistencePort = orderItemPersistencePort;
    }

    @Override
    public Long saveOrder(Order order) {
        Order orderSaved = orderPersistencePort.saveOrder(order);
        List<OrderItem> orderItems = order.getProducts();

        orderItems.forEach(orderItem -> {
            orderItem.setOrderId(orderSaved.getOrderId());
            orderItemPersistencePort.saveOrderItem(orderItem);
        });

        return orderSaved.getOrderId();
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderPersistencePort.deleteOrder(orderId);
        orderItemPersistencePort.removeAllCartOrderItems(orderId);
    }
}
