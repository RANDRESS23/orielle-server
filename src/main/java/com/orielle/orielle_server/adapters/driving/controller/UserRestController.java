package com.orielle.orielle_server.adapters.driving.controller;

import com.orielle.orielle_server.adapters.driving.dto.request.AddUserRequest;
import com.orielle.orielle_server.adapters.driving.dto.response.UserResponse;
import com.orielle.orielle_server.adapters.driving.mapper.request.IUserRequestMapper;
import com.orielle.orielle_server.adapters.driving.mapper.response.IUserResponseMapper;
import com.orielle.orielle_server.adapters.driving.util.DrivingConstants;
import com.orielle.orielle_server.domain.api.IUserServicePort;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = DrivingConstants.TAG_USER_NAME, description = DrivingConstants.TAG_USER_DESCRIPTION)
public class UserRestController {
    private final IUserServicePort userServicePort;
    private final IUserResponseMapper userResponseMapper;

    @GetMapping("/client")
    public ResponseEntity<CustomPage<UserResponse>> getAllClients(
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_PAGE_PARAM) int page,
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_SIZE_PARAM) int size,
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_SORT_PARAM) String sortOrder,
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_SORT_BY_PARAM) String sortBy) {
        boolean ascending = DrivingConstants.DEFAULT_SORT_PARAM.equalsIgnoreCase(sortOrder);
        CustomPage<User> pageClients = userServicePort.getAllClients(page, size, ascending, sortBy);
        CustomPage<UserResponse> responsePage = userResponseMapper.toPageOfUsersResponse(pageClients);

        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }

    @Operation(summary = DrivingConstants.SAVE_CLIENT_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.SAVE_CLIENT_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.SAVE_CLIENT_RESPONSE_400_DESCRIPTION)
    })
    @PostMapping("/client")
    public ResponseEntity<UserResponse> addClient(@Valid @RequestBody AddUserRequest request) {
        User user = IUserRequestMapper.addRequestToUser(request);
        User userSaved = userServicePort.saveClient(user);

        UserResponse response = userResponseMapper.toUserResponse(userSaved);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
