package com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.CartEntity;
import com.orielle.orielle_server.domain.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICartEntityMapper {
    CartEntity toEntity(Cart cart);
    Cart toDomainModel(CartEntity cartEntity);
}
