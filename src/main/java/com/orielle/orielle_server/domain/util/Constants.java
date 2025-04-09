package com.orielle.orielle_server.domain.util;

import java.math.BigDecimal;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String DNI_REGEX = "^\\d{7,10}$";
    public static final String PHONE_REGEX = "^\\+?\\d{1,12}$";
    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{8,}$";
    public static final Integer ZERO_CONSTANT = 0;
    public static final Long QUANTITY_ZERO = 0L;
    public static final BigDecimal TOTAL_PRICE_ZERO = new BigDecimal(0);
    public static final int MINIMUM_AGE_USER = 18;
    public static final int MAXIMUM_USER_NAME_CHARACTERS = 50;
    public static final int MAXIMUM_USER_LASTNAME_CHARACTERS = 50;
    public static final int MAXIMUM_USER_PHONE_CHARACTERS = 13;
    public static final int MAXIMUM_PRODUCT_NAME_CHARACTERS = 70;
    public static final int MAXIMUM_PRODUCT_DESCRIPTION_CHARACTERS = 120;
    public static final int MAXIMUM_CATEGORY_NAME_CHARACTERS = 70;
    public static final int MAXIMUM_CATEGORY_DESCRIPTION_CHARACTERS = 120;

    public static final String FIELD_USER_ID  = "userId";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_CATEGORY = "category";
    public static final String FIELD_DNI = "dni";
    public static final String FIELD_EMAIL = "email";

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null.";
    public static final String FIELD_LASTNAME_NULL_MESSAGE = "Field 'last name' cannot be null.";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null.";
    public static final String FIELD_DOCUMENT_NULL_MESSAGE = "Field 'document' cannot be null.";
    public static final String FIELD_PHONE_NULL_MESSAGE = "Field 'phone' cannot be null.";
    public static final String FIELD_BIRTHDATE_NULL_MESSAGE = "Field 'birthdate' cannot be null.";
    public static final String FIELD_EMAIL_NULL_MESSAGE = "Field 'email' cannot be null.";
    public static final String FIELD_PASSWORD_NULL_MESSAGE = "Field 'password' cannot be null.";
    public static final String FIELD_CREATED_AT_NULL_MESSAGE = "Field 'createdAt' cannot be null.";
    public static final String FIELD_UPDATED_AT_NULL_MESSAGE = "Field 'updatedAt' cannot be null.";
    public static final String FIELD_PRICE_NULL_MESSAGE = "Field 'price' cannot be null.";
    public static final String FIELD_IMAGE_NULL_MESSAGE = "Field 'image' cannot be null.";
    public static final String FIELD_QUANTITY_NULL_MESSAGE = "Field 'quantity' cannot be null.";
    public static final String FIELD_CATEGORY_NULL_MESSAGE = "Field 'category' cannot be null.";
    public static final String FIELD_PRODUCT_ID_NULL_MESSAGE = "Field 'productId' cannot be null.";
    public static final String FIELD_CLIENT_ID_NULL_MESSAGE = "Field 'clientId' cannot be null.";
    public static final String FIELD_TOTAL_QUANTITY_NULL_MESSAGE = "Field 'total quantity' cannot be null.";
    public static final String FIELD_TOTAL_PRICE_NULL_MESSAGE = "Field 'total price' cannot be null.";

    public static final String PRODUCT_ALREADY_EXISTS_MESSAGE = "Product already exists.";
    public static final String CATEGORY_ALREADY_EXISTS_MESSAGE = "Category already exists.";

    public static final String ROLE_NOT_FOUND = "Role not found.";
    public static final String PRODUCT_NOT_FOUND = "Product not found.";
    public static final String CATEGORY_NOT_FOUND = "Category not found.";

    public static final String MINIMUM_PRODUCT_NAME_CHARACTERS_MESSAGE = "Product name can't empty.";
    public static final String MINIMUM_PRODUCT_DESCRIPTION_CHARACTERS_MESSAGE = "Product description can't empty.";
    public static final String MINIMUM_CATEGORY_NAME_CHARACTERS_MESSAGE = "Category name can't empty.";
    public static final String MINIMUM_CATEGORY_DESCRIPTION_CHARACTERS_MESSAGE = "Category description can't empty.";

    public static final String MAXIMUM_USER_NAME_CHARACTERS_MESSAGE = "User name can have up to 50 characters maximum.";
    public static final String MAXIMUM_USER_LASTNAME_CHARACTERS_MESSAGE = "User lastname can have up to 50 characters maximum.";
    public static final String MAXIMUM_USER_PHONE_CHARACTERS_MESSAGE = "User phone can have up to 13 characters maximum.";
    public static final String MAXIMUM_PRODUCT_NAME_CHARACTERS_MESSAGE = "Product name can have up to 70 characters maximum.";
    public static final String MAXIMUM_PRODUCT_DESCRIPTION_CHARACTERS_MESSAGE = "Product description can have up to 120 characters maximum.";
    public static final String MAXIMUM_CATEGORY_NAME_CHARACTERS_MESSAGE = "Category name can have up to 70 characters maximum.";
    public static final String MAXIMUM_CATEGORY_DESCRIPTION_CHARACTERS_MESSAGE = "Category description can have up to 120 characters maximum.";

    public static final String MINIMUM_USER_NAME_CHARACTERS_MESSAGE = "User name can't empty.";
    public static final String MINIMUM_USER_LASTNAME_CHARACTERS_MESSAGE = "User lastname can't empty.";
    public static final String MINIMUM_USER_DNI_CHARACTERS_MESSAGE = "User DNI can't empty.";
    public static final String MINIMUM_USER_EMAIL_CHARACTERS_MESSAGE = "User email can't empty.";
    public static final String MINIMUM_USER_PHONE_CHARACTERS_MESSAGE = "User phone can't empty.";
    public static final String MINIMUM_USER_PASSWORD_CHARACTERS_MESSAGE = "User password can't empty.";

    public static final String INVALID_DOCUMENT = "El campo 'documento' no es válido";
    public static final String INVALID_PHONE = "El campo 'celular' no es válido";
    public static final String INVALID_EMAIL = "El campo 'email' no es válido";
    public static final String INVALID_AGE = "El campo 'fecha de nacimiento' no es válido, debes ser mayor de edad";
    public static final String INVALID_PASSWORD = "El campo 'contraseña' no es válido, debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula, un número y un carácter especial";

    public static final String DOCUMENT_ALREADY_EXISTS_MESSAGE = "Document already exists.";
    public static final String EMAIL_ALREADY_EXISTS_MESSAGE = "Email already exists.";
    public static final String PHONE_ALREADY_EXISTS_MESSAGE = "Phone already exists.";

    public static final String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials.";
    public static final String INVALID_PARAM_MESSAGE = "Invalid parameter.";

    public static final String CART_ALREADY_EXISTS_MESSAGE = "Cart already exists.";
    public static final String CART_NOT_FOUND = "Cart not found.";
    public static final String EMPTY_CART_MESSAGE = "No products were found in the cart.";
    public static final String INVALID_ADD_PRODUCT_QUANTITY_MESSAGE = "La cantidad del producto que desea agregar al carrito excede la cantidad de stock disponible para el producto.";
    public static final String INVALID_BUY_PRODUCT_QUANTITY_MESSAGE = "La cantidad del producto que desea comprar excede la cantidad de stock disponible para el producto.";
    public static final String INVALID_REMOVE_PRODUCT_QUANTITY_FROM_CART_MESSAGE = "La cantidad del producto que desea eliminar del carrito excede la cantidad del producto que está en el carrito.";
    public static final String CART_PRODUCT_NOT_FOUND = "The product was not found in the cart.";

    public static final String DEFAULT_CATEGORY_PARAM = "all";

    public static final String NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE = "Negative exception not allowed.";

    public static final String AUTHORIZATION_HEADER  = "Authorization";
    public static final String BEARER_HEADER  = "Bearer ";
    public static final String ROLE_FIELD  = "role";
}
