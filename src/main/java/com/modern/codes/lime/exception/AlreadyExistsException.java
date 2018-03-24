package com.modern.codes.lime.exception;

/**
 * thrown when an entity with at leas one unique field already exists .
 *
 * @author jaroszk
 */
public class AlreadyExistsException extends RuntimeException {

    /**
     * Constructor accepting an error message.
     *
     * @param message message
     */
    public AlreadyExistsException(String message) {
        super(message);
    }
    private static final long serialVersionUID = 6297471955828454690L;

}