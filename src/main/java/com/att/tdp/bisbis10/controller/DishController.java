package com.att.tdp.bisbis10.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.tdp.bisbis10.dto.DishRequest;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.service.DishService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }
    
    @PostMapping
    public ResponseEntity<Dish> addDish(
        @PathVariable Integer restaurantId,
        @Valid @RequestBody DishRequest dishRequest
    ) {
        Dish newDish = dishService.addDish(restaurantId, dishRequest);
        return new ResponseEntity<>(newDish, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getDishesByRestaurantId(
        @PathVariable Integer restaurantId
    ) {
        List<Dish> dishes = dishService.getDishesByRestaurantId(restaurantId);

        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
}
