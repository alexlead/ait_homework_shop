package de.aittr.g_31_2_shop.exception_handling;

import de.aittr.g_31_2_shop.exception_handling.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonAdvice {

    private Logger logger = LoggerFactory.getLogger(CommonAdvice.class);

    @ExceptionHandler(ThirdTestException.class)
    public ResponseEntity<Response> handleException(ThirdTestException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FourthTestException.class)
    public ResponseEntity<Response> handleException(FourthTestException e) {
        logger.error(String.format("Error: %s", e.getMessage()));
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalProductIdRequestedException.class)
    public ResponseEntity<Response> handleException(IllegalProductIdRequestedException e) {
        logger.error(String.format("Error: %s", e.getMessage()));
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnknownRequestFormatException.class)
    public ResponseEntity<Response> handleException(UnknownRequestFormatException e) {
        logger.error(String.format("Error: %s", e.getMessage()));
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingDataRequestException.class)
    public ResponseEntity<Response> handleException(MissingDataRequestException e) {
        logger.error(String.format("Error: %s", e.getMessage()));
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalCustomerIdRequestedException.class)
    public ResponseEntity<Response> handleException(IllegalCustomerIdRequestedException e) {
        logger.error(String.format("Error: %s", e.getMessage()));
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}