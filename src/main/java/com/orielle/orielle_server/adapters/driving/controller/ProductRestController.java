package com.orielle.orielle_server.adapters.driving.controller;

import com.orielle.orielle_server.adapters.driving.dto.request.AddProductRequest;
import com.orielle.orielle_server.adapters.driving.dto.request.UpdateProductQuantityRequest;
import com.orielle.orielle_server.adapters.driving.dto.response.ProductResponse;
import com.orielle.orielle_server.adapters.driving.mapper.request.IProductRequestMapper;
import com.orielle.orielle_server.adapters.driving.mapper.response.IProductResponseMapper;
import com.orielle.orielle_server.adapters.driving.util.DrivingConstants;
import com.orielle.orielle_server.domain.api.IProductServicePort;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.Product;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Tag(name = DrivingConstants.TAG_PRODUCT_NAME, description = DrivingConstants.TAG_PRODUCT_DESCRIPTION)
public class ProductRestController {
    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;
    private final IProductResponseMapper productResponseMapper;

    @Operation(summary = DrivingConstants.SAVE_PRODUCT_SUMMARY, description = DrivingConstants.SAVE_PRODUCT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.SAVE_PRODUCT_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.SAVE_PRODUCT_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_409, description = DrivingConstants.SAVE_PRODUCT_RESPONSE_409_DESCRIPTION, content = @Content)
    })
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody AddProductRequest request) {
        Product product = IProductRequestMapper.addRequestToProduct(request);
        Product productSaved = productServicePort.saveProduct(product);

        ProductResponse response = productResponseMapper.toProductResponse(productSaved);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = DrivingConstants.GET_PRODUCT_SUMMARY, description = DrivingConstants.GET_PRODUCT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.GET_PRODUCT_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.GET_PRODUCT_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_404, description = DrivingConstants.GET_PRODUCT_RESPONSE_404_DESCRIPTION, content = @Content)
    })
    @GetMapping("/{name}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String name) {
        Product product = productServicePort.getProduct(name);
        ProductResponse response = productResponseMapper.toProductResponse(product);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = DrivingConstants.GET_PRODUCT_SUMMARY, description = DrivingConstants.GET_PRODUCT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.GET_PRODUCT_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.GET_PRODUCT_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_404, description = DrivingConstants.GET_PRODUCT_RESPONSE_404_DESCRIPTION, content = @Content)
    })
    @GetMapping("/id/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
        Product product = productServicePort.getProductById(productId);
        ProductResponse response = productResponseMapper.toProductResponse(product);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = DrivingConstants.GET_ALL_PRODUCTS_PAGINATED_SUMMARY, description = DrivingConstants.GET_ALL_PRODUCTS_PAGINATED_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_200, description = DrivingConstants.GET_ALL_PRODUCTS_PAGINATED_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.GET_ALL_PRODUCTS_PAGINATED_RESPONSE_400_DESCRIPTION, content = @Content)
    })
    @GetMapping
    public ResponseEntity<CustomPage<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_PAGE_PARAM) int page,
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_SIZE_PARAM) int size,
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_SORT_PARAM) String sortOrder,
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_SORT_BY_PARAM) String sortBy) {
        boolean ascending = DrivingConstants.DEFAULT_SORT_PARAM.equalsIgnoreCase(sortOrder);
        CustomPage<Product> pageProducts = productServicePort.getAllProducts(page, size, ascending, sortBy);
        CustomPage<ProductResponse> responsePage = productResponseMapper.toPageOfProductResponse(pageProducts);

        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getTotalProducts() {
        List<Product> totalProducts = productServicePort.getTotalProducts();
        List<ProductResponse> totalProductsResponse = totalProducts.stream()
                .map(productResponseMapper::toProductResponse)
                .toList();

        return new ResponseEntity<>(totalProductsResponse, HttpStatus.OK);
    }

    @Operation(summary = DrivingConstants.UPDATE_PRODUCT_QUANTITY_SUMMARY, description = DrivingConstants.UPDATE_PRODUCT_QUANTITY_PRODUCT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.UPDATE_PRODUCT_QUANTITY_PRODUCT_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.UPDATE_PRODUCT_QUANTITY_PRODUCT_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_404, description = DrivingConstants.UPDATE_PRODUCT_QUANTITY_PRODUCT_RESPONSE_404_DESCRIPTION, content = @Content)
    })
    @PatchMapping("/update-quantity")
    public ResponseEntity<ProductResponse> updateProductQuantity(@Valid @RequestBody UpdateProductQuantityRequest request) {
        Long productId = request.getProductId();
        Long extraQuantity = request.getQuantity();
        Boolean isAddProductQuantity = request.getIsAddProductQuantity();

        Product productUpdated = productServicePort.updateProductQuantity(productId, extraQuantity, isAddProductQuantity);
        ProductResponse response = productResponseMapper.toProductResponse(productUpdated);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = DrivingConstants.DELETE_PRODUCT_SUMMARY, description = DrivingConstants.DELETE_PRODUCT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.DELETE_PRODUCT_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.DELETE_PRODUCT_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_404, description = DrivingConstants.DELETE_PRODUCT_RESPONSE_404_DESCRIPTION, content = @Content)
    })
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String name) {
        productServicePort.deleteProduct(name);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
