package com.orielle.orielle_server.adapters.driving.mapper.response;

import com.orielle.orielle_server.adapters.driving.dto.response.UserResponse;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserResponseMapper {
    UserResponse toUserResponse(User user);

    default CustomPage<UserResponse> toPageOfUsersResponse(CustomPage<User> pageProducts) {
        List<UserResponse> dtoList = pageProducts.getContent().stream()
            .map(this::toUserResponse)
            .toList();
        CustomPage<UserResponse> customPage = new CustomPage<>();
        customPage.setPageNumber(pageProducts.getPageNumber());
        customPage.setPageSize(pageProducts.getPageSize());
        customPage.setTotalElements(pageProducts.getTotalElements());
        customPage.setTotalPages(pageProducts.getTotalPages());
        customPage.setContent(dtoList);

        return customPage;
    }
}
