package com.application.opencrm.client.controller;

import com.application.opencrm.client.dto.AddressCreationRequestDto;
import com.application.opencrm.client.dto.AddressDto;
import com.application.opencrm.client.dto.AddressUpdateRequestDto;
import com.application.opencrm.client.model.Address;
import com.application.opencrm.client.service.AddressService;
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
 * {@code Rest controller} handling requests referring to {@link Address
 * addresses} of a specified {@link com.application.opencrm.client.model.Client client}.
 */
@Tag(name = "Client | Address", description = "Operations referring to addresses of a specified client")
@RestController
@PreAuthorize("hasRole('ROLE_SALES')")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    /**
     * Creates a new {@link Address} for a specified {@code Client} and stores it in database. The data of the new
     * address should be passed as data of a {@code AddressCreationRequestDto} in the request body.
     *
     * @param clientId the {@code id} of the client entity to which the address belongs to
     * @param request  the object containing data of {@code Address} entity to be created
     * @return the dto representing the created {@code Address} entity
     */
    @PostMapping("/address/{clientId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new address", description = "Required authorization role: ROLE_SALES", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing data of the address to be created"))
    @Parameter(name = "clientId", description = "the id of the client to which the address belongs to")
    @ApiResponse(responseCode = "201", description = "Address created successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Client with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public AddressDto createAddress(@PathVariable("clientId") @Min(value = 1, message = "Client id cannot be smaller than 1") Long clientId, @RequestBody @Valid AddressCreationRequestDto request) {
        return addressService.saveAddress(clientId, request);
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
    @PutMapping("/address/{addressId}")
    @Operation(summary = "Updates an address", description = "Required authorization role: ROLE_SALES", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing data of the address to be updated"))
    @Parameter(name = "addressId", description = "the id of the address to be updated")
    @ApiResponse(responseCode = "200", description = "Address updated successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Address with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public AddressDto updateAddress(@PathVariable("addressId") @Min(value = 1, message = "Address id cannot be smaller than 1") Long addressId, @RequestBody @Valid AddressUpdateRequestDto request) {
        return addressService.updateAddress(addressId, request);
    }

    /**
     * Delete an {@link Address} entity of a specified {@code Client} in database.
     *
     * @param addressId the {@code id} of the address entity which is to be deleted
     */
    @DeleteMapping("/address/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes an address", description = "Required authorization role: ROLE_SALES")
    @Parameter(name = "addressId", description = "the id of the address to be deleted")
    @ApiResponse(responseCode = "204", description = "Address deleted successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Address with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public void deleteAddress(@PathVariable("addressId") @Min(value = 1, message = "Address id cannot be smaller than 1") Long addressId) {
        addressService.deleteAddress(addressId);
    }

}