package com.application.opencrm.client.controller;

import com.application.opencrm.client.dto.ClientCreationRequestDto;
import com.application.opencrm.client.dto.ClientDto;
import com.application.opencrm.client.dto.ClientUpdateRequestDto;
import com.application.opencrm.client.model.Client;
import com.application.opencrm.client.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * {@code Rest controller} handling requests referring to {@link Client clients}.
 */
@Tag(name = "Client", description = "Operations referring to clients")
@SecurityRequirement(name = "cookieAuth")
@RestController
@RequestMapping("/client")
@PreAuthorize("hasRole('ROLE_SALES')")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    /**
     * Retrieves all {@link Client} entities from repository.
     *
     * @return the list of dtos representing all found {@code Client} entities
     */
    @GetMapping
    @Operation(summary = "Retrieves all clients", description = "Required authorization role: ROLE_SALES")
    @ApiResponse(responseCode = "200", description = "Clients retrieved successfully")
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    /**
     * Retrieves a {@link Client} entity with specified {@code id} from repository.
     *
     * @param id the id of the searched client
     * @return the dto representing the found {@code Client} entity
     */
    @GetMapping("{id}")
    @Operation(summary = "Retrieves a client", description = "Required authorization role: ROLE_SALES")
    @Parameter(name = "id", description = "the id of the client to which be retrieved")
    @ApiResponse(responseCode = "200", description = "Client retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Client with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public ClientDto getClient(@PathVariable @Min(value = 1, message = "Client id cannot be smaller than 1") Long id) {
        return clientService.getClient(id);
    }

    /**
     * Creates a new {@link Client} and stores it in database. The data of the new client should be passed as data of a
     * {@code ClientCreationRequestDto} in the request body.
     *
     * @param request the object containing data of {@code Client} entity to be created
     * @return the dto representing the created {@code Client} entity
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new client", description = "Required authorization role: ROLE_SALES", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing data of the client to be created"))
    @ApiResponse(responseCode = "201", description = "Client created successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public ClientDto createClient(@RequestBody @Valid ClientCreationRequestDto request) {
        return clientService.saveClient(request);
    }

    /**
     * Updates an existing {@link Client} entity in database.
     * <p>
     * The fields that need to be changed have to be set in the {@code ClientUpdateRequestDto} from the request body. If
     * a field of the request object is set to {@code null}, the corresponding field in the target entity is not
     * affected - it is not set to {@code null}. It is not possible to set a field of {@code Client} entity to
     * {@code null} through this method.
     *
     * @param id      the {@code id} of the client entity to be updated
     * @param request the object containing new data used for the update
     * @return the dto representing the updated {@code Client} entity after the update
     */
    @PutMapping("{id}")
    @Operation(summary = "Updates a client", description = "Required authorization role: ROLE_SALES", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing data of the client to be updated"))
    @Parameter(name = "id", description = "the id of the client to which be updated")
    @ApiResponse(responseCode = "200", description = "Client updated successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Client with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public ClientDto updateClient(@PathVariable @Min(value = 1, message = "Client id cannot be smaller than 1") Long id, @RequestBody @Valid ClientUpdateRequestDto request) {
        return clientService.updateClient(id, request);
    }

    /**
     * Delete a {@link Client} entity with specified {@code id} in database.
     *
     * @param id the {@code id} of the client entity which is to be deleted
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes a client", description = "Required authorization role: ROLE_SALES")
    @Parameter(name = "id", description = "the id of the client to which be updated")
    @ApiResponse(responseCode = "204", description = "Client deleted successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Client with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public void deleteClient(@PathVariable @Min(value = 1, message = "Client id cannot be smaller than 1") Long id) {
        clientService.deleteClient(id);
    }

}
