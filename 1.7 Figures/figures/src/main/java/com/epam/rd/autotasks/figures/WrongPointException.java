package com.epam.rd.autotasks.figures;

public class WrongPointException extends Exception {
    public WrongPointException() {
    }

    public WrongPointException(String message) {
        super(message);
    }

    public WrongPointException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongPointException(Throwable cause) {
        super(cause);
    }
}
