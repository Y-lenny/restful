package com.rest.restapi.exception;

/**
 * <br> 封装api调用错误信息，返回给客户端 </br>
 *
 * @Class ApiError
 * @Author lennylv
 * @Date 2017-1-9 20:47
 * @Version 1.0
 * @Since 1.0
 */
public class ApiError {

    private int code;
    private String message;
    private String developerMessage;

    public ApiError(final int code, final String message, final String developerMessage) {
        super();

        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(final String developerMessage) {
        this.developerMessage = developerMessage;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", developerMessage='" + developerMessage + '\'' +
                '}';
    }
}
