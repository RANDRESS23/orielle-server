package com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.orielle.orielle_server.domain.model.Category;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.spi.ICategoryPersistencePort;
import com.orielle.orielle_server.domain.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public Category saveCategory(Category category) {
        CategoryEntity categoryEntity = categoryRepository.save(categoryEntityMapper.toEntity(category));
        return categoryEntityMapper.toDomainModel(categoryEntity);
    }

    @Override
    public void deleteCategory(String name) {
        categoryRepository.findByName(name).ifPresent(categoryRepository::delete);
    }

    @Override
    public Optional<Category> getCategory(String name) {
        return categoryRepository.findByName(name).map(categoryEntityMapper::toDomainModel);
    }

    @Override
    public Optional<Category> getCategoryById(Long idCategory) {
        return categoryRepository.findByCategoryId(idCategory).map(categoryEntityMapper::toDomainModel);
    }

    @Override
    public CustomPage<Category> getAllCategories(Integer page, Integer size, Boolean ascending) {
        Sort sort = Boolean.TRUE.equals(ascending) ? Sort.by(Constants.FIELD_NAME).ascending() : Sort.by(Constants.FIELD_NAME).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<CategoryEntity> pageCategoriesEntity = categoryRepository.findAll(pageable);
        Page<Category> pageCategories = categoryEntityMapper.toPageOfCategories(pageCategoriesEntity);

        CustomPage<Category> customPage = new CustomPage<>();
        customPage.setPageNumber(pageCategories.getNumber());
        customPage.setPageSize(pageCategories.getSize());
        customPage.setTotalElements(pageCategories.getTotalElements());
        customPage.setTotalPages(pageCategories.getTotalPages());
        customPage.setContent(pageCategories.getContent());

        return customPage;
    }

    @Override
    public List<Category> getTotalCategories() {
        return categoryRepository.findAll().stream().map(categoryEntityMapper::toDomainModel).toList();
    }
}
