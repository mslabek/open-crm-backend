package com.application.opencrm.client.controller;

import com.application.opencrm.client.dto.ContactCreationRequestDto;
import com.application.opencrm.client.dto.ContactDto;
import com.application.opencrm.client.dto.ContactUpdateRequestDto;
import com.application.opencrm.client.model.Client;
import com.application.opencrm.client.model.Contact;
import com.application.opencrm.client.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * {@code Rest controller} handling requests referring to {@link Contact contacts} of a specified {@link Client client}.
 */
@Tag(name = "Client | Contact", description = "Operations referring to contacts")
@RestController
@PreAuthorize("hasRole('ROLE_SALES')")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    /**
     * Creates a new {@link Contact} for a specified {@code Client} and stores it in database. The data of the new
     * contact should be passed as data of a {@code ContactCreationRequestDto} in the request body.
     *
     * @param clientId the {@code id} of the client entity to which the contact belongs to
     * @param request  the object containing data of {@code Contact} entity to be created
     * @return the dto representing the created {@code Contact} entity
     */
    @PostMapping("/client/{clientId}/contact")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new contact", description = "Required authorization role: ROLE_SALES", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing data of the contact to be created"))
    @Parameter(name = "clientId", description = "the id of the client to which the contact belongs to")
    @ApiResponse(responseCode = "201", description = "Contact created successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Client with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public ContactDto createContact(
            @PathVariable("clientId") @Min(value = 1, message = "Contact id cannot be smaller than 1") Long clientId, @RequestBody @Valid ContactCreationRequestDto request) {
        return contactService.saveContact(clientId, request);
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
     * @param request   the object containing new data used for the update
     * @return the dto representing the updated {@code Contact} entity after the update
     */
    @PutMapping("contact/{contactId}")
    @Operation(summary = "Updates an contact", description = "Required authorization role: ROLE_SALES", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing data of the contact to be updated"))
    @Parameter(name = "contactId", description = "the id of the contact to be updated")
    @ApiResponse(responseCode = "200", description = "Contact updated successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Contact with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public ContactDto updateContact(@PathVariable("contactId") @Min(value = 1, message = "Contact id cannot be smaller than 1") Long contactId, @RequestBody @Valid ContactUpdateRequestDto request) {
        return contactService.updateContact(contactId, request);
    }

    /**
     * Delete a {@link Contact} entity of a specified {@code Client} in database.
     *
     * @param id the {@code id} of the contact entity which is to be deleted
     */
    @DeleteMapping("contact/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes a contact", description = "Required authorization role: ROLE_SALES")
    @Parameter(name = "contactId", description = "the id of the contact to be deleted")
    @ApiResponse(responseCode = "204", description = "Contact deleted successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Contact with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public void deleteContact(@PathVariable("contactId") @Min(value = 1, message = "Contact id cannot be smaller than 1") Long id) {
        contactService.deleteContact(id);
    }

}
