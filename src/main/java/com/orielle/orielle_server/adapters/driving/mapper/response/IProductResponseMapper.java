package com.orielle.orielle_server.adapters.driving.mapper.response;

import com.orielle.orielle_server.adapters.driving.dto.response.ProductResponse;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductResponseMapper {
    ProductResponse toProductResponse(Product product);

    default CustomPage<ProductResponse> toPageOfProductResponse(CustomPage<Product> pageProducts) {
        List<ProductResponse> dtoList = pageProducts.getContent().stream()
                .map(this::toProductResponse)
                .toList();
        CustomPage<ProductResponse> customPage = new CustomPage<>();
        customPage.setPageNumber(pageProducts.getPageNumber());
        customPage.setPageSize(pageProducts.getPageSize());
        customPage.setTotalElements(pageProducts.getTotalElements());
        customPage.setTotalPages(pageProducts.getTotalPages());
        customPage.setContent(dtoList);

        return customPage;
    }
}
