package com.orielle.orielle_server.adapters.driving.mapper.request;

import com.orielle.orielle_server.adapters.driving.dto.request.AddCategoryRequest;
import com.orielle.orielle_server.adapters.driving.util.DrivingConstants;
import com.orielle.orielle_server.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {
    @Mapping(target = DrivingConstants.CATEGORY_ID, ignore = true)
    @Mapping(target = DrivingConstants.CREATED_AT, ignore = true)
    @Mapping(target = DrivingConstants.UPDATED_AT, ignore = true)
    Category addRequestToCategory(AddCategoryRequest addCategoryRequest);
}
