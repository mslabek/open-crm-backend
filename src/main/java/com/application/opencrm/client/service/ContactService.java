package com.application.opencrm.client.service;

import com.application.opencrm.client.dto.ContactCreationRequestDto;
import com.application.opencrm.client.dto.ContactDto;
import com.application.opencrm.client.dto.ContactUpdateRequestDto;
import com.application.opencrm.client.mapper.ContactMapper;
import com.application.opencrm.client.model.Contact;
import com.application.opencrm.client.repository.ContactRepository;
import com.application.opencrm.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service handling operations on {@link Contact} objects.
 * <p>
 * This class serves as a layer between {@link ContactRepository} and controllers. Entities ({@code Contact} objects)
 * should generally not leave the service layer. Public or protected methods that return entities are intended for
 * communication with other services.
 */
@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository repository;
    private final ContactMapper mapper;
    private final ClientService clientService;

    /**
     * Creates a new {@link Contact} for a specified {@code Client} and stores it in database. The data of the new
     * contact should be passed as data of a {@code ContactCreationRequestDto} in the request body.
     *
     * @param clientId the {@code id} of the client entity to which the contact belongs to
     * @param request  the object containing data of {@code Contact} entity to be created
     * @return the dto representing the created {@code Contact} entity
     */
    @Transactional
    public ContactDto saveContact(Long clientId, ContactCreationRequestDto request) {
        Contact contact = mapper.contactCreationRequestToContact(request);
        clientService.getClientFromRepository(clientId)
                     .addContact(contact);
        return mapper.contactToContactDto(repository.save(contact));
    }

    /**
     * Updates an existing {@link Contact} entity of a specified {@code Client} in database.
     * <p>
     * The fields that need to be changed have to be set in the {@code ContactUpdateRequestDto} from the request body.
     * If a field of the request object is set to {@code null}, the corresponding field in the target entity is not
     * affected - it is not set to {@code null}. It is not possible to set a field of {@code Contact} entity to
     * {@code null} through this method.
     *
     * @param contactId the {@code id} of the contact entity to be updated
     * @param request  the object containing new data used for the update
     * @return the dto representing the updated {@code Contact} entity after the update
     */
    @Transactional
    public ContactDto updateContact(Long contactId, ContactUpdateRequestDto request) {
        Contact updatedContact = getContactFromRepository(contactId);
        mapper.updateContact(updatedContact, request);
        repository.save(updatedContact);
        return mapper.contactToContactDto(updatedContact);
    }

    /**
     * Delete a {@link Contact} entity of a specified {@code Client} in database.
     *
     * @param id the {@code id} of the contact entity which is to be deleted
     */
    @Transactional
    public void deleteContact(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Contact requested to be removed not found in the database.");
        }
    }

    /**
     * Retrieves {@code Contact} entity from repository.
     * <p> The returned entity is not intended to be used externally outside the service layer. {@code Contact} dtos
     * can be retrieved by referring to the related {@code Client} entities through methods in the
     * {@link ClientService} class
     *
     * @param id the {@code id} of the contact entity which is to be returned
     * @return the address entity
     */
    @Transactional(readOnly = true)
    public Contact getContactFromRepository(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new ResourceNotFoundException("Contact not found in the database."));
    }

}
