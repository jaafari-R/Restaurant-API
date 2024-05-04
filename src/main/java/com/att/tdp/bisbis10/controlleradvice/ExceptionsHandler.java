package com.att.tdp.bisbis10.controlleradvice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.att.tdp.bisbis10.exception.NotFoundException;

@RestControllerAdvice
public class ExceptionsHandler {
    
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
