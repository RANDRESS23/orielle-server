package com.orielle.orielle_server.adapters.driven.jpa.mysql.repository;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByName(String name);
    Optional<ProductEntity> findByProductId(Long productId);
    Page<ProductEntity> findAll(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p JOIN p.category c ORDER BY c.name ASC")
    Page<ProductEntity> findAllOrderByCategoryNameAsc(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p JOIN p.category c ORDER BY c.name DESC")
    Page<ProductEntity> findAllOrderByCategoryNameDesc(Pageable pageable);
}
