package com.orielle.orielle_server.adapters.driven.jpa.mysql.repository;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.CartProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartProductRepository extends JpaRepository<CartProductEntity, Long> {
    @Query(value = "SELECT * FROM cart_products c WHERE c.cart_id = :cartId", nativeQuery = true)
    List<CartProductEntity> findAllProducts(@Param("cartId") Long cartId);

    @Query(value = "SELECT * FROM cart_products c WHERE c.cart_id = :cartId", nativeQuery = true)
    Page<CartProductEntity> findAllCartProducts(Pageable pageable, @Param("cartId") Long cartId);

    @Modifying
    @Query(value = "DELETE FROM cart_products WHERE cart_id = :cartId", nativeQuery = true)
    void removeAllCartProducts(@Param("cartId") Long cartId);
}
