package com.orielle.orielle_server.domain.spi;

import com.orielle.orielle_server.domain.model.Category;
import com.orielle.orielle_server.domain.model.CustomPage;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {
    Category saveCategory(Category category);
    void deleteCategory(String name);
    Optional<Category> getCategory(String name);
    Optional<Category> getCategoryById(Long idCategory);
    CustomPage<Category> getAllCategories(Integer page, Integer size, Boolean ascending);
    List<Category> getTotalCategories();
}
