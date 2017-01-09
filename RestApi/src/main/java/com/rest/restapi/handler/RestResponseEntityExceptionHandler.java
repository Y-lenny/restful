package com.rest.restapi.handler;


import com.rest.restapi.exception.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.InvalidMimeTypeException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * <br>统一处理服务端异常</br>
 *
 * @Class RestResponseEntityExceptionHandler
 * @Author lennylv
 * @Date 2017-1-9 20:18
 * @Version 1.0
 * @Since 1.0
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    public RestResponseEntityExceptionHandler() {
        super();
    }

    //400 请求参数异常
    @Override
    protected final ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.info("Info: Bad Request {}", ex.getMessage());
        log.debug("Debug: Bad Request {}", ex);

        final ApiError apiError = message(HttpStatus.BAD_REQUEST, ex);
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    //400 请求参数异常
    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.info("Info: Bad Request {}", ex.getMessage());
        log.debug("Debug: Bad Request {}", ex);

        final BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        final ValidationError dto = processFieldErrors(fieldErrors);

        return handleExceptionInternal(ex, dto, headers, HttpStatus.BAD_REQUEST, request);
    }

    //400 请求参数异常
    @ExceptionHandler(value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
    public final ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
        log.info("Info: Bad Request {}", ex.getLocalizedMessage());
        log.debug("Debug: Bad Request {}", ex);

        final ApiError apiError = message(HttpStatus.BAD_REQUEST, ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // 403 服务器拒绝请求
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleEverything(final AccessDeniedException ex, final WebRequest request) {
        logger.error("Error: Forbidden {}", ex);

        final ApiError apiError = message(HttpStatus.FORBIDDEN, ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    // 404 资源未找到
    @ExceptionHandler(value = {MyEntityNotFoundException.class, MyEntityNotFoundException.class, MyResourceNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        log.warn("Warn: Not Found {}", ex.getMessage());

        final ApiError apiError = message(HttpStatus.NOT_FOUND, ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 409 资源冲突
    @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class, MyConflictException.class})
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        log.warn("Warn: Conflict {}", ex.getMessage());

        final ApiError apiError = message(HttpStatus.CONFLICT, ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    // 415 不支持的资源类型
    @ExceptionHandler({InvalidMimeTypeException.class, InvalidMediaTypeException.class})
    protected ResponseEntity<Object> handleInvalidMimeTypeException(final IllegalArgumentException ex, final WebRequest request) {
        log.warn("Warn: Unsupported Media Type {}", ex.getMessage());

        final ApiError apiError = message(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
    }

    // 500 服务器异常
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handle500s(final RuntimeException ex, final WebRequest request) {
        logger.error("Error: Server Exception {}", ex);

        final ApiError apiError = message(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ValidationError processFieldErrors(final List<FieldError> fieldErrors) {
        final ValidationError dto = new ValidationError();

        for (final FieldError fieldError : fieldErrors) {
            final String localizedErrorMessage = fieldError.getDefaultMessage();
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
        return dto;
    }

    private ApiError message(final HttpStatus httpStatus, final Exception ex) {
        final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        final String devMessage = ExceptionUtils.getStackTrace(ex);

        return new ApiError(httpStatus.value(), message, devMessage);
    }

}
