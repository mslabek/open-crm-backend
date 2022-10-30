package com.application.opencrm.client.mapper;

import com.application.opencrm.client.dto.AddressCreationRequestDto;
import com.application.opencrm.client.dto.AddressDto;
import com.application.opencrm.client.dto.AddressUpdateRequestDto;
import com.application.opencrm.client.model.Address;
import org.mapstruct.*;

/**
 * Mapper used to handle mapping and updating operations on {@link Address} objects and on related dtos.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AddressMapper {

    /**
     * Maps a {@code Address} to {@code AddressDto} object.
     *
     * @param address the {@code address} to be mapped
     * @return the mapped {@code AddressDto}
     */
    AddressDto addressToAddressDto(Address address);

    /**
     * Maps a {@code AddressCreationRequestDto} to {@code Address} entity.
     *
     * @param request the {@code address} to be mapped
     * @return the mapped {@code address} entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    Address addressCreationRequestDtoToAddress(AddressCreationRequestDto request);

    /**
     * Updates fields in a {@code Address} entity from data contained in {@code AddressUpdateRequestDto}. The method
     * changes the entity's data to the data from all non-null fields in the request dto.
     *
     * @param address the {@code address} to be updated
     * @param request the object containing new data
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddress(@MappingTarget Address address, AddressUpdateRequestDto request);
}
