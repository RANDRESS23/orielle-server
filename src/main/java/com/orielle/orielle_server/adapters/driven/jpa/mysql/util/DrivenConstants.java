package com.orielle.orielle_server.adapters.driven.jpa.mysql.util;

public class DrivenConstants {
    private DrivenConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME_NOT_BLANK_MESSAGE = "Field 'name' cannot be null.";
    public static final String FIELD_DESCRIPTION_NOT_BLANK_MESSAGE = "Field 'description' cannot be null.";

    public static final String CATEGORY_TABLE_NAME = "category";
    public static final String PRODUCT_TABLE_NAME = "product";

    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_CART_CREATED_AT = "created_at";
    public static final String COLUMN_CART_UPDATED_AT = "updated_at";

    public static final String FIELD_CATEGORY = "category";

    public static final String SORT_BY_PRODUCT_NAME = "name";
    public static final String SORT_BY_CATEGORY_NAME = "category.name";
}
