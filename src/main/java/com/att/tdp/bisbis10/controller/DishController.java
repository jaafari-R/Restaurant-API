package com.att.tdp.bisbis10.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.tdp.bisbis10.service.DishService;

@RestController
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }
    
}
