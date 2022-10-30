package com.application.opencrm.client.dto;

import com.application.opencrm.client.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * Data transfer object of a request to create an {@link Address}.
 */
@Builder
@Getter
@AllArgsConstructor
public class AddressCreationRequestDto {

    @Schema(description = "Name of the person under address", example = "Jan Kowalski", required = true)
    @NotBlank(message = "Person name cannot be blank")
    private final String personName;

    @Schema(description = "Address country name", example = "Poland", required = true)
    @NotBlank(message = "Country cannot be blank")
    private final String country;

    @Schema(description = "Address city name", example = "Warsaw", required = true)
    @NotBlank(message = "City cannot be blank")
    private final String city;

    @Schema(description = "Address region name", example = "woj. mazowieckie", required = true)
    @NotBlank(message = "Region cannot be blank")
    private final String region;

    @Schema(description = "Address street name", example = "ul. Mazowiecka", required = true)
    @NotBlank(message = "Street cannot be blank")
    private final String street;

    @Schema(description = "Address building number", example = "5", required = true)
    @NotBlank(message = "Building number cannot be blank")
    private final String buildingNumber;

    @Schema(description = "Address postal code", example = "00-001", required = true)
    @NotBlank(message = "Postal code cannot be blank")
    private final String postalCode;

}
