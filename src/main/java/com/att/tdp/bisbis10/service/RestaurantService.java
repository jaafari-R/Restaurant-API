package com.att.tdp.bisbis10.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public List<Restaurant> getAllRestaurants(Pageable pageable) {
        List<Restaurant> restaurants;
        if(pageable == null) {
            restaurants = restaurantRepository.findAll();
        }
        else {
            Page<Restaurant> restaurantsPage = restaurantRepository.findAll(pageable);
            restaurants = restaurantsPage.getContent();
        }
        return restaurants;
	}

    public List<Restaurant> getRestaurantsByCuisine(String cuisine, Pageable pageable) {
        List<Restaurant> restaurants;
        if(pageable == null) {
            restaurants = restaurantRepository.findByCuisinesContaining(cuisine);
        }
        else {
            Page<Restaurant> restaurantsPage = restaurantRepository.findByCuisinesContaining(cuisine, pageable);
            restaurants = restaurantsPage.getContent();
        }

        return restaurants;
    }

    public RestaurantWithDishesResponse getRestaurantWithDishesById(Integer restaurantId) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        RestaurantWithDishesResponse restaurantWithDishesResponse = new RestaurantWithDishesResponse(restaurant);
        return restaurantWithDishesResponse;
    }

    public Restaurant getRestaurantById(Integer restaurantId) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        if(!restaurantOpt.isPresent()) {
            throw new RestaurantNotFoundException(restaurantId);
        }

        return restaurantOpt.get();
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
