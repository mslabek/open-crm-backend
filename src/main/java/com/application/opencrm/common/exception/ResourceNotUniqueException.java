package com.application.opencrm.common.exception;

import com.application.opencrm.infrastructure.exception.ApiLevelException;
import com.application.opencrm.user.model.User;

/**
 * Exception thrown when a unique constraint in the database was violated. For example: adding a {@link User} with a
 * username which is already taken.
 */
public class ResourceNotUniqueException extends RuntimeException implements ApiLevelException {

    public ResourceNotUniqueException(String message) {
        super(message);
    }

}
