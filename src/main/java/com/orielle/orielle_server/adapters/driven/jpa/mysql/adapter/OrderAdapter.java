package com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.IOrderEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.IOrderRepository;
import com.orielle.orielle_server.domain.model.Order;
import com.orielle.orielle_server.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class OrderAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Transactional
    @Override
    public Order saveOrder(Order order) {
        OrderEntity orderEntity = orderRepository.save(orderEntityMapper.toEntity(order));
        return orderEntityMapper.toDomainModel(orderEntity);
    }

    @Transactional
    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.removeOrder(orderId);
    }
}
