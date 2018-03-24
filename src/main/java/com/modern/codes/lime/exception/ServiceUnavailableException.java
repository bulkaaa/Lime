package com.modern.codes.lime.exception;

/**
 * @author jaroszk
 */
public class ServiceUnavailableException extends RuntimeException {

    /**
     * Constructor accepting an error message.
     *
     * @param message message
     */
    public ServiceUnavailableException(final String message) {
        super(message);
    }
    private static final long serialVersionUID = 6872580404755827065L;
}
