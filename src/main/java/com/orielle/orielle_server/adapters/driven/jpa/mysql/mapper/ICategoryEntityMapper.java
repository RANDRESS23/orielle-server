package com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.orielle.orielle_server.domain.model.Category;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    Category toDomainModel(CategoryEntity categoryEntity);

    default Page<Category> toPageOfCategories(Page<CategoryEntity> pageOfCategoriesEntity) {
        List<Category> dtoList = pageOfCategoriesEntity.getContent().stream()
                .map(this::toDomainModel)
                .toList();
        return new PageImpl<>(dtoList, pageOfCategoriesEntity.getPageable(), pageOfCategoriesEntity.getTotalElements());
    }
}
