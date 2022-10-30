package com.application.opencrm.client.dto;

import com.application.opencrm.client.model.Address;
import com.application.opencrm.client.model.Client;
import com.application.opencrm.client.model.Contact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Data transfer object of a {@link Client} for communication with processes external to the service layer. The dto
 * encapsulates corresponding dtos of related {@link Address addresses} and {@link Contact contacts}.
 */
@Getter
@Builder
@AllArgsConstructor
public class ClientDto {

    @Schema(description = "Client id", example = "1")
    private final Long id;

    @Schema(description = "Client name", example = "Jan Kowalski")
    private final String name;

    @Schema(description = "Client type", example = "INDIVIDUAL", allowableValues = {"INDIVIDUAL", "ORGANISATION"})
    private final String clientType;

    @Schema(description = "Client addresses")
    private final List<AddressDto> addresses;

    @Schema(description = "Client contacts")
    private final List<ContactDto> contacts;

}
