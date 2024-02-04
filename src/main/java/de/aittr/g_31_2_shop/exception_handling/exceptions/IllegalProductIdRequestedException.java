package de.aittr.g_31_2_shop.exception_handling.exceptions;

public class IllegalProductIdRequestedException extends RuntimeException{
    public IllegalProductIdRequestedException(String message) {
        super(message);
    }
}
