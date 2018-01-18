package com.booking.support.exceptions;

public class BeconAdditionException extends RuntimeException {

    public BeconAdditionException() {
    }

    public BeconAdditionException(String message) {
        super(message);
    }

    public BeconAdditionException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeconAdditionException(Throwable cause) {
        super(cause);
    }
}
