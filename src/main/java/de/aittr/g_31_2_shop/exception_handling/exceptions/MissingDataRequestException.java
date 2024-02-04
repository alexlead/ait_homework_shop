package de.aittr.g_31_2_shop.exception_handling.exceptions;

public class MissingDataRequestException extends RuntimeException{
    public MissingDataRequestException(String message) {
        super(message);
    }
}
