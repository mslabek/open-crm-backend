package com.application.opencrm.client.dto;

import com.application.opencrm.client.model.Client;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 *  Data transfer object of a request to update a {@link Client}.
 */
@Getter
@Builder
@AllArgsConstructor
public class ClientUpdateRequestDto {

    @Schema(description = "Client name", example = "Jan Kowalski")
    private final String name;

    @Schema(description = "Client type", example = "INDIVIDUAL", allowableValues = {"INDIVIDUAL", "ORGANISATION"})
    private final String clientType;

}
