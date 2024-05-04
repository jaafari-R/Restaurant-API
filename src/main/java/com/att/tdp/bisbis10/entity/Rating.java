package com.att.tdp.bisbis10.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "RestaurantRating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float rating;

    @ManyToOne
    private Restaurant restaurant;

    public Rating() {
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }

        Rating other = (Rating) obj;

        return ((rating == null && other.getRating() == null) || (rating != null && rating.equals(other.getRating()))) &&
            ((restaurant == null && other.getRestaurant() == null) || (restaurant != null && restaurant.getId() != null && restaurant.getId().equals(other.getRestaurant().getId())));
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", rating='" + getRating() + "'" +
            ", restaurant='" + getRestaurant().getId() + "'" +
            "}";
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getRating() {
        return this.rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
