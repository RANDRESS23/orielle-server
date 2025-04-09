package com.orielle.orielle_server.domain.spi;

import com.orielle.orielle_server.domain.model.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartPersistencePort {
    Cart saveCart(Cart cart);
    List<Long> getCartsIds();
    Optional<Cart> getCartByClientId(Long clientId);
}
