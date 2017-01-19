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

    private final int code;
    private final String message;
    private final List<FieldError> fieldErrors = new ArrayList<FieldError>();

    public ValidationError(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public final void addFieldError(final int code, final String field, final String message) {
        final FieldError error = new FieldError(code, field, message);
        fieldErrors.add(error);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public final List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", fieldErrors=" + fieldErrors +
                '}';
    }
}