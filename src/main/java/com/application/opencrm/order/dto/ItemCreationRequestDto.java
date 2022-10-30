package com.application.opencrm.order.dto;

import com.application.opencrm.inventory.model.Inventory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import java.math.BigInteger;

/**
 * Data transfer object of a request to create or update a {@link Inventory}.
 */
@Builder
@Getter
@AllArgsConstructor
public class ItemCreationRequestDto {

    @Schema(description = "Id of the inventory from which the ordered item will be created", required = true, example = "1")
    @Min(value = 1, message = "The id cannot be smaller than 1")
    private final Long inventoryId;

    @Schema(description = "Amount of units of the ordered item", required = true, example = "1000")
    @Min(value = 1, message = "The units cannot be smaller than 1")
    private final BigInteger units;

}
