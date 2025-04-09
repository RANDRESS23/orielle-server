package com.orielle.orielle_server.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class AddUserRequest {
    private final String name;
    private final String lastName;
    private final String dni;
    private final String phone;
    private final String birthdate;
    private final String email;
    private String password;
}
