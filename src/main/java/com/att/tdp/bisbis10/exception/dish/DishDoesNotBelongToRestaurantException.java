package com.att.tdp.bisbis10.exception.dish;

public class DishDoesNotBelongToRestaurantException extends RuntimeException {
    static String message1 = "Dish with id ";
    static String message2 = " does not belong to the restaurant with id ";

    public DishDoesNotBelongToRestaurantException(Integer restaurantId, Integer dishId) {
        super(getMessage(restaurantId, dishId));
    }

    public DishDoesNotBelongToRestaurantException(String message) {
        super(message);
    }    

    public DishDoesNotBelongToRestaurantException(String message, Throwable cause) {
        super(message, cause);

    }

    public DishDoesNotBelongToRestaurantException(Throwable cause) {
        super(cause);

    }

    static private String getMessage(Integer restaurantId, Integer dishId) {
        return message1 + dishId + message2 + restaurantId;
    } 
}
