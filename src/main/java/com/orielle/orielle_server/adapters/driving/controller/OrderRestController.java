package com.orielle.orielle_server.adapters.driving.controller;

import com.orielle.orielle_server.adapters.driving.dto.request.AddOrderRequest;
import com.orielle.orielle_server.adapters.driving.mapper.request.IOrderRequestMapper;
import com.orielle.orielle_server.adapters.driving.util.DrivingConstants;
import com.orielle.orielle_server.domain.api.IOrderServicePort;
import com.orielle.orielle_server.domain.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Tag(name = DrivingConstants.TAG_ORDER_NAME, description = DrivingConstants.TAG_ORDER_DESCRIPTION)
public class OrderRestController {
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderServicePort orderServicePort;

    @Operation(summary = DrivingConstants.SAVE_ORDER_SUMMARY, description = DrivingConstants.SAVE_ORDER_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.SAVE_ORDER_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.SAVE_ORDER_RESPONSE_400_DESCRIPTION, content = @Content)
    })
    @PostMapping
    public ResponseEntity<Long> addOrder(@Valid @RequestBody AddOrderRequest request) {
        Order order = orderRequestMapper.addRequestToOrder(request);
        Long orderId = orderServicePort.saveOrder(order);

        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }

    @Operation(summary = DrivingConstants.DELETE_ORDER_SUMMARY, description = DrivingConstants.DELETE_ORDER_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.DELETE_ORDER_RESPONSE_200_DESCRIPTION)
    })
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteOrder(@Valid @RequestBody Long orderId) {
        orderServicePort.deleteOrder(orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
