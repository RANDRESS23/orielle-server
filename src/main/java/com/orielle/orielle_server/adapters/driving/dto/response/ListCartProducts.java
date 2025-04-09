package com.orielle.orielle_server.adapters.driving.dto.response;

import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ListCartProducts {
    CartDto cart;
    CustomPage<Product> products;
}
