package com.rest.restapi.exception;

/**
 * Thrown when validation conflict error is found. Message used to describe the validation error.
 */
public class MyValidationException extends RuntimeException {

    public MyValidationException(final String message) {
        super(message);
    }

}
