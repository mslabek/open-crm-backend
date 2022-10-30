package com.application.opencrm.client.dto;

import com.application.opencrm.client.model.Address;
import com.application.opencrm.client.model.Client;
import com.application.opencrm.client.model.ClientType;
import com.application.opencrm.client.model.Contact;
import com.application.opencrm.common.validation.ValidEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Data transfer object of a request to create a {@link Client}. The dto encapsulates corresponding creation request
 * dtos of related {@link Address addresses} and {@link Contact contacts}, thus it is possible to request creation of
 * a {@code client} and all his {@code addresses} and {@code contacts} in one request.
 */
@Builder
@Getter
@AllArgsConstructor
public class ClientCreationRequestDto {

    @Schema(description = "Client name", example = "Jan Kowalski", required = true)
    @NotBlank(message = "Name cannot be blank")
    private final String name;

    @Schema(description = "Client type", example = "INDIVIDUAL", allowableValues = {"INDIVIDUAL", "ORGANISATION"}, required = true)
    @NotBlank(message = "Client type cannot be blank")
    @ValidEnum(message = "Client type must be an Enum value of ClientType", targetClassType = ClientType.class)
    private final String clientType;

    @Schema(description = "Client addresses")
    private final List<AddressCreationRequestDto> addresses;

    @Schema(description = "Client contacts")
    private final List<ContactCreationRequestDto> contacts;

}
