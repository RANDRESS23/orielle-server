package com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.UserEntity;
import com.orielle.orielle_server.domain.model.Role;
import com.orielle.orielle_server.domain.model.User;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {
    UserEntity toEntity(User user);

    static User toDomainModel(UserEntity userEntity, IRoleEntityMapper roleEntityMapper) {
        Role role = roleEntityMapper.toDomainModel(userEntity.getRole());

        return new User.UserBuilder()
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .lastName(userEntity.getLastName())
                .dni(userEntity.getDni())
                .phone(userEntity.getPhone())
                .birthdate(userEntity.getBirthdate())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .role(role)
                .build();
    }

    default Page<User> toPageOfUsers(Page<UserEntity> pageOfUsersEntity, IRoleEntityMapper roleEntityMapper) {
        List<User> dtoList = pageOfUsersEntity.getContent().stream()
                .map(userEntity -> toDomainModel(userEntity, roleEntityMapper))
                .toList();
        return new PageImpl<>(dtoList, pageOfUsersEntity.getPageable(), pageOfUsersEntity.getTotalElements());
    }
}
