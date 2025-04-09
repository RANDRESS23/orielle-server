package com.orielle.orielle_server.domain.api;

import com.orielle.orielle_server.domain.model.Cart;

import java.util.List;

public interface ICartServicePort {
    Cart saveCart(Cart cart);
    Cart updateCart(Cart cart);
    Cart getCartByClientId();
    List<Long> getCartsIds();
    void buyCartProducts();
}
