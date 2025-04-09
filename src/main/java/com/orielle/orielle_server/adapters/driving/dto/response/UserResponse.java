package com.orielle.orielle_server.adapters.driving.dto.response;

import com.orielle.orielle_server.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserResponse {
    private final Long userId;
    private final String name;
    private final String lastName;
    private final String dni;
    private final String phone;
    private final LocalDate birthdate;
    private final String email;
    private Role role;
}
