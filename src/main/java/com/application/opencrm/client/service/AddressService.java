package com.application.opencrm.client.service;

import com.application.opencrm.client.dto.AddressCreationRequestDto;
import com.application.opencrm.client.dto.AddressDto;
import com.application.opencrm.client.dto.AddressUpdateRequestDto;
import com.application.opencrm.client.mapper.AddressMapper;
import com.application.opencrm.client.model.Address;
import com.application.opencrm.client.repository.AddressRepository;
import com.application.opencrm.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service handling operations on {@link Address} objects.
 * <p>
 * This class serves as a layer between {@link AddressRepository} and controllers. Entities ({@code Address} objects)
 * should generally not leave the service layer. Public or protected methods that return entities are intended for
 * communication with other services.
 */
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;
    private final AddressMapper mapper;
    private final ClientService clientService;

    /**
     * Creates a new {@link Address} for a specified {@code Client} and stores it in database. The data of the new
     * address should be passed as data of a {@code AddressCreationRequestDto} in the request body.
     *
     * @param clientId the {@code id} of the client entity to which the address belongs to
     * @param request  the object containing data of {@code Address} entity to be created
     * @return the dto representing the created {@code Address} entity
     */
    @Transactional
    public AddressDto saveAddress(Long clientId, AddressCreationRequestDto request) {
        Address address = mapper.addressCreationRequestDtoToAddress(request);
        clientService.getClientFromRepository(clientId)
                     .addAddress(address);
        return mapper.addressToAddressDto(repository.save(address));
    }

    /**
     * Updates an existing {@link Address} entity of a specified {@code Client} in database.
     * <p>
     * The fields that need to be changed have to be set in the {@code AddressUpdateRequestDto} from the request body.
     * If a field of the request object is set to {@code null}, the corresponding field in the target entity is not
     * affected - it is not set to {@code null}. It is not possible to set a field of {@code Address} entity to
     * {@code null} through this method.
     *
     * @param addressId the {@code id} of the address entity to be updated
     * @param request   the object containing new data used for the update
     * @return the dto representing the updated {@code Address} entity after the update
     */
    @Transactional
    public AddressDto updateAddress(Long addressId, AddressUpdateRequestDto request) {
        Address updatedAddress = getAddressFromRepository(addressId);
        mapper.updateAddress(updatedAddress, request);
        repository.save(updatedAddress);
        return mapper.addressToAddressDto(updatedAddress);
    }

    /**
     * Delete an {@code address} entity of a specified {@code Client} in database.
     *
     * @param id the {@code id} of the address entity which is to be deleted
     */
    @Transactional
    public void deleteAddress(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Address requested to be removed not found in the database.");
        }
    }

    /**
     * Retrieves {@code Address} entity from repository.
     * <p> The returned entity is not intended to be used externally outside the service layer. {@code Address} dtos
     * can be retrieved by referring to the related {@code Client} entities through methods in the
     * {@link ClientService} class
     *
     * @param id the {@code id} of the address entity which is to be returned
     * @return the address entity
     */
    @Transactional(readOnly = true)
    public Address getAddressFromRepository(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new ResourceNotFoundException("Address not found in the database."));
    }

}
