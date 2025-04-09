package com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.CartProductEntity;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.ICartProductEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.ICartProductRepository;
import com.orielle.orielle_server.domain.model.CartProduct;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.spi.ICartProductPersistencePort;
import com.orielle.orielle_server.domain.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class CartProductAdapter implements ICartProductPersistencePort {
    private final ICartProductRepository cartProductRepository;
    private final ICartProductEntityMapper cartProductEntityMapper;

    @Override
    public CartProduct saveCartProduct(CartProduct cartProduct) {
        CartProductEntity cartProductEntity = cartProductRepository.save(cartProductEntityMapper.toEntity(cartProduct));
        return cartProductEntityMapper.toDomainModel(cartProductEntity);
    }

    @Override
    public void removeCartProduct(CartProduct cartProduct) {
        CartProductEntity cartProductEntity = cartProductEntityMapper.toEntity(cartProduct);
        cartProductRepository.delete(cartProductEntity);
    }

    @Transactional
    @Override
    public void removeAllCartProducts(Long cartId) {
        cartProductRepository.removeAllCartProducts(cartId);
    }

    @Override
    public List<CartProduct> getAllProducts(Long cartId) {
        List<CartProductEntity> listOfCartProductsEntity = cartProductRepository.findAllProducts(cartId);
        return cartProductEntityMapper.toListOfCartProducts(listOfCartProductsEntity);
    }

    @Override
    public CustomPage<CartProduct> getAllCartProducts(int page, int size, boolean ascending, Long cartId) {
        Sort sort = Boolean.TRUE.equals(ascending) ? Sort.by(Constants.FIELD_NAME).ascending() : Sort.by(Constants.FIELD_NAME).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<CartProductEntity> pageOfCartProductsEntity = cartProductRepository.findAllCartProducts(pageable, cartId);
        Page<CartProduct> cartProductPage = cartProductEntityMapper.toPageOfCartProducts(pageOfCartProductsEntity);

        CustomPage<CartProduct> customPage = new CustomPage<>();
        customPage.setPageNumber(cartProductPage.getNumber());
        customPage.setPageSize(cartProductPage.getSize());
        customPage.setTotalElements(cartProductPage.getTotalElements());
        customPage.setTotalPages(cartProductPage.getTotalPages());
        customPage.setContent(cartProductPage.getContent());

        return customPage;
    }
}
