package com.att.tdp.bisbis10.exception.pagination;

import com.att.tdp.bisbis10.exception.InvalidValueException;

public class InvalidPageSizeValueException extends InvalidValueException{
    static private String message = "PageSize must be a positive value!";

    public InvalidPageSizeValueException() {
        super(message);
    }
    
    public InvalidPageSizeValueException(String message) {
        super(message);
    }

    public InvalidPageSizeValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPageSizeValueException(Throwable cause) {
        super(cause);
    }

}
