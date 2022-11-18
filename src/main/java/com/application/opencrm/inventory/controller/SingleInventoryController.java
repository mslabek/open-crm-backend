package com.application.opencrm.inventory.controller;

import com.application.opencrm.inventory.dto.InventoryCategoryRequestDto;
import com.application.opencrm.inventory.dto.InventoryCreationRequestDto;
import com.application.opencrm.inventory.dto.InventoryParentDto;
import com.application.opencrm.inventory.dto.InventoryUpdateRequestDto;
import com.application.opencrm.inventory.model.Category;
import com.application.opencrm.inventory.model.Inventory;
import com.application.opencrm.inventory.service.InventoryService;
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
 * {@code Rest controller} handling requests referring to {@link Inventory inventory}.
 */
@Tag(name = "Inventory", description = "Operations referring to inventory")
@SecurityRequirement(name = "cookieAuth")
@RestController
@RequestMapping("/inventory")
@PreAuthorize("hasRole('ROLE_INVENTORY')")
@RequiredArgsConstructor
public class SingleInventoryController {

    private final InventoryService inventoryService;

    /**
     * Retrieves all {@link Inventory} entities from repository. Each returned dto contains its
     * {@link Category categories}. This can be useful for displaying all items regardless of their characteristics.
     *
     * @return the list of dtos representing all found {@code Inventory} entities
     */
    @GetMapping
    @Operation(summary = "Retrieves all inventories", description = "Required authorization role: ROLE_INVENTORY")
    @ApiResponse(responseCode = "200", description = "Inventories retrieved successfully")
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    List<InventoryParentDto> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    /**
     * Retrieves a {@link Inventory} entity with specified {@code id} from repository. The dto model contains its
     * {@link Category categories}.
     *
     * @param id the id of the searched {@code Inventory}
     * @return the dto representing the found {@code Inventory} entity
     */
    @GetMapping("{id}")
    @Operation(summary = "Retrieves an inventory", description = "Required authorization role: ROLE_INVENTORY")
    @Parameter(name = "id", description = "the id of the inventory to which be retrieved")
    @ApiResponse(responseCode = "200", description = "Inventory retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Inventory with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public InventoryParentDto getInventory(@PathVariable @Min(value = 1, message = "Inventory id cannot be smaller than 1") Long id) {
        return inventoryService.getInventoryWithCategories(id);
    }

    /**
     * Creates a new {@link Inventory} and stores it in database. The data of the new inventory should be passed as data
     * of a {@code InventoryRequestDto} in the request body.
     *
     * @param request the object containing data of {@code Inventory} entity to be created
     * @return the dto representing the created {@code Inventory} entity
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new category", description = "Required authorization role: ROLE_INVENTORY", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing data of the inventory to be created"))
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public InventoryParentDto createInventory(@RequestBody @Valid InventoryCreationRequestDto request) {
        return inventoryService.saveInventory(request);
    }

    /**
     * Updates an existing {@link Inventory} entity in database.
     * <p>
     * The fields that need to be changed have to be set in the {@code InventoryRequestDto} from the request body. If a
     * field of the request object is set to {@code null}, the corresponding field in the target entity is not affected
     * - it is not set to {@code null}. It is not possible to set a field of {@code Inventory} entity to {@code null}
     * through this method.
     *
     * @param id      the {@code id} of the contact entity to be updated
     * @param request the object containing new data used for the update
     * @return the dto representing the updated {@code Inventory} entity after the update
     */
    @PutMapping("{id}")
    @Operation(summary = "Updates an inventory", description = "Required authorization role: ROLE_INVENTORY", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing data of the inventory to be updated"))
    @Parameter(name = "id", description = "the id of the category to which be updated")
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Category with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public InventoryParentDto updateInventory(@PathVariable @Min(value = 1, message = "Inventory id cannot be smaller than 1") Long id, @RequestBody @Valid InventoryUpdateRequestDto request) {
        return inventoryService.updateInventory(id, request);
    }

    /**
     * Delete a {@link Inventory} entity in database.
     *
     * @param id the {@code id} of the inventory entity which is to be deleted
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes an inventory", description = "Required authorization role: ROLE_INVENTORY")
    @Parameter(name = "id", description = "the id of the inventory to which be updated")
    @ApiResponse(responseCode = "204", description = "Inventory deleted successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Inventory with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public void deleteInventory(@PathVariable @Min(value = 1, message = "Inventory id cannot be smaller than 1") Long id) {
        inventoryService.deleteInventory(id);
    }

    /**
     * Adds an {@link Inventory} to a one or multiple existing {@link Category Categories}. The categories have to be
     * specified in {@code InventoryCategoryRequestDto}.
     *
     * @param id      the {@code id} of the inventory entity which is to be added to the categories
     * @param request the object containing data specifying to which categories the item is to be added
     * @return the dto representing the updated {@code Inventory} entity after the addition
     */
    @PutMapping("{id}/category")
    @Operation(summary = "Deletes an inventory", description = "Required authorization role: ROLE_INVENTORY", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing identifiers of the categories to which the inventory is to be added"))
    @Parameter(name = "id", description = "the id of the inventory which is to be added to the specified categories")
    @ApiResponse(responseCode = "200", description = "Inventory added to category/categories successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Inventory with specified id not found or category with specified slug not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public InventoryParentDto addInventoryToCategories(@PathVariable @Min(value = 1, message = "Inventory id cannot be smaller than 1") Long id, @RequestBody @Valid InventoryCategoryRequestDto request) {
        return inventoryService.addInventoryToCategories(id, request);
    }

    /**
     * Removes an {@link Inventory} from one or multiple existing {@link Category Categories}. The categories that the
     * item will be removed from have to be specified in {@code InventoryCategoryRequestDto}.
     *
     * @param id      the {@code id} of the inventory entity which is to be removed from the categories
     * @param request the object containing data specifying which categories the item is to be removed from
     * @return the dto representing the updated {@code Inventory} entity after the removal
     */
    @DeleteMapping("{id}/category")
    @Operation(summary = "Deletes an inventory", description = "Required authorization role: ROLE_INVENTORY", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing identifiers of the categories to which the inventory is to be removed from"))
    @Parameter(name = "id", description = "the id of the inventory which is to be removed from the specified categories")
    @ApiResponse(responseCode = "200", description = "Inventory removed from category/categories successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Inventory with specified id not found or category with specified slug not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public InventoryParentDto removeInventoryFromCategories(@PathVariable @Min(value = 1, message = "Inventory id cannot be smaller than 1") Long id, @RequestBody @Valid InventoryCategoryRequestDto request) {
        return inventoryService.removeInventoryFromCategories(id, request);
    }

}
