package com.application.opencrm.order.mapper;

import com.application.opencrm.client.model.Address;
import com.application.opencrm.order.dto.OrderingAddressDto;
import com.application.opencrm.order.model.OrderingAddress;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper used to handle mapping operations on {@link OrderingAddress} objects and on related dtos.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderingAddressMapper {

    /**
     * Maps an {@code OrderingAddress} to {@code OrderingAddressDto} object.
     *
     * @param orderingAddress the {@code orderingAddress} to be mapped
     * @return the mapped {@code OrderingAddressDto}
     */
    OrderingAddressDto orderingAddressToOrderingAddressDto(OrderingAddress orderingAddress);

    @Mapping(source = "id", target = "id", ignore = true)
    OrderingAddress addressToOrderingAddress(Address address);

}
