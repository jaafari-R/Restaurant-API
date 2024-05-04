package com.att.tdp.bisbis10.exception.dish;

import com.att.tdp.bisbis10.exception.NotFoundException;

public class DishNotFoundException extends NotFoundException{
    static private String message = "No dish found with id "; 

    public DishNotFoundException(Integer dishId) {
        super(getMessage(dishId));
    }

    public DishNotFoundException(String message) {
        super(message);
    }    

    public DishNotFoundException(String message, Throwable cause) {
        super(message, cause);

    }

    public DishNotFoundException(Throwable cause) {
        super(cause);
    }

    static private String getMessage(Integer dishId) {
        return message + dishId;
    } 
}
