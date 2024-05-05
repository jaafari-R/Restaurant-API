package com.att.tdp.bisbis10.datagenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;

public class RatingDataGen {
    static private final int ratingPerRestaurant = 20;

    static public List<Rating> createRandomRatingList(List<Restaurant> restaurants) {
        List<Rating> ratings = new ArrayList<>();
        Random random = new Random();
        for (Restaurant restaurant : restaurants) {
            for (int i = 0; i < ratingPerRestaurant; i++) {
                float rating = random.nextFloat(1f, 5f);
                ratings.add(new Rating(
                    restaurant,
                    rating
                ));
            }
        }
        return ratings;
    }
}
