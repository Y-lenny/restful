package com.rest.restapi.exception;

/**
 * <br>封装参数校验错误信息，返回给客户端</br>
 *
 * @Class FieldError
 * @Author lennylv
 * @Date 2017-1-9 20:48
 * @Version 1.0
 * @Since 1.0
 */
public class FieldError {

    private final String field;
    private final String message;

    public FieldError(final String field, final String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}