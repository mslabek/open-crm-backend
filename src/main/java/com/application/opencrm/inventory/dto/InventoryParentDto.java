package com.application.opencrm.inventory.dto;

import com.application.opencrm.inventory.model.Category;
import com.application.opencrm.inventory.model.Inventory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;
import java.util.Set;

/**
 * Data transfer object of a {@link Inventory} for communication with processes external to the service layer. This
 * dto contains {@link Category categories} it belongs to.
 *
 * @see CategoryChildDto
 */
@Getter
@AllArgsConstructor
public class InventoryParentDto {

    @Schema(description = "Inventory id", example = "1")
    private final Long id;

    @Schema(description = "Inventory name", example = "ASUS GeForce RTX 3080 TUF GAMING V2 LHR 10GB GDDR6X")
    private final String name;

    @Schema(description = "Inventory description", example = "New 3080 series graphics card made by ASUS")
    private final String description;

    @Schema(description = "List of slugs identifying categories this inventory is to be added to")
    private final Set<CategoryChildDto> categories;

    @Schema(description = "Price in some currency for one unit", example = "800000")
    private final BigInteger unitPrice;

    @Schema(description = "Type of quantity units the item is measured in", example = "COUNTABLE", allowableValues = {"COUNTABLE", "DISTANCE", "AREA", "VOLUME", "MASS"})
    private final String quantityType;

    @Schema(description = "Amount of units of the inventory in stock", example = "0")
    private final BigInteger units;

}
