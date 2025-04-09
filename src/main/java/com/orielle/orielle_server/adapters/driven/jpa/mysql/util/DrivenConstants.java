package com.orielle.orielle_server.adapters.driven.jpa.mysql.util;

public class DrivenConstants {
    private DrivenConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME_NOT_BLANK_MESSAGE = "Field 'name' can't be null.";
    public static final String FIELD_DESCRIPTION_NOT_BLANK_MESSAGE = "Field 'description' can't be null.";

    public static final String CATEGORY_TABLE_NAME = "category";
    public static final String PRODUCT_TABLE_NAME = "product";

    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_CART_CREATED_AT = "created_at";
    public static final String COLUMN_CART_UPDATED_AT = "updated_at";

    public static final String CART_TABLE_NAME = "cart";
    public static final String COLUMN_CART_ID = "cart_id";
    public static final String COLUMN_CART_CLIENT_ID = "client_id";
    public static final String COLUMN_CART_TOTAL_QUANTITY = "total_quantity";
    public static final String COLUMN_CART_TOTAL_PRICE = "total_price";

    public static final String CART_PRODUCT_TABLE_NAME = "cart_products";
    public static final String COLUMN_CART_PRODUCT_ID = "cart_product_id";
    public static final String COLUMN_CART_PRODUCT_PRODUCT_ID = "product_id";
    public static final String COLUMN_CART_PRODUCT_NAME = "name";
    public static final String COLUMN_CART_PRODUCT_QUANTITY = "quantity";

    public static final String FIELD_CATEGORY = "category";

    public static final String SORT_BY_PRODUCT_NAME = "name";
    public static final String SORT_BY_CATEGORY_NAME = "category.name";

    public static final String DOCUMENT_REGEX = "^\\d+$";
    public static final String PHONE_REGEX = "^\\+?\\d{1,13}$";
    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

    public static final int MAXIMUM_USER_NAME_CHARACTERS = 50;
    public static final int MAXIMUM_USER_LASTNAME_CHARACTERS = 50;
    public static final int MAXIMUM_USER_PHONE_CHARACTERS = 13;
    public static final int MAXIMUM_ROLE_DESCRIPTION_CHARACTERS = 200;

    public static final String USERS_TABLE_NAME = "user";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USER_LASTNAME = "lastname";
    public static final String COLUMN_USER_DNI = "dni";
    public static final String COLUMN_USER_PHONE = "phone";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_BIRTHDATE = "birthdate";
    public static final String COLUMN_ROLE_ID = "role_id";

    public static final String ORDER_TABLE_NAME = "orders";
    public static final String COLUMN_ORDER_ID = "order_id";
    public static final String COLUMN_ORDER_CLIENT_ID = "client_id";
    public static final String COLUMN_ORDER_TOTAL_QUANTITY = "total_quantity";
    public static final String COLUMN_ORDER_TOTAL_PRICE = "total_price";

    public static final String ORDER_ITEM_TABLE_NAME = "order_items";
    public static final String COLUMN_ORDER_ITEM_ID = "order_item_id";
    public static final String COLUMN_ORDER_ITEM_PRODUCT_ID = "product_id";
    public static final String COLUMN_ORDER_ITEM_QUANTITY = "quantity";
    public static final String COLUMN_ORDER_ITEM_TOTAL_PRICE = "total_price";

    public static final String STATE_TABLE_NAME = "state";

    public static final String ROLES_TABLE_NAME = "role";
    public static final String COLUMN_ROLE_DESCRIPTION = "description";

    public static final String USER_ID_FIELD = "userId";
    public static final String ROLE_FIELD = "role";
    public static final String FULL_NAME_FIELD = "fullName";

    public static final String INVALID_DNI = "The 'dni' field is not valid";
    public static final String INVALID_PHONE = "The 'phone' field is not valid";
    public static final String INVALID_EMAIL = "The 'email' field is not valid";

    public static final String AUTHORIZATION_HEADER  = "Authorization";
}
