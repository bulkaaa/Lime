package com.modern.codes.lime.exception;

import org.springframework.validation.Errors;

import java.util.Locale;

/**
 * thrown when a request is invalid in the current context.
 *
 * @author jaroszk
 *
 */
public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = -3445333324593174117L;

    private final Errors errors;
    private final Locale locale;

    /**
     * Constructor accepting an error message, an Errors object and a Locale object.
     *
     * @param message
     *            message
     * @param errors
     *            Errors object
     * @param locale
     *            Locale object
     */
    public InvalidRequestException(final String message, final Errors errors, final Locale locale) {
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

}