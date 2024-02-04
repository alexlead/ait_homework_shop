package de.aittr.g_31_2_shop.exception_handling.exceptions;

public class IllegalCustomerIdRequestedException extends RuntimeException{
    public IllegalCustomerIdRequestedException(String message) {
        super(message);
    }
}
