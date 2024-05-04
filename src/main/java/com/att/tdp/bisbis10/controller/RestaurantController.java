package com.att.tdp.bisbis10.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.att.tdp.bisbis10.dto.RestaurantRequest;
import com.att.tdp.bisbis10.dto.RestaurantUpdateCuisinesRequest;
import com.att.tdp.bisbis10.dto.RestaurantWithDishesResponse;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RestaurantService;
import com.att.tdp.bisbis10.utils.PaginationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    
    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest) {
        Restaurant newRestaurant = restaurantService.addRestaurant(restaurantRequest);
        return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants(
        @RequestParam(required = false) String cuisine,
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer pageSize
    ) {
        List<Restaurant> restaurants;
        Pageable pageable = PaginationUtils.createPagable(page, pageSize);

        if(cuisine != null) {
            restaurants = restaurantService.getRestaurantsByCuisine(cuisine, pageable);
        } 
        else {
            restaurants = restaurantService.getAllRestaurants(pageable);
        }
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantWithDishesResponse> getRestaurantById(@PathVariable Integer restaurantId) {
        RestaurantWithDishesResponse restaurant = restaurantService.getRestaurantWithDishesById(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurantCuisines(@PathVariable Integer restaurantId, @RequestBody RestaurantUpdateCuisinesRequest RestaurantUpdateCuisinesRequest) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurantCuisines(restaurantId, RestaurantUpdateCuisinesRequest);
        return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurantById(@PathVariable Integer restaurantId) {
        restaurantService.deleteRestaurantById(restaurantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
