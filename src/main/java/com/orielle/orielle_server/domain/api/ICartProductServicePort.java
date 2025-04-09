package com.orielle.orielle_server.domain.api;

import com.orielle.orielle_server.domain.model.Cart;
import com.orielle.orielle_server.domain.model.CartProduct;
import com.orielle.orielle_server.domain.model.CustomPage;

public interface ICartProductServicePort {
    Cart saveCartProduct(CartProduct cartProduct);
    Cart removeCartProduct(CartProduct cartProduct);
    CustomPage<CartProduct> getAllCartProducts(Integer page, Integer size, Boolean ascending, String category);
    Cart getAllCartProductsInTheCart();
}
