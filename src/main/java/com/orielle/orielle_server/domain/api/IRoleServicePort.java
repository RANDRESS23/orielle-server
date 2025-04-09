package com.orielle.orielle_server.domain.api;

import com.orielle.orielle_server.domain.enums.RoleEnum;
import com.orielle.orielle_server.domain.model.Role;

public interface IRoleServicePort {
    Role getRole(RoleEnum name);
}
