package com.orielle.orielle_server.domain.util;

import com.orielle.orielle_server.domain.exception.EmptyFieldException;
import com.orielle.orielle_server.domain.exception.MaxLengthException;
import com.orielle.orielle_server.domain.model.Category;

public class CategoryValidation {
    public void validateCategory(Category category) {
        validateNameCategory(category.getName());
        validateDescriptionCategory(category.getDescription());
    }

    private void validateNameCategory(String name) {
        if (name.trim().isEmpty()) {
            throw new EmptyFieldException(Constants.MINIMUM_CATEGORY_NAME_CHARACTERS_MESSAGE);
        }

        if (name.trim().length() > Constants.MAXIMUM_CATEGORY_NAME_CHARACTERS) {
            throw new MaxLengthException(Constants.MAXIMUM_CATEGORY_NAME_CHARACTERS_MESSAGE);
        }
    }

    private void validateDescriptionCategory(String description) {
        if (description.trim().isEmpty()) {
            throw new EmptyFieldException(Constants.MINIMUM_CATEGORY_DESCRIPTION_CHARACTERS_MESSAGE);
        }

        if (description.trim().length() > Constants.MAXIMUM_CATEGORY_DESCRIPTION_CHARACTERS) {
            throw new MaxLengthException(Constants.MAXIMUM_CATEGORY_DESCRIPTION_CHARACTERS_MESSAGE);
        }
    }
}
