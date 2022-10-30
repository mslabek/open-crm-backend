package com.application.opencrm.inventory.dto;

import com.application.opencrm.inventory.model.Category;
import com.application.opencrm.inventory.model.Inventory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * Data transfer object of a {@link Category} for communication with processes external to the service layer. This
 * dto contains {@link Inventory inventories} belonging to it.
 *
 * @see CategoryChildDto
 */
@Getter
@AllArgsConstructor
public class CategoryParentDto {

    @Schema(description = "Category id", example = "1")
    private final Long id;

    @Schema(description = "Inventories that belong to the category")
    private final Set<InventoryChildDto> inventories;

    @Schema(description = "Category name", example = "PC components")
    private final String name;

    @Schema(description = "Slug of the name", example = "pc-components")
    private final String slug;

    @Schema(description = "Category description", example = "Hardware parts for building a personal computer")
    private final String description;

}
