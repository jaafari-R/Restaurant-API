package com.att.tdp.bisbis10.controlleradvice;

import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.att.tdp.bisbis10.exception.NotFoundException;

@RestControllerAdvice
public class ExceptionsHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidArgument(MethodArgumentNotValidException e) {
        List<String> messages = e.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(HttpsURLConnection.HTTP_BAD_REQUEST, messages);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleItemNotFound(NotFoundException e) {
        List<String> messages = getSingleMessageList(e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), messages);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    private List<String> getSingleMessageList(String message) {
        return List.of(message);
    }
}
