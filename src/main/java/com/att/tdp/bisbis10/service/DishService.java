package com.att.tdp.bisbis10.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.tdp.bisbis10.dto.DishRequest;
import com.att.tdp.bisbis10.dto.DishUpdateRequest;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.dish.DishDoesNotBelongToRestaurantException;
import com.att.tdp.bisbis10.exception.dish.DishNotFoundException;
import com.att.tdp.bisbis10.repository.DishRepository;

import jakarta.validation.Valid;

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

    public List<Dish> getDishesByRestaurantId(Integer restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return restaurant.getDishes();
    }

    public Dish updateDish(Integer restaurantId, Integer dishId, @Valid DishUpdateRequest dishUpdateRequest) {
        Dish dish = getDishByRestaurantAndDishIds(restaurantId, dishId);

        dish.setDescription(
            dishUpdateRequest.getDescription()
        );
        dish.setPrice(
            dishUpdateRequest.getPrice()
        );

        Dish updatedDish = dishRepository.save(dish);
        return updatedDish;
    }

    public Dish getDishByRestaurantAndDishIds(Integer restaurantId, Integer dishId) {
        Optional<Dish> dishOpt = dishRepository.findById(dishId);
            System.out.println("WTF");
            if(!dishOpt.isPresent()) {
            throw new DishNotFoundException(dishId);
        }

        Dish dish = dishOpt.get();
        if(dish.getRestaurant().getId() != restaurantId) {
            throw new DishDoesNotBelongToRestaurantException(restaurantId, dishId);
        }

        return dish;
    }
}
