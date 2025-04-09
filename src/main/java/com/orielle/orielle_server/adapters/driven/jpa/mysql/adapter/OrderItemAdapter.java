package com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.IOrderItemEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.IOrderItemRepository;
import com.orielle.orielle_server.domain.model.OrderItem;
import com.orielle.orielle_server.domain.spi.IOrderItemPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class OrderItemAdapter implements IOrderItemPersistencePort {
    private final IOrderItemRepository orderItemRepository;
    private final IOrderItemEntityMapper orderItemEntityMapper;

    @Transactional
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItemEntityMapper.toEntity(orderItem));
    }

    @Transactional
    @Override
    public void removeAllCartOrderItems(Long orderId) {
        orderItemRepository.removeAllCartOrderItems(orderId);
    }
}
