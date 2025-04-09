package com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.CartEntity;
import com.orielle.orielle_server.domain.model.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICartEntityMapper {
    CartEntity toEntity(Cart cart);
    Cart toDomainModel(CartEntity cartEntity);
}
