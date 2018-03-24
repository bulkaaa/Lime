package com.modern.codes.lime.exception;

/**
 * thrown when a contract management API call is not allowed.
 *
 * @author jaroszk
 */
public class OperationNotAllowedException extends RuntimeException {

    /**
     * Constructor accepting an error message.
     *
     * @param message message
     */
    public OperationNotAllowedException(final String message) {
        super(message);
    }
    private static final long serialVersionUID = -7174220178014196107L;

}
