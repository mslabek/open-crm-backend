package com.application.opencrm.client.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactCreationRequestValidator implements ConstraintValidator<ValidContactCreationRequest, ContactCreationRequestDto> {

    @Override
    public boolean isValid(ContactCreationRequestDto value, ConstraintValidatorContext context) {
        return !(value.getEmail().isBlank() && value.getPhoneNumber().isBlank());
    }

}
