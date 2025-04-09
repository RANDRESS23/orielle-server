package com.orielle.orielle_server.domain.spi;

import com.orielle.orielle_server.domain.model.CartProduct;
import com.orielle.orielle_server.domain.model.CustomPage;

import java.util.List;

public interface ICartProductPersistencePort {
    CartProduct saveCartProduct(CartProduct cartProduct);
    void removeCartProduct(CartProduct cartProduct);
    void removeAllCartProducts(Long cartId);
    List<CartProduct> getAllProducts(Long cartId);
    CustomPage<CartProduct> getAllCartProducts(int page, int size, boolean ascending, Long cartId);
}
