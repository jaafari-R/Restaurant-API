package com.att.tdp.bisbis10.exception.restaurant;

import com.att.tdp.bisbis10.exception.NotFoundException;

public class RestaurantNotFoundException  extends NotFoundException{
    static String message = "No restaurant found with id ";
    
    public RestaurantNotFoundException(Integer restaurantId) {
        super(getMessage(restaurantId));
    }
    
    public RestaurantNotFoundException(Integer restaurantId, Throwable cause) {
        super(getMessage(restaurantId), cause);
    }

    public RestaurantNotFoundException(Throwable cause) {
        super(cause);
    }
    
    static private String getMessage(Integer restaurantId) {
        return message + restaurantId;
    }
}
