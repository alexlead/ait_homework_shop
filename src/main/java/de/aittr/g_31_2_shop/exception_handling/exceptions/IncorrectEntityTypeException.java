package de.aittr.g_31_2_shop.exception_handling.exceptions;

public class IncorrectEntityTypeException extends RuntimeException {

    public IncorrectEntityTypeException(String message) {
        super(message);
    }

    public IncorrectEntityTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}