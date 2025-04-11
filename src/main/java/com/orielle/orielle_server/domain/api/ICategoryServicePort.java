package com.orielle.orielle_server.domain.api;

import com.orielle.orielle_server.domain.model.Category;
import com.orielle.orielle_server.domain.model.CustomPage;

import java.util.List;

public interface ICategoryServicePort {
    Category saveCategory(Category category);
    void deleteCategory(String name);
    Category getCategory(String name);
    Category getCategoryById(Long idCategory);
    CustomPage<Category> getAllCategories(Integer page, Integer size, Boolean ascending);
    List<Category> getTotalCategories();
}
