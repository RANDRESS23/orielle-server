package com.orielle.orielle_server.adapters.driving.mapper.request;

import com.orielle.orielle_server.adapters.driving.dto.request.AddProductToCart;
import com.orielle.orielle_server.adapters.driving.util.DrivingConstants;
import com.orielle.orielle_server.domain.model.CartProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICartProductRequestMapper {
    @Mapping(target = DrivingConstants.CART_PRODUCT_ID, ignore = true)
    @Mapping(target = DrivingConstants.CART_ID, ignore = true)
    CartProduct addRequestToCartProduct(AddProductToCart addProductToCart);
}
