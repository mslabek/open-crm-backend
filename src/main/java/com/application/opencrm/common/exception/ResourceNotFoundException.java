package com.application.opencrm.common.exception;

import com.application.opencrm.infrastructure.exception.ApiLevelException;

import java.util.NoSuchElementException;

/**
 * Exception thrown when a requested resource cannot be found in the database.
 */
public class ResourceNotFoundException extends NoSuchElementException implements ApiLevelException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
