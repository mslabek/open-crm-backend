package com.application.opencrm.common.exception;

import com.application.opencrm.infrastructure.exception.ApiLevelException;

public class InternalServerException extends RuntimeException implements ApiLevelException {

    public InternalServerException(String message) {
        super(message);
    }

}
