package com.att.tdp.bisbis10.datagenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;

public class DishDataGen {
    private static final String[] adjectives = {"Spicy", "Savory", "Creamy", "Zesty", "Tangy", "Sizzling", "Gourmet", "Exotic", "Hearty", "Delicious"};
    private static final String[] nouns = {"Pasta", "Soup", "Salad", "Curry", "Stir Fry", "Taco", "Burger", "Stew", "Pizza", "Sushi"};
    private static final String[] ingredients = {"Chicken", "Beef", "Fish", "Tofu", "Vegetables", "Shrimp", "Lamb", "Pork", "Rice", "Potatoes"};
    private static final int dishesPerRestaurant = 15;

    static public List<Dish> createRandomDishesList(List<Restaurant> restaurants) {
        List<Dish> dishes = new ArrayList<>();
        Random random = new Random();

        for (Restaurant restaurant : restaurants) {
            for (int i = 0; i < dishesPerRestaurant; i++) {
                String name = generateDishName();
                float price = random.nextFloat(100f) + 1f;
                dishes.add(new Dish(name, name + " dish", price, restaurant));
            }
        }

        return dishes;
    }

    public static String generateDishName() {
        Random rand = new Random();
        String adjective = adjectives[rand.nextInt(adjectives.length)];
        String noun = nouns[rand.nextInt(nouns.length)];
        String ingredient = ingredients[rand.nextInt(ingredients.length)];
        String extraIngredient = ingredients[rand.nextInt(ingredients.length)];
        if (!ingredient.equals(extraIngredient)) {
            return adjective + " " + ingredient + " " + noun + " with " + extraIngredient;
        } else {
            return adjective + " " + ingredient + " " + noun;
        }
    }
}
