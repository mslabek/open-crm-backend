package com.application.opencrm.client.dto;

import com.application.opencrm.client.model.Contact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 *  Data transfer object of a request to create an {@link Contact}.
 */
@Builder
@Getter
@AllArgsConstructor
@ValidContactCreationRequest(message = "Email and phone number cannot be both blank")
public class ContactCreationRequestDto {

    @Schema(description = "Contact name", example = "Personal address", required = true)
    @NotBlank(message = "Contact name cannot be blank")
    private final String name;

    @Schema(description = "Email address", example = "jankowalski@gmail.com")
    @Email(message = "Email has to be a valid email address")
    private final String email;

    @Schema(description = "Phone number", example = "+48 123 123 123")
    private final String phoneNumber;

}
