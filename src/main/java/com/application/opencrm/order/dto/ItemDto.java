package com.application.opencrm.order.dto;

import com.application.opencrm.order.model.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

/**
 * Data transfer object of a {@link Item} for communication with processes external to the service layer.
 */
@Getter
@AllArgsConstructor
public class ItemDto {

    @Schema(description = "Amount of units of the ordered item", example = "ASUS GeForce RTX 3080 TUF GAMING V2 LHR 10GB GDDR6X")
    private final String name;

    @Schema(description = "Type of quantity units the item is measured in", example = "COUNTABLE", allowableValues = {"COUNTABLE", "DISTANCE", "AREA", "VOLUME", "MASS"})
    private final String quantityType;

    @Schema(description = "Amount of units of the ordered item", example = "1000")
    private final BigInteger units;

    @Schema(description = "Price in some currency for one unit of the item", example = "100")
    private final BigInteger unitPrice;

}
