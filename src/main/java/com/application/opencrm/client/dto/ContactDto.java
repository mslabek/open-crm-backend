package com.application.opencrm.client.dto;

import com.application.opencrm.client.model.Contact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data transfer object of a {@link Contact} for communication with processes external to the service layer.
 */
@Getter
@AllArgsConstructor
public class ContactDto {

    @Schema(description = "Contact id", example = "1")
    private final Long id;

    @Schema(description = "Contact name", example = "Personal address")
    private final String name;

    @Schema(description = "Email address", example = "jankowalski@gmail.com")
    private final String email;

    @Schema(description = "Phone number", example = "+48 123 123 123")
    private final String phoneNumber;

}
