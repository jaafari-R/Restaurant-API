package com.att.tdp.bisbis10.dto;

import java.util.List;
import java.util.Set;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;

public class RestaurantWithDishesResponse {
    private Integer id;
    private String name;
    private Boolean isKosher;
    private float averageRating;
    private Set<String> cuisines;
    private List<Dish> dishes;

    public RestaurantWithDishesResponse(Restaurant restaurant) {
        id = restaurant.getId();
        name = restaurant.getName();
        isKosher = restaurant.getIsKosher();
        averageRating = restaurant.getAverageRating();
        cuisines = restaurant.getCuisines();
        dishes = restaurant.getDishes();
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", isKosher='" + isIsKosher() + "'" +
            ", averageRating='" + getAverageRating() + "'" +
            ", cuisines='" + getCuisines() + "'" +
            ", dishes='" + getDishes() + "'" +
            "}";
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isIsKosher() {
        return this.isKosher;
    }

    public Boolean getIsKosher() {
        return this.isKosher;
    }

    public void setIsKosher(Boolean isKosher) {
        this.isKosher = isKosher;
    }

    public float getAverageRating() {
        return this.averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public Set<String> getCuisines() {
        return this.cuisines;
    }

    public void setCuisines(Set<String> cuisines) {
        this.cuisines = cuisines;
    }

    public List<Dish> getDishes() {
        return this.dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

}
