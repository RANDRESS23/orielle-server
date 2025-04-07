package com.orielle.orielle_server.adapters.driving.controller;

import com.orielle.orielle_server.adapters.driving.dto.request.AddCategoryRequest;
import com.orielle.orielle_server.adapters.driving.dto.response.CategoryResponse;
import com.orielle.orielle_server.adapters.driving.mapper.request.ICategoryRequestMapper;
import com.orielle.orielle_server.adapters.driving.mapper.response.ICategoryResponseMapper;
import com.orielle.orielle_server.adapters.driving.util.DrivingConstants;
import com.orielle.orielle_server.domain.api.ICategoryServicePort;
import com.orielle.orielle_server.domain.model.Category;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Tag(name = DrivingConstants.TAG_CATEGORY_NAME, description = DrivingConstants.TAG_CATEGORY_DESCRIPTION)
public class CategoryRestController {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Operation(summary = DrivingConstants.SAVE_CATEGORY_SUMMARY, description = DrivingConstants.SAVE_CATEGORY_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.SAVE_CATEGORY_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.SAVE_CATEGORY_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_409, description = DrivingConstants.SAVE_CATEGORY_RESPONSE_409_DESCRIPTION, content = @Content)
    })
    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody AddCategoryRequest request) {
        Category category = categoryRequestMapper.addRequestToCategory(request);
        Category categorySaved = categoryServicePort.saveCategory(category);
        CategoryResponse response = categoryResponseMapper.toCategoryResponse(categorySaved);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = DrivingConstants.GET_CATEGORY_SUMMARY, description = DrivingConstants.GET_CATEGORY_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.GET_CATEGORY_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.GET_CATEGORY_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_404, description = DrivingConstants.GET_CATEGORY_RESPONSE_404_DESCRIPTION, content = @Content)
    })
    @GetMapping("/{name}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable String name) {
        Category category = categoryServicePort.getCategory(name);
        CategoryResponse response = categoryResponseMapper.toCategoryResponse(category);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = DrivingConstants.GET_ALL_CATEGORIES_PAGINATED_SUMMARY, description = DrivingConstants.GET_ALL_CATEGORIES_PAGINATED_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_200, description = DrivingConstants.GET_ALL_CATEGORIES_PAGINATED_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.GET_ALL_CATEGORIES_PAGINATED_RESPONSE_400_DESCRIPTION, content = @Content)
    })
    @GetMapping
    public ResponseEntity<CustomPage<CategoryResponse>> getAllCategories(
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_PAGE_PARAM) int page,
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_SIZE_PARAM) int size,
            @RequestParam(defaultValue = DrivingConstants.DEFAULT_SORT_PARAM) String sortOrder) {
        boolean ascending = DrivingConstants.DEFAULT_SORT_PARAM.equalsIgnoreCase(sortOrder);
        CustomPage<Category> pageCategories = categoryServicePort.getAllCategories(page, size, ascending);
        CustomPage<CategoryResponse> responsePage = categoryResponseMapper.toPageOfCategoryResponse(pageCategories);

        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> getTotalCategories() {
        List<Category> totalCategories = categoryServicePort.getTotalCategories();
        List<CategoryResponse> totalCategoriesResponse = totalCategories.stream()
                .map(categoryResponseMapper::toCategoryResponse)
                .toList();

        return new ResponseEntity<>(totalCategoriesResponse, HttpStatus.OK);
    }

    @Operation(summary = DrivingConstants.DELETE_CATEGORY_SUMMARY, description = DrivingConstants.DELETE_CATEGORY_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.DELETE_CATEGORY_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.DELETE_CATEGORY_RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_404, description = DrivingConstants.DELETE_CATEGORY_RESPONSE_404_DESCRIPTION, content = @Content)
    })
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String name) {
        categoryServicePort.deleteCategory(name);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
