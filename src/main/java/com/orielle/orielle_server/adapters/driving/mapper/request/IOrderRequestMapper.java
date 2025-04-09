package com.orielle.orielle_server.adapters.driving.mapper.request;

import com.orielle.orielle_server.adapters.driving.dto.request.AddOrderRequest;
import com.orielle.orielle_server.adapters.driving.dto.request.OrderItemDto;
import com.orielle.orielle_server.domain.enums.StatusEnum;
import com.orielle.orielle_server.domain.model.Order;
import com.orielle.orielle_server.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {
    OrderItem toOrderItem(OrderItemDto orderItemDto);

    default Order addRequestToOrder(AddOrderRequest addOrderRequest) {
        List<OrderItem> listOfOrderItems = addOrderRequest.getProducts().stream()
            .map(this::toOrderItem)
            .toList();

        return new Order.OrderBuilder()
            .clientId(addOrderRequest.getClientId())
            .totalQuantity(addOrderRequest.getTotalQuantity())
            .totalPrice(addOrderRequest.getTotalPrice())
            .products(listOfOrderItems)
            .status(StatusEnum.PENDING)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }
}
