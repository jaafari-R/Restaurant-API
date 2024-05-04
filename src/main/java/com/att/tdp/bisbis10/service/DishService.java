package com.att.tdp.bisbis10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.tdp.bisbis10.dto.DishRequest;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.DishRepository;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantService restaurantService;
    
    @Autowired
    public DishService(DishRepository dishRepository, RestaurantService restaurantService) {
        this.dishRepository = dishRepository;
        this.restaurantService = restaurantService;
    }

    public Dish addDish(Integer restaurantId, DishRequest dishRequest) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        Dish dish = new Dish(
            dishRequest.getName(),
            dishRequest.getDescription(),
            dishRequest.getPrice(),
            restaurant
        );

        Dish newDish = dishRepository.save(dish);
        return newDish;
    }

}
