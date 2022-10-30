package com.application.opencrm.infrastructure.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Class containing information about errors that occurred during the processing of client's requests. It can be used
 * to limit the internal error information for the client (for example for security reasons) but also provide enough
 * information for the client to know what to do next.
 */
@Getter
@Setter
public class ApiError {

    private final ZonedDateTime timestamp;
    private final Integer statusCode;
    private final HttpStatus status;
    private final String message;
    private final List<String> details;
    private final String path;

    public ApiError(ZonedDateTime timestamp, Integer statusCode, HttpStatus status, String message, List<String> details, String path) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.details = details;
        this.path = path;
    }

    public ApiError(ZonedDateTime timestamp, Integer statusCode, HttpStatus status, String message, String path) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.path = path;
        this.details = null;
    }
}
