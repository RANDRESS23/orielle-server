package com.orielle.orielle_server.adapters.driven.jpa.mysql.repository;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    @Modifying
    @Query(value = "DELETE FROM orders WHERE order_id = :orderId", nativeQuery = true)
    void removeOrder(@Param("orderId") Long orderId);
}
