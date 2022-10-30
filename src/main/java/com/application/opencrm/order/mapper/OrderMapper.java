package com.application.opencrm.order.mapper;

import com.application.opencrm.order.dto.OrderDto;
import com.application.opencrm.order.model.Order;
import org.mapstruct.Builder;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * Mapper used to handle mapping operations on {@link Order} objects and on related dtos.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy =
                                                                                          CollectionMappingStrategy.ADDER_PREFERRED, builder = @Builder(disableBuilder = true), uses = {ItemMapper.class, OrderingAddressMapper.class})
public interface OrderMapper {

    /**
     * Maps an {@code Order} to {@code OrderDto} object.
     *
     * @param order the {@code order} to be mapped
     * @return the mapped {@code OrderDto}
     */
    OrderDto orderToOrderDto(Order order);

}
