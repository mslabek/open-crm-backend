package com.application.opencrm.demo;

import com.application.opencrm.infrastructure.exception.ApiLevelException;

public class DataGeneratorException extends RuntimeException implements ApiLevelException {

    public DataGeneratorException(String message) {
        super(message);
    }

}

