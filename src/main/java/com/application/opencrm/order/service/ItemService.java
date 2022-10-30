package com.application.opencrm.order.service;

import com.application.opencrm.inventory.model.Inventory;
import com.application.opencrm.inventory.service.InventoryService;
import com.application.opencrm.order.dto.ItemCreationRequestDto;
import com.application.opencrm.order.model.Item;
import com.application.opencrm.order.model.OrderingAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service handling operations on {@link OrderingAddress} objects.
 */
@Service
@RequiredArgsConstructor
public class ItemService {

    private final InventoryService inventoryService;

    /**
     * Creates {@code Item} based on the data contained within a request object.
     *
     * @param request the object containing data of {@code Category} entity to be created
     * @return the created {@code Item}
     */
    protected Item buildItem(ItemCreationRequestDto request) {
        Inventory inventory = inventoryService.getInventoryFromRepository(request.getInventoryId());
        return Item.builder()
                      .units(request.getUnits())
                      .inventory(inventory)
                      .name(inventory.getName())
                      .quantityType(inventory.getQuantityType())
                      .unitPrice(inventory.getUnitPrice())
                      .build();
    }

}
