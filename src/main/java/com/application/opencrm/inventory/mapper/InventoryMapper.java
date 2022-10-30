package com.application.opencrm.inventory.mapper;

import com.application.opencrm.inventory.dto.InventoryChildDto;
import com.application.opencrm.inventory.dto.InventoryCreationRequestDto;
import com.application.opencrm.inventory.dto.InventoryParentDto;
import com.application.opencrm.inventory.dto.InventoryUpdateRequestDto;
import com.application.opencrm.inventory.model.Inventory;
import org.mapstruct.*;

/**
 * Mapper used to handle mapping and updating operations on {@link Inventory} objects and on related dtos.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy =
                                                                                          CollectionMappingStrategy.ADDER_PREFERRED, builder = @Builder(disableBuilder = true), uses = CategoryMapper.class)
public interface InventoryMapper {

    /**
     * Maps an {@code Inventory} to {@code InventoryParentDto} object.
     *
     * @param inventory the {@code inventory} to be mapped
     * @return the mapped {@code InventoryParentDto}
     */
    InventoryParentDto inventoryToInventoryParentDto(Inventory inventory);

    /**
     * Maps an {@code Inventory} to {@code InventoryChildDto} object.
     *
     * @param inventory the {@code inventory} to be mapped
     * @return the mapped {@code InventoryChildDto}
     */
    InventoryChildDto inventoryToInventoryChildDto(Inventory inventory);

    /**
     * Maps an {@code InventoryRequestDto} to {@code Inventory} object.
     *
     * @param request the {@code request} to be mapped
     * @return the mapped {@code Inventory}
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Inventory inventoryCreationRequestDtoToInventory(InventoryCreationRequestDto request);

    /**
     * Updates fields in a {@code Inventory} entity from data contained in {@code InventoryRequestDto}. The method
     * changes the entity's data to the data from all non-null fields in the request dto.
     *
     * @param inventory the {@code inventory} to be updated
     * @param request the object containing new data
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInventory(@MappingTarget Inventory inventory, InventoryUpdateRequestDto request);


}
