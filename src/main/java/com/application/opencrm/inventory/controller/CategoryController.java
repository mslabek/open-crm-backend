package com.application.opencrm.inventory.controller;

import com.application.opencrm.inventory.dto.*;
import com.application.opencrm.inventory.model.Category;
import com.application.opencrm.inventory.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * {@code Rest controller} handling requests referring to {@link Category categories}.
 */
@Tag(name = "Inventory | Category", description = "Operations referring to categories of inventory")
@SecurityRequirement(name = "cookieAuth")
@RestController
@RequestMapping("/category")
@PreAuthorize("hasRole('ROLE_INVENTORY')")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Retrieves all {@link Category} entities from repository.
     *
     * @return the list of dtos representing all found {@code Category} entities
     */
    @GetMapping
    @Operation(summary = "Retrieves all categories", description = "Required authorization role: ROLE_INVENTORY")
    @ApiResponse(responseCode = "200", description = "Inventories retrieved successfully")
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public List<CategoryChildDto> getAllInventoryCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Retrieves a {@link Category} entity with specified {@code id} from repository.
     *
     * @param id the id of the searched {@code Category}
     * @return the dto representing the found {@code Category} entity
     */
    @GetMapping("{id}")
    @Operation(summary = "Retrieves a category with belonging inventories", description = "Required authorization role: ROLE_INVENTORY")
    @Parameter(name = "id", description = "the id of the category to which be retrieved")
    @ApiResponse(responseCode = "200", description = "Category retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Category with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public CategoryParentDto getCategory(@PathVariable @Min(value = 1, message = "Category id cannot be smaller than 1") Long id) {
        return categoryService.getCategoryWithInventories(id);
    }

    /**
     * Creates a new {@link Category} and stores it in database. The data of the new category should be passed as data
     * of a {@code CategoryRequestDto} in the request body.
     *
     * @param request the object containing data of {@code Category} entity to be created
     * @return the dto representing the created {@code Category} entity
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new category", description = "Required authorization role: ROLE_INVENTORY", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing data of the category to be created"))
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public CategoryChildDto createCategory(@RequestBody @Valid CategoryCreationRequestDto request) {
        return categoryService.saveCategory(request);
    }

    /**
     * Updates an existing {@link Category} entity in database.
     * <p>
     * The fields that need to be changed have to be set in the {@code CategoryRequestDto} from the request body.
     * If a field of the request object is set to {@code null}, the corresponding field in the target entity is not
     * affected - it is not set to {@code null}. It is not possible to set a field of {@code Category} entity to
     * {@code null} through this method.
     *
     * @param id      the {@code id} of the client entity to be updated
     * @param request the object containing new data used for the update
     * @return the dto representing the updated {@code Category} entity after the update
     */
    @PutMapping("{id}")
    @Operation(summary = "Updates a category", description = "Required authorization role: ROLE_INVENTORY", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing data of the category to be updated"))
    @Parameter(name = "id", description = "the id of the category to which be updated")
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Category with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public CategoryChildDto updateInventory(@PathVariable @Min(value = 1, message = "Category id cannot be smaller than 1") Long id, @RequestBody @Valid CategoryUpdateRequestDto request) {
        return categoryService.updateCategory(id, request);
    }

    /**
     * Delete a {@link Category} entity with specified {@code id} in database.
     *
     * @param id the {@code id} of the {@code Category} entity which is to be deleted
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes a category", description = "Required authorization role: ROLE_INVENTORY")
    @Parameter(name = "id", description = "the id of the client to which be updated")
    @ApiResponse(responseCode = "204", description = "Category deleted successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Category with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public void deleteCategory(@PathVariable @Min(value = 1, message = "Category id cannot be smaller than 1") Long id) {
        categoryService.deleteCategory(id);
    }

}
