package com.att.tdp.bisbis10.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.att.tdp.bisbis10.dto.RestaurantRequest;
import com.att.tdp.bisbis10.dto.RestaurantUpdateCuisinesRequest;
import com.att.tdp.bisbis10.dto.RestaurantWithDishesResponse;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.restaurant.RestaurantNotFoundException;
import com.att.tdp.bisbis10.repository.RestaurantRepository;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant addRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant(
            restaurantRequest.getName(),
            restaurantRequest.getIsKosher(),
            restaurantRequest.getCuisines()
        );

        Restaurant newRestaurant = restaurantRepository.save(restaurant);
        return newRestaurant;
    }

	public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
	}

    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        List<Restaurant> restaurants = restaurantRepository.findByCuisinesContaining(cuisine);
        return restaurants;
    }

    public RestaurantWithDishesResponse getRestaurantById(Integer restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(!restaurant.isPresent()) {
            throw new RestaurantNotFoundException(restaurantId);
        }

        RestaurantWithDishesResponse restaurantWithDishesResponse = new RestaurantWithDishesResponse(restaurant.get());
        return restaurantWithDishesResponse;
    }

    public Restaurant updateRestaurantCuisines(Integer restaurantId, RestaurantUpdateCuisinesRequest restaurantUpdateCuisinesRequest) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        if(!restaurantOpt.isPresent()) {
            throw new RestaurantNotFoundException(restaurantId);
        }

        Restaurant restaurant = restaurantOpt.get();
        restaurant.setCuisines(
            restaurantUpdateCuisinesRequest.getCuisines()
        );
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public void deleteRestaurantById(Integer restaurantId) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId); 
        if(!restaurantOpt.isPresent()) {
            throw new RestaurantNotFoundException(restaurantId);
        }

        restaurantRepository.deleteById(restaurantId);
    }

}
