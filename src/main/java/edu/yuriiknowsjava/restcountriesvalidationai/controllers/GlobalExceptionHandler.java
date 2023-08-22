package edu.yuriiknowsjava.restcountriesvalidationai.controllers;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import edu.yuriiknowsjava.restcountriesvalidationai.dto.ApiErrorDto;
import edu.yuriiknowsjava.restcountriesvalidationai.exceptions.InternalServerException;
import edu.yuriiknowsjava.restcountriesvalidationai.exceptions.ServiceUnavailableException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ApiErrorDto> handleServiceUnavailableException(ServiceUnavailableException ex) {
        var status = HttpStatus.SERVICE_UNAVAILABLE;
        return new ResponseEntity<>(new ApiErrorDto(status.value(), List.of(ex.getMessage())), status);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ApiErrorDto> handleInternalServerException(InternalServerException ex) {
        var status = HttpStatus.SERVICE_UNAVAILABLE;
        return new ResponseEntity<>(new ApiErrorDto(status.value(), List.of(ex.getMessage())), status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDto> handleValidationMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var status = HttpStatus.BAD_REQUEST;
        var errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ApiErrorDto(status.value(), errors), status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorDto> handleValidationMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        var status = HttpStatus.BAD_REQUEST;
        var errors = List.of(String.format("query parameter \"%s\" cannot be %s", ex.getName(), ex.getValue()));
        return new ResponseEntity<>(new ApiErrorDto(status.value(), errors), status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorDto> handleConstraintViolationException(ConstraintViolationException ex) {
        var status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiErrorDto(status.value(), List.of(ex.getMessage())), status);
    }
}
