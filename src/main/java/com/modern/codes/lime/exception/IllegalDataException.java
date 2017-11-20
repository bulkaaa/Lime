package com.modern.codes.lime.exception;

/**
 * thrown when the given data is not valid in the current context.
 *
 * @author jaroszk
 *
 */
public class IllegalDataException extends RuntimeException {

    private static final long serialVersionUID = 7063120525832668082L;

    /**
     * Constructor accepting an error message.
     * 
     * @param message
     *            message
     */
    public IllegalDataException(String message) {
        super(message);
    }

}
