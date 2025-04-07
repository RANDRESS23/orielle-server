package com.orielle.orielle_server.domain.util;

import com.orielle.orielle_server.domain.exception.EmptyFieldException;
import com.orielle.orielle_server.domain.exception.MaxLengthException;
import com.orielle.orielle_server.domain.exception.NegativeNotAllowedException;
import com.orielle.orielle_server.domain.model.Product;

import java.math.BigDecimal;

public class ProductValidation {
    public void validateProduct(Product product) {
        validateNameProduct(product.getName());
        validateDescriptionProduct(product.getDescription());
        validatePriceProduct(product.getPrice());
        validateQuantityProduct(product.getQuantity());
    }

    private void validateNameProduct(String name) {
        if (name.trim().isEmpty()) {
            throw new EmptyFieldException(Constants.MINIMUM_PRODUCT_NAME_CHARACTERS_MESSAGE);
        }

        if (name.trim().length() > Constants.MAXIMUM_PRODUCT_NAME_CHARACTERS) {
            throw new MaxLengthException(Constants.MAXIMUM_PRODUCT_NAME_CHARACTERS_MESSAGE);
        }
    }

    private void validateDescriptionProduct(String description) {
        if (description.trim().isEmpty()) {
            throw new EmptyFieldException(Constants.MINIMUM_PRODUCT_DESCRIPTION_CHARACTERS_MESSAGE);
        }

        if (description.trim().length() > Constants.MAXIMUM_PRODUCT_DESCRIPTION_CHARACTERS) {
            throw new MaxLengthException(Constants.MAXIMUM_PRODUCT_DESCRIPTION_CHARACTERS_MESSAGE);
        }
    }

    private void validatePriceProduct(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < Constants.ZERO_CONSTANT) {
            throw new NegativeNotAllowedException(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE);
        }
    }

    private void validateQuantityProduct(Long quantity) {
        if (quantity < Constants.ZERO_CONSTANT) {
            throw new NegativeNotAllowedException(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE);
        }
    }
}
