package com.rest.restapi.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {

    private final List<FieldError> fieldErrors = new ArrayList<FieldError>();

    public ValidationError() {
        super();
    }

    //

    public final void addFieldError(final String path, final String message) {
        final FieldError error = new FieldError(path, message);
        fieldErrors.add(error);
    }

    public final List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    //

    @Override
    public final String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ValidationError [fieldErrors=").append(fieldErrors).append("]");
        return builder.toString();
    }

}