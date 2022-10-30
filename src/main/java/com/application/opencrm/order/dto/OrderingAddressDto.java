package com.application.opencrm.order.dto;

import com.application.opencrm.order.model.OrderingAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data transfer object of a {@link OrderingAddress} for communication with processes external to the service layer.
 */
@Getter
@AllArgsConstructor
public class OrderingAddressDto {

    @Schema(description = "Name of the person under address", example = "Jan Kowalski")
    private final String personName;

    @Schema(description = "Address country name", example = "Poland")
    private final String country;

    @Schema(description = "Address city name", example = "Warsaw")
    private final String city;

    @Schema(description = "Address region name", example = "woj. mazowieckie")
    private final String region;

    @Schema(description = "Address street name", example = "ul. Mazowiecka")
    private final String street;

    @Schema(description = "Address building number", example = "5")
    private final String buildingNumber;

    @Schema(description = "Address postal code", example = "00-001")
    private final String postalCode;

}
