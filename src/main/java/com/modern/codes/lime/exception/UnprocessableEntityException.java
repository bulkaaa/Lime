package com.modern.codes.lime.exception;

import java.util.Locale;

import org.springframework.validation.Errors;

/**
 * thrown when an entity contains validation errors.
 *
 * @author jarosz
 */
public class UnprocessableEntityException extends RuntimeException {

    /**
     * Constructor accepting an error message, an Errors object and a Locale object.
     *
     * @param message message
     * @param errors  Errors object
     * @param locale  Locale object
     */
    public UnprocessableEntityException(final String message, final Errors errors, final Locale locale) {
        super(message);
        this.errors = errors;
        this.locale = locale;
    }

    /**
     * Returns the stored Errors object.
     *
     * @return Errors object
     */
    public Errors getErrors() {
        return errors;
    }

    /**
     * Return the stored Locale object.
     *
     * @return Locale object
     */
    public Locale getLocale() {
        return locale;
    }
    private final Errors errors;
    private final Locale locale;
    private static final long serialVersionUID = -3445333324593174117L;

}
