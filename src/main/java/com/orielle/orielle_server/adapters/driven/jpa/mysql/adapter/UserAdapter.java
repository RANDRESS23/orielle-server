package com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.UserEntity;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.orielle.orielle_server.domain.api.IRoleServicePort;
import com.orielle.orielle_server.domain.enums.RoleEnum;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.Role;
import com.orielle.orielle_server.domain.model.User;
import com.orielle.orielle_server.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;
    private final IRoleServicePort roleServicePort;

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));
        return IUserEntityMapper.toDomainModel(userEntity, roleEntityMapper);
    }

    @Override
    public Optional<User> getUserByDocument(String name) {
        return userRepository.findByDni(name)
                .map(userEntity -> IUserEntityMapper.toDomainModel(userEntity, roleEntityMapper));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntity -> IUserEntityMapper.toDomainModel(userEntity, roleEntityMapper));
    }

    @Override
    public Optional<User> getUserByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .map(userEntity -> IUserEntityMapper.toDomainModel(userEntity, roleEntityMapper));
    }

    @Override
    public CustomPage<User> getAllClients(Integer page, Integer size, Boolean ascending, String sortBy) {
        Sort sort = Boolean.TRUE.equals(ascending) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Role role = roleServicePort.getRole(RoleEnum.CLIENTE);
        Page<UserEntity> pageUsersEntity = userRepository.findAllClients(role.getRoleId(), pageable);
        Page<User> pageUsers = userEntityMapper.toPageOfUsers(pageUsersEntity, roleEntityMapper);

        CustomPage<User> customPage = new CustomPage<>();
        customPage.setPageNumber(pageUsers.getNumber());
        customPage.setPageSize(pageUsers.getSize());
        customPage.setTotalElements(pageUsers.getTotalElements());
        customPage.setTotalPages(pageUsers.getTotalPages());
        customPage.setContent(pageUsers.getContent());

        return customPage;
    }
}
