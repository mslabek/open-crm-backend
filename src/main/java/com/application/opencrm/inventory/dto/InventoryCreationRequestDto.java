package com.application.opencrm.inventory.dto;

import com.application.opencrm.inventory.model.Inventory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.util.Set;

/**
 * Data transfer object of a request to create a {@link Inventory}.
 */
@Builder
@Getter
@AllArgsConstructor
public class InventoryCreationRequestDto {

    @Schema(description = "Inventory name", example = "ASUS GeForce RTX 3080 TUF GAMING V2 LHR 10GB GDDR6X", required = true)
    @NotBlank(message = "Inventory name cannot be blank")
    private final String name;

    @Schema(description = "Inventory description", example = "New 3080 series graphics card made by ASUS")
    private final String description;

    @Schema(description = "Price in some currency for one unit", example = "800000", required = true)
    @Min(value = 0, message = "Price cannot be smaller than 0")
    private final BigInteger unitPrice;

    @Schema(description = "Type of quantity units the item is measured in", example = "COUNTABLE", allowableValues = {"COUNTABLE", "DISTANCE", "AREA", "VOLUME", "MASS"}, required = true)
    @NotBlank(message = "Quantity type cannot be blank")
    private final String quantityType;

    @Schema(description = "Amount of units of the inventory in stock", example = "0", required = true)
    @Min(value = 0, message = "Amount of units cannot be smaller than 0")
    private final BigInteger units;

    @Schema(description = "List of slugs identifying categories this inventory is to be added to")
    private final Set<String> categoriesSlugs;

}
