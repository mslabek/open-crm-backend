package com.application.opencrm.inventory.dto;

import com.application.opencrm.inventory.model.Category;
import com.application.opencrm.inventory.model.Inventory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * Data transfer object of a request to remove or add {@link Inventory} to one or multiple {@link Category Categories}.
 */
@Getter
@AllArgsConstructor
public class InventoryCategoryRequestDto {

    @Schema(description = "List of slugs identifying categories the inventory is to be added to or removed from", required = true)
    private final Set<String> categoriesSlugs;

}
