package com.orielle.orielle_server.adapters.driven.jpa.mysql.repository;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IOrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    @Modifying
    @Query(value = "DELETE FROM order_items WHERE order_id = :orderId", nativeQuery = true)
    void removeAllCartOrderItems(@Param("orderId") Long orderId);
}
