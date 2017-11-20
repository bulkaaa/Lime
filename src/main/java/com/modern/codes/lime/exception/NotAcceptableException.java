package com.modern.codes.lime.exception;

/**
 * exception, thrown when given data is not acceptable.
 *
 * @author jaroszk
 *
 */
public class NotAcceptableException extends RuntimeException {

    private static final long serialVersionUID = 5991191955244366152L;

    /**
     * Constructor accepting an error message.
     *
     * @param message
     *            message
     */
    public NotAcceptableException(String message) {
        super(message);
    }

}
