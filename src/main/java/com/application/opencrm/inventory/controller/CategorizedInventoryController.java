package com.application.opencrm.inventory.controller;

import com.application.opencrm.inventory.dto.CategoryParentDto;
import com.application.opencrm.inventory.model.Category;
import com.application.opencrm.inventory.model.Inventory;
import com.application.opencrm.inventory.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * {@code Rest controller} handling requests referring to {@link Inventory Inventory} grouped by {@link Category
 * categories}.
 */
@Tag(name = "Inventory")
@RestController
@RequestMapping("/category-inventory")
@PreAuthorize("hasRole('ROLE_INVENTORY')")
@RequiredArgsConstructor
public class CategorizedInventoryController {

    private final CategoryService categoryService;

    /**
     * Retrieves all {@link Inventory} entities from repository belonging to the specified {@link Category}.
     *
     * @param slug the slug of the {@code Category} which {@code Inventory} will be retrieved
     * @return the {@code Category} dto with all belonging {@code Inventory} objects
     */
    @GetMapping("{slug}")
    @Operation(summary = "Retrieves all inventories belonging to the specified category", description = "Required authorization role: ROLE_INVENTORY")
    @Parameter(name = "slug", description = "the slug of the category from which inventories are to be retrieved")
    @ApiResponse(responseCode = "200", description = "Inventories retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Category with specified slug not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public CategoryParentDto getCategorizedInventory(@PathVariable @NotBlank String slug) {
        return categoryService.getCategoryWithInventories(slug);
    }

}
