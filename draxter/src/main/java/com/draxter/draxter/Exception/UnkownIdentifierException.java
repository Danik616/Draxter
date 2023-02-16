package com.draxter.draxter.Exception;

public class UnkownIdentifierException extends Exception {

    public UnkownIdentifierException() {
        super();
    }

    public UnkownIdentifierException(String message) {
        super(message);
    }

    public UnkownIdentifierException(String message, Throwable cause) {
        super(message, cause);
    }
}