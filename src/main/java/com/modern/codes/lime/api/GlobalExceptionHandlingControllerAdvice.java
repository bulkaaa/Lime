package com.modern.codes.lime.api;

import com.modern.codes.lime.exception.ForbiddenException;
import com.modern.codes.lime.exception.NotAcceptableException;
import com.modern.codes.lime.exception.NotFoundException;
import com.modern.codes.lime.exception.OperationNotAllowedException;
import com.modern.codes.lime.exception.UnprocessableEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.ServiceUnavailableException;
import java.nio.file.AccessDeniedException;

/**
 * * Performs exception handling for all REST API controllers. This class provides exception handlers that respond to
 * possible exceptions with appropriate HTTP status codes for the client.
 *
 */
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandlingControllerAdvice.class);

    /**
     * Exception handler for <i>AccessDeniedException</i>, translating error into HTTP status code 401.
     *
     * @param ex
     *            AccessDeniedException
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public void handleAccessDeniedException(AccessDeniedException ex) {
        LOG.info(ex.getMessage());
    }

    /**
     * Exception handler for <i>ForbiddenException</i>, translating error into HTTP status code 403.
     *
     * @param ex
     *            ForbiddenException
     */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public void handleForbiddenException(ForbiddenException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>NotFoundException</i>, translating error into HTTP status code 404.
     *
     * @param ex
     *            NotFoundException
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleNotFoundException(NotFoundException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>OperationNotAllowedException</i>, translating error into HTTP status code 405.
     *
     * @param ex
     *            OperationNotAllowedException
     */
    @ExceptionHandler(OperationNotAllowedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public void handleOperationNotAllowedException(OperationNotAllowedException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>NotAcceptableException</i>, translating error into HTTP status code 406.
     *
     * @param ex
     *            NotAcceptableException
     */
    @ExceptionHandler(NotAcceptableException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public void handleNotAcceptableException(NotAcceptableException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>UnprocessableEntityException</i>, translating error into HTTP status code 422.
     *
     * @param ex
     *            UnprocessableEntityException
     */
    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public void handleUnprocessableEntityException(UnprocessableEntityException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>IllegalStateException</i>, translating error into HTTP status code 428.
     *
     * @param ex
     *            IllegalStateException
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(value = HttpStatus.PRECONDITION_REQUIRED)
    public void handleIllegalStateException(IllegalStateException ex) {
        LOG.warn(ex.getMessage());
    }

    /**
     * Exception handler for <i>ServiceUnavailableException</i>, translating error into HTTP status code 503.
     *
     * @param ex
     *            ServiceUnavailableException
     */
    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public void handleServiceUnavailableException(ServiceUnavailableException ex) {
        LOG.warn(ex.getMessage());
    }


    /**
     * General fallback exception handler, translating all not otherwise caught errors into HTTP status code 503.
     *
     * @param ex
     *            Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public void handleException(Exception ex) {
        LOG.error(ex.getMessage(), ex);
    }
}
