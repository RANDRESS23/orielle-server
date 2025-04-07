package com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.orielle.orielle_server.domain.model.Category;
import com.orielle.orielle_server.domain.model.Product;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductEntityMapper {
    ProductEntity toEntity(Product product);

    static Product toDomainModel(ProductEntity productEntity, ICategoryEntityMapper categoryEntityMapper) {
        Category category = categoryEntityMapper.toDomainModel(productEntity.getCategory());

        return new Product.ProductBuilder()
            .productId(productEntity.getProductId())
            .name(productEntity.getName())
            .description(productEntity.getDescription())
            .quantity(productEntity.getQuantity())
            .price(productEntity.getPrice())
            .image(productEntity.getImage())
            .category(category)
            .createdAt(productEntity.getCreatedAt())
            .updatedAt(productEntity.getUpdatedAt())
            .build();
    }

    default Page<Product> toPageOfProducts(Page<ProductEntity> pageOfProductsEntity, ICategoryEntityMapper categoryEntityMapper) {
        List<Product> dtoList = pageOfProductsEntity.getContent().stream()
                .map(productEntity -> toDomainModel(productEntity, categoryEntityMapper))
                .toList();
        return new PageImpl<>(dtoList, pageOfProductsEntity.getPageable(), pageOfProductsEntity.getTotalElements());
    }
}
