package de.aittr.g_31_2_shop.exception_handling.exceptions;

public class UnknownRequestFormatException extends RuntimeException{
    public UnknownRequestFormatException(String message) {
        super(message);
    }
}
