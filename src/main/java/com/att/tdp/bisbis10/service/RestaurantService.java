package com.att.tdp.bisbis10.service;

import org.springframework.stereotype.Service;

import com.att.tdp.bisbis10.repository.RestaurantRepository;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

}
