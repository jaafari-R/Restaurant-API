package com.att.tdp.bisbis10.service;

import org.springframework.stereotype.Service;

import com.att.tdp.bisbis10.repository.RatingRepository;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }
    
}
