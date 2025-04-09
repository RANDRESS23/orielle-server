package com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.CartEntity;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.ICartEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.ICartRepository;
import com.orielle.orielle_server.domain.model.Cart;
import com.orielle.orielle_server.domain.spi.ICartPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CartAdapter implements ICartPersistencePort {
    private final ICartRepository cartRepository;
    private final ICartEntityMapper cartEntityMapper;

    @Override
    public Cart saveCart(Cart cart) {
        CartEntity cartEntity = cartRepository.save(cartEntityMapper.toEntity(cart));
        return cartEntityMapper.toDomainModel(cartEntity);
    }

    @Override
    public List<Long> getCartsIds() {
        return cartRepository.findAll().stream().map(CartEntity::getCartId).toList();
    }

    @Override
    public Optional<Cart> getCartByClientId(Long clientId) {
        return cartRepository.findByClientId(clientId)
                .map(cartEntityMapper::toDomainModel);
    }
}
