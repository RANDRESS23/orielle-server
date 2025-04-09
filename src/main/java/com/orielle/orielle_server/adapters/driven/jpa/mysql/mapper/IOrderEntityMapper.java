package com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.orielle.orielle_server.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {
    OrderEntity toEntity(Order order);
    Order toDomainModel(OrderEntity orderEntity);
}
