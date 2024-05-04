package com.att.tdp.bisbis10.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.tdp.bisbis10.dto.RatingRequest;
import com.att.tdp.bisbis10.service.RatingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }
    
    @PostMapping
    public ResponseEntity<Void> addRestaurantRating(@Valid @RequestBody RatingRequest ratingRequest) {
        ratingService.addRestaurantRating(ratingRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
