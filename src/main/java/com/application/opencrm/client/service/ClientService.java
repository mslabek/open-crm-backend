package com.application.opencrm.client.service;

import com.application.opencrm.client.dto.ClientCreationRequestDto;
import com.application.opencrm.client.dto.ClientDto;
import com.application.opencrm.client.dto.ClientUpdateRequestDto;
import com.application.opencrm.client.mapper.ClientMapper;
import com.application.opencrm.client.model.Client;
import com.application.opencrm.client.repository.ClientRepository;
import com.application.opencrm.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service handling operations on {@link Client} objects.
 * <p>
 * This class serves as a layer between {@link ClientRepository} and controllers. Entities ({@code Client} objects)
 * should generally not leave the service layer. Public or protected methods that return entities are intended for
 * communication with other services.
 */
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;

    /**
     * Retrieves all {@link Client} entities from repository.
     *
     * @return the list of dtos representing all found {@code Client} entities
     */
    @Transactional(readOnly = true)
    public List<ClientDto> getAllClients() {
        return repository.findAll()
                         .stream()
                         .map(mapper::clientToClientDto)
                         .collect(Collectors.toList());
    }

    /**
     * Retrieves a {@link Client} entity with specified {@code id} from repository.
     *
     * @param id the id of the searched client
     * @return the dto representing the found {@code Client} entity
     */
    @Transactional(readOnly = true)
    public ClientDto getClient(Long id) {
        return mapper.clientToClientDto(getClientFromRepository(id));
    }

    /**
     * Creates a new {@link Client} and stores it in database. The data of the new client should be passed as data of a {@code ClientCreationRequestDto} in the request body.
     *
     * @param request the object containing data of {@code Client} entity to be created
     * @return the dto representing the created {@code Client} entity
     */
    @Transactional
    public ClientDto saveClient(ClientCreationRequestDto request) {
        Client savedClient = repository.save(mapper.clientCreationRequestDtoToClient(request));
        return mapper.clientToClientDto(savedClient);
    }

    /**
     * Updates an existing {@link Client} entity in database.
     * <p>
     * The fields that need to be changed have to be set in the {@code ClientUpdateRequestDto} from the request body.
     * If a field of the request object is set to {@code null}, the corresponding field in the target entity is not
     * affected - it is not set to {@code null}. It is not possible to set a field of {@code Client} entity to
     * {@code null} through this method.
     *
     * @param id      the {@code id} of the client entity to be updated
     * @param request the object containing new data used for the update
     * @return the dto representing the updated {@code Client} entity after the update
     */
    @Transactional
    public ClientDto updateClient(Long id, ClientUpdateRequestDto request) {
        Client updatedClient = getClientFromRepository(id);
        mapper.updateClient(updatedClient, request);
        repository.save(updatedClient);
        return mapper.clientToClientDto(updatedClient);
    }

    /**
     * Delete a {@link Client} entity with specified {@code id} in database.
     *
     * @param id the {@code id} of the client entity which is to be deleted
     */
    @Transactional
    public void deleteClient(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Client requested to be removed not found in the database.");
        }
    }

    /**
     * Retrieves {@code Client} entity from repository.
     * <p>
     * The returned entity is not intended to be used externally outside the service layer.
     *
     * @param id the {@code id} of the client entity which is to be returned
     * @return the client entity
     */
    @Transactional(readOnly = true)
    public Client getClientFromRepository(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new ResourceNotFoundException("Client not found in the database."));
    }

}
