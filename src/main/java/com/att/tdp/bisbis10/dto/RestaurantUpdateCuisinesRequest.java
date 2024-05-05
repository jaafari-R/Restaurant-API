package com.att.tdp.bisbis10.dto;

import java.util.Set;

import jakarta.validation.constraints.NotNull;

public class RestaurantUpdateCuisinesRequest {
    @NotNull(message = "cuisines must be provided!")
    private Set<String> cuisines;

    public RestaurantUpdateCuisinesRequest() {
    }

    public RestaurantUpdateCuisinesRequest(Set<String> cuisines) {
        this.cuisines = cuisines;
    }

    
    @Override
    public String toString() {
        return "{" +
            " cuisines='" + getCuisines() + "'" +
            "}";
    }

    public Set<String> getCuisines() {
        return this.cuisines;
    }

    public void setCuisines(Set<String> cuisines) {
        this.cuisines = cuisines;
    }

}
