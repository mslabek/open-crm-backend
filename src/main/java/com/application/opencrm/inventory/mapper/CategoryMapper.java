package com.application.opencrm.inventory.mapper;

import com.application.opencrm.inventory.dto.*;
import com.application.opencrm.inventory.model.Category;
import org.mapstruct.*;

/**
 * Mapper used to handle mapping and updating operations on {@link Category} objects and on related dtos.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface CategoryMapper {

    /**
     * Maps a {@code Category} to {@code CategoryChildDto} object.
     *
     * @param category the {@code category} to be mapped
     * @return the mapped {@code CategoryChildDto}
     */
    CategoryChildDto categoryToCategoryChildDto(Category category);

    /**
     * Maps a {@code Category} to {@code CategoryParentDto} object.
     *
     * @param category the {@code category} to be mapped
     * @return the mapped {@code CategoryParentDto}
     */
    CategoryParentDto categoryToCategoryParentDto(Category category);

    /**
     * Maps a {@code CategoryRequestDto} to {@code Category} object.
     *
     * @param request the {@code request} to be mapped
     * @return the mapped {@code Category}
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventories", ignore = true)
    @Mapping(target = "slug", ignore = true)
    Category categoryRequestDtoToCategory(CategoryCreationRequestDto request);

    /**
     * Updates fields in a {@code Category} entity from data contained in {@code CategoryRequestDto}. The method
     * changes the entity's data to the data from all non-null fields in the request dto.
     *
     * @param category the {@code category} to be updated
     * @param request the object containing new data
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventories", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategory(@MappingTarget Category category, CategoryUpdateRequestDto request);

}
