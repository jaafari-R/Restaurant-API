package com.att.tdp.bisbis10.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RatingRequest {
    @NotNull(message = "restaurantId must be provided!")
    Integer restaurantId;
    @NotNull(message = "rating must be provided")
    @Min(value = 1, message = "rating has to be at least 1!")
    @Max(value = 5, message = "rating has to be at most 5!")
    Float rating;    

    public RatingRequest() {
    }

    public RatingRequest(Integer restaurantId, Float rating) {
        this.restaurantId = restaurantId;
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "{" +
            " restaurantId='" + getRestaurantId() + "'" +
            ", rating='" + getRating() + "'" +
            "}";
    }

    public Integer getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Float getRating() {
        return this.rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

}
