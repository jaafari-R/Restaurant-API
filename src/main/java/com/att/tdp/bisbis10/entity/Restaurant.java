package com.att.tdp.bisbis10.entity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.att.tdp.bisbis10.util.MathUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Transient
    private float averageRating;

    private Boolean isKosher;

    @ElementCollection
    private Set<String> cuisines;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Rating> ratings;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(String name, Boolean isKosher, Set<String> cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }

        Restaurant other = (Restaurant) obj;

        return ((this.name == null && other.getName() == null) || this.name != null && this.name.equals(other.getName())) &&
        (Float.compare(this.averageRating, other.getAverageRating()) == 0) &&
        ((this.isKosher == null && other.getIsKosher() == null) || this.isKosher != null && this.isKosher.equals(other.getIsKosher())) &&
        ((this.cuisines == null && other.getCuisines() == null) || this.cuisines != null && this.cuisines.equals(other.getCuisines()));
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", averageRating='" + getAverageRating() + "'" +
            ", isKosher='" + isIsKosher() + "'" +
            ", cuisines='" + getCuisines() + "'" +
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

    public float getAverageRating() {
        if(ratings != null) {
            List<Float> ratingsVals = ratings.stream()
                .map(Rating::getRating)
                .collect(Collectors.toList());
            float rating = MathUtils.lastTwoDecimalsFloat(
                MathUtils.average(ratingsVals)
            );
            return rating;
        }
        return 0;
    }

    public boolean isIsKosher() {
        return this.isKosher;
    }

    public Boolean getIsKosher() {
        return this.isKosher;
    }

    public void setIsKosher(Boolean isKosher) {
        this.isKosher = isKosher;
    }

    public Set<String> getCuisines() {
        return this.cuisines;
    }

    public void setCuisines(Set<String> cuisines) {
        this.cuisines = cuisines;
    }

    public List<Rating> getRatings() {
        return this.ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public List<Dish> getDishes() {
        return this.dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

}
