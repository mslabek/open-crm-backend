package com.application.opencrm.client.dto;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContactCreationRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidContactCreationRequest {

    String message() default "{com.application.opencrm.client.dto.ValidContractCreationRequest.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
