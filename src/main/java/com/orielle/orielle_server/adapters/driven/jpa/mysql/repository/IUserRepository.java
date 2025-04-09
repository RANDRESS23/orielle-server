package com.orielle.orielle_server.adapters.driven.jpa.mysql.repository;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByDni(String dni);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPhone(String phone);

    @Query(value = "SELECT * FROM user u WHERE u.role_id = :roleId", nativeQuery = true)
    Page<UserEntity> findAllClients(@Param("roleId") Long roleId, Pageable pageable);
}
