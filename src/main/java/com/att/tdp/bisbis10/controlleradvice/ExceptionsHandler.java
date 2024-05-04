package com.att.tdp.bisbis10.controlleradvice;

import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.att.tdp.bisbis10.exception.InvalidValueException;
import com.att.tdp.bisbis10.exception.NotFoundException;
import com.att.tdp.bisbis10.exception.dish.DishDoesNotBelongToRestaurantException;

@RestControllerAdvice
public class ExceptionsHandler {
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleItemNotFound(MethodArgumentTypeMismatchException e) {
        ErrorResponse errorResponse = errorResponseWithSingleMessage(e, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleItemNotFound(HttpMessageNotReadableException e) {
        ErrorResponse errorResponse = errorResponseWithSingleMessage(e, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

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
        ErrorResponse errorResponse = errorResponseWithSingleMessage(e, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DishDoesNotBelongToRestaurantException.class)
    public ResponseEntity<ErrorResponse> handleDishDoesNotBelongToRestaurant(DishDoesNotBelongToRestaurantException e) {
        ErrorResponse errorResponse = errorResponseWithSingleMessage(e, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ErrorResponse> handleDishDoesNotBelongToRestaurant(InvalidValueException e) {
        ErrorResponse errorResponse = errorResponseWithSingleMessage(e, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



    private ErrorResponse errorResponseWithSingleMessage(Exception e, int status) {
        List<String> messages = List.of(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(status, messages);
        return errorResponse;
    }
}
