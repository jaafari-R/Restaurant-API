package com.att.tdp.bisbis10.datagenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.att.tdp.bisbis10.entity.Restaurant;

public class RestaurantDataGen {
    static public List<Restaurant> createRandomRestaurantList(int count) {
        List<String> cuisinesOptions = Arrays.asList("Italian", "Mexican", "Chinese", "Indian", "Japanese", "Thai");
        List<String> restaurantNames = Arrays.asList("Tasty", "Delicious", "Yummy", "Savory", "Gourmet", "Spicy", "Flavorful", "Sizzling", "Tangy", "Tasty", "Crispy", "Zesty", "Succulent", "Aromatic", "Juicy", "Tender", "Mouthwatering", "Satisfying", "Hearty", "Sizzling");

        List<Restaurant> restaurants = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String name = restaurantNames.get(random.nextInt(restaurantNames.size())) + " Restaurant";
            boolean isKosher = random.nextBoolean();
            Set<String> cuisines = new HashSet<>();
            int numCuisines = random.nextInt(3) + 1;
            for (int j = 0; j < numCuisines; j++) {
                cuisines.add(cuisinesOptions.get(random.nextInt(cuisinesOptions.size())));
            }
            restaurants.add(new Restaurant(name, isKosher, cuisines));
        }

        return restaurants;
    }
}
