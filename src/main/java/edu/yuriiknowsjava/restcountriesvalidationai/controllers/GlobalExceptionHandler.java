package edu.yuriiknowsjava.restcountriesvalidationai.controllers;

import java.util.Map;

import edu.yuriiknowsjava.restcountriesvalidationai.exceptions.InternalServerException;
import edu.yuriiknowsjava.restcountriesvalidationai.exceptions.ServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Object> handleServiceUnavailableException(ServiceUnavailableException ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<Object> handleInternalServerException(InternalServerException ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
