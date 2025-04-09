package com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.CartProductEntity;
import com.orielle.orielle_server.domain.model.CartProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICartProductEntityMapper {
    CartProductEntity toEntity(CartProduct cartProduct);
    CartProduct toDomainModel(CartProductEntity cartProductEntity);
    List<CartProduct> toListOfCartProducts(List<CartProductEntity> listOfCartProductsEntity);

    default Page<CartProduct> toPageOfCartProducts(Page<CartProductEntity> pageOfCartProductsEntity) {
        List<CartProduct> dtoList = pageOfCartProductsEntity.getContent().stream()
                .map(this::toDomainModel)
                .toList();
        return new PageImpl<>(dtoList, pageOfCartProductsEntity.getPageable(), pageOfCartProductsEntity.getTotalElements());
    }
}
