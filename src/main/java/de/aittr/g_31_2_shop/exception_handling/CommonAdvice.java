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

    @ExceptionHandler(ProductValidationException.class)
    public ResponseEntity<ValidationResponse> handleException(ProductValidationException e) {
        e.printStackTrace();
        ValidationResponse response = new ValidationResponse(e.getMessage(),
                e.getCause().getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /*
    Домашнее задание 15
    3. Подумать, какие нештатные ситуации могут возникать при работе сервиса продуктов (для этого реализовать его до конца),
    создать для них соответствующие эксепшены (минимум 3). Эксепшены должны быть
    названы правильно, то есть название должно отражать суть причины ошибки.
    4. Выбросить эти эксепшены в нужных местах и обработать при помощи адвайса.
     */
    @ExceptionHandler(IncorrectEntityTypeException.class)
    public void handleException(IncorrectEntityTypeException e) {
        Throwable cause = e.getCause();
        String message = cause == null ?
                String.format("Error: %s", e.getMessage()) :
                String.format("Error: %s,%ncause: %s",
                        e.getMessage(), cause.getMessage());
        logger.error(message);
    }

    @ExceptionHandler(CustomerValidationException.class)
    public ResponseEntity<ValidationResponse> handleException(CustomerValidationException e) {
        e.printStackTrace();
        ValidationResponse response = new ValidationResponse(e.getMessage(),
                e.getCause().getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoActiveProductsFoundException.class)
    public ResponseEntity<Response> handleException(NoActiveProductsFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Response> handleException(ProductNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InactiveProductException.class)
    public ResponseEntity<Response> handleException(InactiveProductException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}