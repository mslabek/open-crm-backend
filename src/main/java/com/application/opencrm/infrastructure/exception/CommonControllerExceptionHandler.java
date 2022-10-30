package com.application.opencrm.infrastructure.exception;

import com.application.opencrm.common.exception.ResourceNotFoundException;
import com.application.opencrm.common.exception.ResourceNotUniqueException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class containing methods handling exceptions thrown by {@code controllers}. This mechanism allows intercepting
 * specified exceptions globally (for all controllers) thus removing the need for code duplication. All
 * {@link ApiLevelException ApiLevelExceptions} should have an {@link ExceptionHandler}.
 */
@ControllerAdvice
public class CommonControllerExceptionHandler {

    /**
     * Handles {@link ResourceNotFoundException}.
     *
     * @param ex      the exception thrown by a controller
     * @param request the current request
     * @return the ResponseEntity instance containing the error message in its body
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ApiLevelException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError error = new ApiError(ZonedDateTime.now(ZoneId.of("Z")), status.value(), status, ex.getMessage(),
        request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    /**
     * Handles {@link ResourceNotUniqueException}.
     *
     * @param ex      the exception thrown by a controller
     * @param request the current request
     * @return the ResponseEntity instance containing the error message in its body
     */
    @ExceptionHandler(ResourceNotUniqueException.class)
    public ResponseEntity<Object> handleResourceNotUnique(ApiLevelException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = new ApiError(ZonedDateTime.now(ZoneId.of("Z")), status.value(), status, ex.getMessage(),
            request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Validation failed with: " + ex.getBindingResult().getErrorCount() + " errors.";
        List<String> details = ex.getAllErrors()
                                  .stream()
                                  .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                  .collect(Collectors.toList());
        ApiError error = new ApiError(ZonedDateTime.now(ZoneId.of("Z")), status.value(), status, message,
            details, request.getServletPath());
        return generateDefaultResponse(error, status);
    }


    private ResponseEntity<Object> generateDefaultResponse(ApiError error, HttpStatus status) {
        return new ResponseEntity<>(error, new HttpHeaders(), status);
    }

}
