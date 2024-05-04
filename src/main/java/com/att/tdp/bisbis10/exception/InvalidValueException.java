package com.att.tdp.bisbis10.exception;

public class InvalidValueException extends RuntimeException {
    
    public InvalidValueException(String message) {
        super(message);
    }
    
    public InvalidValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidValueException(Throwable cause) {
        super(cause);
    }
}
