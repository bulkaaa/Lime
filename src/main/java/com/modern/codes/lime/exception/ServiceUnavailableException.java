package com.modern.codes.lime.exception;

/**
 *
 * @author jaroszk
 *
 */
public class ServiceUnavailableException extends RuntimeException {

    private static final long serialVersionUID = 6872580404755827065L;

    /**
     * Constructor accepting an error message.
     *
     * @param message message
     */
    public ServiceUnavailableException(String message) {
        super(message);
    }
}
