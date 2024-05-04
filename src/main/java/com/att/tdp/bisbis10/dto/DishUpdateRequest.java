package com.att.tdp.bisbis10.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DishUpdateRequest {
    @NotBlank(message = "description must not be empty!")
    private String description;
    @Min(value = 0, message = "The dish price can not be negative")
    @NotNull(message = "price must be provided!")
    private Float price;

    public DishUpdateRequest() {
    }


    @Override
    public String toString() {
        return "{" +
            "description='" + getDescription() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
    
}
