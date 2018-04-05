package com.modern.codes.lime.exception;

/**
 * thrown when an entity is not found where it is expected.
 *
 * @author jaroszk
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructor accepting an error message.
     *
     * @param message message
     */
    public NotFoundException(final String message) {
        super(message);
    }

    private static final long serialVersionUID = 6297471955828454690L;

}
