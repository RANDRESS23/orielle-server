package com.orielle.orielle_server.domain.api.use_case;

import com.orielle.orielle_server.domain.api.ICategoryServicePort;
import com.orielle.orielle_server.domain.exception.AlreadyExistsFieldException;
import com.orielle.orielle_server.domain.exception.NotFoundException;
import com.orielle.orielle_server.domain.model.Category;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.spi.ICategoryPersistencePort;
import com.orielle.orielle_server.domain.util.CategoryValidation;
import com.orielle.orielle_server.domain.util.Constants;

import java.time.LocalDateTime;
import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;
    private final CategoryValidation categoryValidation;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort, CategoryValidation categoryValidation) {
        this.categoryPersistencePort = categoryPersistencePort;
        this.categoryValidation = categoryValidation;
    }

    @Override
    public Category saveCategory(Category category) {
        if(categoryPersistencePort.getCategory(category.getName()).isPresent()) {
            throw new AlreadyExistsFieldException(Constants.CATEGORY_ALREADY_EXISTS_MESSAGE);
        }

        categoryValidation.validateCategory(category);

        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        return categoryPersistencePort.saveCategory(category);
    }

    @Override
    public void deleteCategory(String name) {
        Category category = categoryPersistencePort.getCategory(name)
            .orElseThrow(() -> new NotFoundException(Constants.CATEGORY_NOT_FOUND));
        categoryPersistencePort.deleteCategory(category.getName());
    }

    @Override
    public Category getCategory(String name) {
        return categoryPersistencePort.getCategory(name)
            .orElseThrow(() -> new NotFoundException(Constants.CATEGORY_NOT_FOUND));
    }

    @Override
    public CustomPage<Category> getAllCategories(Integer page, Integer size, Boolean ascending) {
        return categoryPersistencePort.getAllCategories(page, size, ascending);
    }

    @Override
    public List<Category> getTotalCategories() {
        return categoryPersistencePort.getTotalCategories();
    }
}
