package com.rest.restapi.exception;

public class FieldError {

    private final String field;

    private final String message;

    public FieldError(final String field, final String message) {
        this.field = field;
        this.message = message;
    }

    // API

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}