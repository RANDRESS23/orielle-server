package com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.orielle.orielle_server.domain.enums.RoleEnum;
import com.orielle.orielle_server.domain.model.Role;
import com.orielle.orielle_server.domain.spi.IRolePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public Optional<Role> getRoleByName(RoleEnum name) {
        return roleRepository.findByName(name)
                .map(roleEntityMapper::toDomainModel);
    }
}
