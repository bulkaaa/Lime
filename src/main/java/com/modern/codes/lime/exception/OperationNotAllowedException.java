package com.modern.codes.lime.exception;

/**
 * thrown when a contract management API call is not allowed (eg. the subscription is inactive).
 *
 *
 */
public class OperationNotAllowedException extends RuntimeException {

    private static final long serialVersionUID = -7174220178014196107L;

    /**
     * Constructor accepting an error message.
     * 
     * @param message
     *            message
     */
    public OperationNotAllowedException(String message) {
        super(message);
    }

}
