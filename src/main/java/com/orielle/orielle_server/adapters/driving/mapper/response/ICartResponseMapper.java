package com.orielle.orielle_server.adapters.driving.mapper.response;

import com.orielle.orielle_server.adapters.driving.dto.response.CartDto;
import com.orielle.orielle_server.adapters.driving.dto.response.CartResponse;
import com.orielle.orielle_server.domain.model.Cart;
import com.orielle.orielle_server.domain.model.CartProduct;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICartResponseMapper {
    CartResponse toCartResponse(Cart cart);
    CartDto toCartDto(Cart cart);
    Product toProduct(CartProduct cartProduct);

    default CustomPage<Product> toPageProductDto(CustomPage<CartProduct> cartProduct) {
        List<Product> productList = cartProduct.getContent().stream()
                .map(this::toProduct)
                .toList();

        CustomPage<Product> productDtoPage = new CustomPage<>();
        productDtoPage.setPageNumber(cartProduct.getPageNumber());
        productDtoPage.setPageSize(cartProduct.getPageSize());
        productDtoPage.setTotalElements(cartProduct.getTotalElements());
        productDtoPage.setTotalPages(cartProduct.getTotalPages());
        productDtoPage.setContent(productList);

        return productDtoPage;
    }
}
