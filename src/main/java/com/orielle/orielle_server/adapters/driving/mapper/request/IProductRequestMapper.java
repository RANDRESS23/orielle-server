package com.orielle.orielle_server.adapters.driving.mapper.request;

import com.orielle.orielle_server.adapters.driving.dto.request.AddProductRequest;
import com.orielle.orielle_server.adapters.driving.util.DrivingConstants;
import com.orielle.orielle_server.domain.model.Category;
import com.orielle.orielle_server.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IProductRequestMapper {
    @Mapping(target = DrivingConstants.PRODUCT_ID, ignore = true)
    @Mapping(source = DrivingConstants.CATEGORY_ID, target = DrivingConstants.CATEGORY_CATEGORY_ID)
    @Mapping(target = DrivingConstants.CATEGORY_CATEGORY_NAME, constant = DrivingConstants.FIELD_NAME)
    @Mapping(target = DrivingConstants.CATEGORY_CATEGORY_DESCRIPTION, constant = DrivingConstants.FIELD_DESCRIPTION)
    @Mapping(target = DrivingConstants.CREATED_AT, ignore = true)
    @Mapping(target = DrivingConstants.UPDATED_AT, ignore = true)
    static Product addRequestToProduct(AddProductRequest addProductRequest) {
        Category category = new Category(
            addProductRequest.getCategoryId(),
            null, null, null, null
        );

        return new Product.ProductBuilder()
            .name(addProductRequest.getName())
            .description(addProductRequest.getDescription())
            .quantity(addProductRequest.getQuantity())
            .price(addProductRequest.getPrice())
            .image(addProductRequest.getImage())
            .category(category)
            .build();
    }
}
