package com.application.opencrm.client.mapper;

import com.application.opencrm.client.dto.ContactCreationRequestDto;
import com.application.opencrm.client.dto.ContactDto;
import com.application.opencrm.client.dto.ContactUpdateRequestDto;
import com.application.opencrm.client.model.Contact;
import org.mapstruct.*;

/**
 * Mapper used to handle mapping and updating operations on {@link Contact} objects and on related dtos.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContactMapper {

    /**
     * Maps a {@code Contact} to {@code ContactDto} object.
     *
     * @param contact the {@code contact} to be mapped
     * @return the mapped {@code ContactDto}
     */
    ContactDto contactToContactDto(Contact contact);

    /**
     * Maps a {@code ContactCreationRequestDto} to {@code Contact} entity.
     *
     * @param request the {@code contact} to be mapped
     * @return the mapped {@code contact} entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    Contact contactCreationRequestToContact(ContactCreationRequestDto request);

    /**
     * Updates fields in a {@code Contact} entity from data contained in {@code ContactUpdateRequestDto}. The method
     * changes the entity's data to the data from all non-null fields in the request dto.
     *
     * @param contact the {@code contact} to be updated
     * @param request the object containing new data
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateContact(@MappingTarget Contact contact, ContactUpdateRequestDto request);

}
