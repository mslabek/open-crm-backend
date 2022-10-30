package com.application.opencrm.order.mapper;

import com.application.opencrm.order.dto.ItemDto;
import com.application.opencrm.order.model.Item;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * Mapper used to handle mapping operations on {@link Item} objects and on related dtos.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ItemMapper {

    /**
     * Maps an {@code Item} to {@code ItemDto} object.
     *
     * @param item the {@code item} to be mapped
     * @return the mapped {@code ItemDto}
     */
    ItemDto itemToItemDto(Item item);

}
