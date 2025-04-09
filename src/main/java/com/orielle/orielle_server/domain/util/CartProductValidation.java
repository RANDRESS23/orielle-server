package com.orielle.orielle_server.domain.util;

import com.orielle.orielle_server.domain.exception.NegativeNotAllowedException;
import com.orielle.orielle_server.domain.model.CartProduct;

public class CartProductValidation {
    public void validateCartProduct(CartProduct product) {
        validateQuantityProduct(product.getQuantity());
    }

    private void validateQuantityProduct(Long quantity) {
        if (quantity < Constants.ZERO_CONSTANT) {
            throw new NegativeNotAllowedException(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE);
        }
    }
}
