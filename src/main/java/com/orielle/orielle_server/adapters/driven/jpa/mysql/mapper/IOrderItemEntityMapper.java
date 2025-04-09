package com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.OrderItemEntity;
import com.orielle.orielle_server.domain.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrderItemEntityMapper {
    OrderItemEntity toEntity(OrderItem orderItem);
    OrderItem toDomainModel(OrderItemEntity orderItemEntity);
}
