package com.orielle.orielle_server.adapters.driving.controller;

import com.orielle.orielle_server.adapters.driving.dto.request.AddProductToCart;
import com.orielle.orielle_server.adapters.driving.dto.response.CartDto;
import com.orielle.orielle_server.adapters.driving.dto.response.CartResponse;
import com.orielle.orielle_server.adapters.driving.dto.response.ListCartProducts;
import com.orielle.orielle_server.adapters.driving.mapper.request.ICartProductRequestMapper;
import com.orielle.orielle_server.adapters.driving.mapper.response.ICartResponseMapper;
import com.orielle.orielle_server.adapters.driving.util.DrivingConstants;
import com.orielle.orielle_server.domain.api.ICartProductServicePort;
import com.orielle.orielle_server.domain.api.ICartServicePort;
import com.orielle.orielle_server.domain.model.Cart;
import com.orielle.orielle_server.domain.model.CartProduct;
import com.orielle.orielle_server.domain.model.CustomPage;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = DrivingConstants.TAG_CART_NAME, description = DrivingConstants.TAG_CART_DESCRIPTION)
public class CartRestController {
    private final ICartProductServicePort cartProductServicePort;
    private final ICartServicePort cartServicePort;
    private final ICartProductRequestMapper cartProductRequestMapper;
    private final ICartResponseMapper cartResponseMapper;

    @Operation(summary = DrivingConstants.SAVE_CART_PRODUCT_SUMMARY, description = DrivingConstants.SAVE_CART_PRODUCT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.SAVE_CART_PRODUCT_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.SAVE_CART_PRODUCT_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_503, description = DrivingConstants.SAVE_CART_PRODUCT_RESPONSE_503_DESCRIPTION, content = @Content)
    })
    @PostMapping
    public ResponseEntity<CartResponse> addProductToCart(@Valid @RequestBody AddProductToCart request) {
        CartProduct product = cartProductRequestMapper.addRequestToCartProduct(request);
        Cart cartUpdated = cartProductServicePort.saveCartProduct(product);
        CartResponse response = cartResponseMapper.toCartResponse(cartUpdated);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = DrivingConstants.REMOVE_CART_PRODUCT_SUMMARY, description = DrivingConstants.REMOVE_CART_PRODUCT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.REMOVE_CART_PRODUCT_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.REMOVE_CART_PRODUCT_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_503, description = DrivingConstants.REMOVE_CART_PRODUCT_RESPONSE_503_DESCRIPTION, content = @Content)
    })
    @DeleteMapping
    public ResponseEntity<CartResponse> removeProductToCart(@Valid @RequestBody AddProductToCart request) {
        CartProduct product = cartProductRequestMapper.addRequestToCartProduct(request);
        Cart cartUpdated = cartProductServicePort.removeCartProduct(product);
        CartResponse response = cartResponseMapper.toCartResponse(cartUpdated);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = DrivingConstants.GET_CART_PRODUCTS_SUMMARY, description = DrivingConstants.GET_CART_PRODUCTS_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.GET_CART_PRODUCTS_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.GET_CART_PRODUCTS_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_503, description = DrivingConstants.GET_CART_PRODUCTS_RESPONSE_503_DESCRIPTION, content = @Content)
    })
    @GetMapping
    public ResponseEntity<ListCartProducts> getAllProducts(
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_PAGE_PARAM) int page,
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_SIZE_PARAM) int size,
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_SORT_PARAM) String sortOrder,
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_CATEGORY_PARAM) String category) {
        boolean ascending = DrivingConstants.DEFAULT_SORT_PARAM.equalsIgnoreCase(sortOrder);
        CustomPage<CartProduct> cartProductPage = cartProductServicePort.getAllCartProducts(page, size, ascending, category);
        CartDto cart = cartResponseMapper.toCartDto(cartServicePort.getCartByClientId());
        ListCartProducts responsePage = new ListCartProducts(cart, cartResponseMapper.toPageProductDto(cartProductPage));

        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Cart> getAllProductsInTheCart() {
        Cart cart = cartProductServicePort.getAllCartProductsInTheCart();

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/buy")
    public ResponseEntity<Map<String, String>> buyProducts() {
        cartServicePort.buyCartProducts();

        HashMap<String, String> map = new HashMap<>();
        map.put(DrivingConstants.MESSAGE, DrivingConstants.CART_PRODUCTS_BOUGHT_MESSAGE);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
