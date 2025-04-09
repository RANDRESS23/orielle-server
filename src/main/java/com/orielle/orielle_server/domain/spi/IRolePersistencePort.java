package com.orielle.orielle_server.domain.spi;

import com.orielle.orielle_server.domain.enums.RoleEnum;
import com.orielle.orielle_server.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    Optional<Role> getRoleByName(RoleEnum name);
}
