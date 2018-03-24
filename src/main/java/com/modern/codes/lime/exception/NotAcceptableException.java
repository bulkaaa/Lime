package com.modern.codes.lime.exception;

/**
 * exception, thrown when given data is not acceptable.
 *
 * @author jaroszk
 */
public class NotAcceptableException extends RuntimeException {

    /**
     * Constructor accepting an error message.
     *
     * @param message message
     */
    public NotAcceptableException(final String message) {
        super(message);
    }
    private static final long serialVersionUID = 5991191955244366152L;

}
