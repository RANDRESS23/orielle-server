package com.orielle.orielle_server.domain.api.use_case;

import com.orielle.orielle_server.domain.api.IRoleServicePort;
import com.orielle.orielle_server.domain.enums.RoleEnum;
import com.orielle.orielle_server.domain.exception.NotFoundException;
import com.orielle.orielle_server.domain.model.Role;
import com.orielle.orielle_server.domain.spi.IRolePersistencePort;
import com.orielle.orielle_server.domain.util.Constants;

public class RoleUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Role getRole(RoleEnum name) {
        return rolePersistencePort.getRoleByName(name)
            .orElseThrow(() -> new NotFoundException(Constants.ROLE_NOT_FOUND));
    }
}
