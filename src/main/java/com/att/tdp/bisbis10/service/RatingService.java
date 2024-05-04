package com.att.tdp.bisbis10.service;

import org.springframework.stereotype.Service;

import com.att.tdp.bisbis10.dto.RatingRequest;
import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.RatingRepository;

import jakarta.validation.Valid;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RestaurantService restaurantService;

    public RatingService(RatingRepository ratingRepository, RestaurantService restaurantService) {
        this.ratingRepository = ratingRepository;
        this.restaurantService = restaurantService;
    }

    public Rating addRestaurantRating(@Valid RatingRequest ratingRequest) {
        Restaurant restaurant = restaurantService.getRestaurantById(
            ratingRequest.getRestaurantId()
        );

        Rating rating = new Rating(
            restaurant,
            ratingRequest.getRating()
        );

        Rating newRating = ratingRepository.save(rating);
        return newRating;
    }
    
}
