package com.rest.restapi.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>封装校验错误信息，返回给客户端</br>
 *
 * @Class ValidationError
 * @Author lennylv
 * @Date 2017-1-9 20:49
 * @Version 1.0
 * @Since 1.0
 */
public class ValidationError {

    private final List<FieldError> fieldErrors = new ArrayList<FieldError>();

    public ValidationError() {
        super();
    }

    public final void addFieldError(final String path, final String message) {
        final FieldError error = new FieldError(path, message);
        fieldErrors.add(error);
    }

    public final List<FieldError> getFieldErrors() {
        return fieldErrors;
    }


    @Override
    public final String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ValidationError [fieldErrors=").append(fieldErrors).append("]");
        return builder.toString();
    }

}