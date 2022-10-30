package com.application.opencrm.client.mapper;

import com.application.opencrm.client.dto.AddressDto;
import com.application.opencrm.client.dto.ClientCreationRequestDto;
import com.application.opencrm.client.dto.ClientDto;
import com.application.opencrm.client.dto.ClientUpdateRequestDto;
import com.application.opencrm.client.model.Address;
import com.application.opencrm.client.model.Client;
import org.mapstruct.*;

/**
 * Mapper used to handle mapping and updating operations on {@link Client} objects and on related dtos.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    builder = @Builder(disableBuilder = true), uses = {AddressMapper.class, ContactMapper.class})
public interface ClientMapper {

    /**
     * Maps a {@code Client} to {@code ClientDto} object.
     *
     * @param client the {@code client} to be mapped
     * @return the mapped {@code ClientDto}
     */
    ClientDto clientToClientDto(Client client);

    /**
     * Maps a {@code ClientCreationRequestDto} to {@code Client} entity. All objects aggregated in the {@code Client}
     * are also mapped to the corresponding dtos (for example all {@link Address addresses} of the mapped client get
     * mapped to {@link AddressDto address dtos}.
     *
     * @param request the {@code client} to be mapped
     * @return the mapped {@code client} entity
     */
    @Mapping(target = "id", ignore = true)
    Client clientCreationRequestDtoToClient(ClientCreationRequestDto request);

    /**
     * Updates fields in a {@code Client} entity from data contained in {@code ClientUpdateRequestDto}. The method
     * changes the entity's data to the data from all non-null fields in the request dto.
     *
     * @param client the {@code client} to be updated
     * @param request the object containing new data
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClient(@MappingTarget Client client, ClientUpdateRequestDto request);

}
