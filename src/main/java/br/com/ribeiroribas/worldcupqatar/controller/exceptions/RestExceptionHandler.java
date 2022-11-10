package br.com.ribeiroribas.worldcupqatar.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataScrapingErrorException.class)
    private ResponseEntity<Object> handleDataScrapingError(DataScrapingErrorException ex) {
        return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
