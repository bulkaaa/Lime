package com.modern.codes.lime.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.model.ValidParam;
import com.modern.codes.lime.model.ValidationError;

/**
 * Created by jaroszk on 07.01.2018.
 */
public abstract class BaseController {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    public ValidParam handleInvalidRequestException(InvalidRequestException ex) {
        LOG.warn(ex.getMessage());

        final List<ValidationError> validationErrors = new ArrayList<>();
        ex.getErrors()
          .getFieldErrors()
          .forEach(fieldError -> {
              LOG.debug("Error in field {}: {}", fieldError.getField(),
                        messageSource.getMessage(fieldError, ex.getLocale()));

              validationErrors.add(new ValidationError(fieldError.getField(), fieldError.getCode(),
                                                       messageSource.getMessage(fieldError, ex.getLocale())));
          });

        final ValidParam response = new ValidParam();
        response.setValidationErrors(validationErrors);

        return response;
    }
    @Autowired
    MessageSource messageSource;
    /**
     * Exception handler for <i>InvalidRequestException</i>, translating error into HTTP status code 422 and returning
     * validation errors in the payload of the response.
     *
     * @param ex
     * InvalidRequestException
     */

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
}
