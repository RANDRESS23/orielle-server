package com.orielle.orielle_server.adapters.driving.mapper.response;

import com.orielle.orielle_server.adapters.driving.dto.response.CategoryResponse;
import com.orielle.orielle_server.domain.model.Category;
import com.orielle.orielle_server.domain.model.CustomPage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    CategoryResponse toCategoryResponse(Category category);

    default CustomPage<CategoryResponse> toPageOfCategoryResponse(CustomPage<Category> pageCategories) {
        List<CategoryResponse> dtoList = pageCategories.getContent().stream()
                .map(this::toCategoryResponse)
                .toList();
        CustomPage<CategoryResponse> customPage = new CustomPage<>();
        customPage.setPageNumber(pageCategories.getPageNumber());
        customPage.setPageSize(pageCategories.getPageSize());
        customPage.setTotalElements(pageCategories.getTotalElements());
        customPage.setTotalPages(pageCategories.getTotalPages());
        customPage.setContent(dtoList);

        return customPage;
    }
}
