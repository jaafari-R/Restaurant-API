package com.att.tdp.bisbis10.exception.pagination;

import com.att.tdp.bisbis10.exception.InvalidValueException;

public class NegativePageValueException extends InvalidValueException {
    static private String message = "Page number can not be a negative value!";

    public NegativePageValueException() {
        super(message);
    }
    
    public NegativePageValueException(String message) {
        super(message);
    }

    public NegativePageValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativePageValueException(Throwable cause) {
        super(cause);
    }

}
