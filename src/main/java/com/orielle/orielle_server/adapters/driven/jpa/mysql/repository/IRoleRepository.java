package com.orielle.orielle_server.adapters.driven.jpa.mysql.repository;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.orielle.orielle_server.domain.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
