package com.modern.codes.lime.exception;

/**
 * thrown when eg. an authentication attempt is not allowed with the given credentials (not details returned).
 *
 * @author jaroszk
 */
public class ForbiddenException extends RuntimeException {

    /**
     * Constructor accepting an error message.
     *
     * @param message message
     */
    public ForbiddenException(final String message) {
        super(message);
    }
    private static final long serialVersionUID = 2365475115651958346L;

}
