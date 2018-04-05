package com.modern.codes.lime.api;

import java.nio.file.AccessDeniedException;

import javax.naming.ServiceUnavailableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.modern.codes.lime.exception.AlreadyExistsException;
import com.modern.codes.lime.exception.ForbiddenException;
import com.modern.codes.lime.exception.NotAcceptableException;
import com.modern.codes.lime.exception.NotFoundException;
import com.modern.codes.lime.exception.OperationNotAllowedException;
import com.modern.codes.lime.exception.UnprocessableEntityException;

/**
 * * Performs exception handling for all REST API controller. This class provides exception handlers that respond to
 * possible exceptions with appropriate HTTP status codes for the client.
 *
 * @author jaroszk
 */
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

    /**
     * Exception handler for <i>AccessDeniedException</i>, translating error into HTTP status code 401.
     *
     * @param ex AccessDeniedException
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleAccessDeniedException(final AccessDeniedException ex) {
        LOG.info(ex.getMessage());
    }

    /**
     * Exception handler for <i>ForbiddenException</i>, translating error into HTTP status code 403.
     *
     * @param ex ForbiddenException
     */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleForbiddenException(final ForbiddenException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>NotFoundException</i>, translating error into HTTP status code 404.
     *
     * @param ex NotFoundException
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException(final NotFoundException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>OperationNotAllowedException</i>, translating error into HTTP status code 405.
     *
     * @param ex OperationNotAllowedException
     */
    @ExceptionHandler(OperationNotAllowedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void handleOperationNotAllowedException(final OperationNotAllowedException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>NotAcceptableException</i>, translating error into HTTP status code 406.
     *
     * @param ex NotAcceptableException
     */
    @ExceptionHandler(NotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public void handleNotAcceptableException(final NotAcceptableException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>AlreadyExistsException</i>, translating error into HTTP status code 409.
     *
     * @param ex AlreadyExistsException
     */
    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public void handleAlreadyExistsException(AlreadyExistsException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>UnprocessableEntityException</i>, translating error into HTTP status code 422.
     *
     * @param ex UnprocessableEntityException
     */
    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void handleUnprocessableEntityException(final UnprocessableEntityException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>IllegalStateException</i>, translating error into HTTP status code 428.
     *
     * @param ex IllegalStateException
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
    public void handleIllegalStateException(final IllegalStateException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>ServiceUnavailableException</i>, translating error into HTTP status code 503.
     *
     * @param ex ServiceUnavailableException
     */
    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public void handleServiceUnavailableException(final ServiceUnavailableException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * General fallback exception handler, translating all not otherwise caught errors into HTTP status code 503.
     *
     * @param ex Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public void handleException(final Exception ex) {
        LOG.error(ex.getMessage(), ex);
    }

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandlingControllerAdvice.class);
}
