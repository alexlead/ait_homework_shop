package de.aittr.g_31_2_shop.exception_handling.exceptions;

public class NoActiveProductsFoundException extends RuntimeException {

    public NoActiveProductsFoundException(String message) {
        super(message);
    }
}