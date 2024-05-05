package com.att.tdp.bisbis10.datagenerator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.att.tdp.bisbis10.dto.DishRequest;
import com.att.tdp.bisbis10.dto.OrderItemDTO;
import com.att.tdp.bisbis10.dto.OrderRequest;
import com.att.tdp.bisbis10.dto.RatingRequest;
import com.att.tdp.bisbis10.dto.RestaurantRequest;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.DishService;
import com.att.tdp.bisbis10.service.OrderService;
import com.att.tdp.bisbis10.service.RatingService;
import com.att.tdp.bisbis10.service.RestaurantService;

public class DataCreator {
    private final RestaurantService restaurantService;
    private final RatingService ratingService;
    private final OrderService orderService;
    private final DishService dishService;
    
    @Autowired
    DataCreator(RestaurantService restaurantService, DishService dishService, OrderService orderService, OrderService orderService2, RatingService ratingService) {
        this.restaurantService = restaurantService;
        this.ratingService = ratingService;
        this.orderService = orderService;
        this.dishService = dishService;

        int RESTAURANT_COUNT = 100;

        // if there is data, STOP
        if(!restaurantService.getAllRestaurants(PageRequest.of(0,1)).isEmpty()) {
            return;
        }

        // create data
        
        // -- Restaurants
        List<Restaurant> restaurants = RestaurantDataGen.createRandomRestaurantList(RESTAURANT_COUNT);
        List<Restaurant> createdRestaurants = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            Restaurant createdRestaurant = restaurantService.addRestaurant(
                new RestaurantRequest(
                    restaurant.getName(),
                    restaurant.getIsKosher(),
                    restaurant.getCuisines()
                )
            );
            createdRestaurant.setDishes(new ArrayList<>());
            createdRestaurants.add(createdRestaurant);
        }

        // TODO
        // -- Dishes
        List<Dish> dishes = DishDataGen.createRandomDishesList(createdRestaurants);
        for (Dish dish : dishes) {
            Dish newDish = dishService.addDish(dish.getRestaurant().getId(), new DishRequest(
                dish.getName(),
                dish.getDescription(),
                dish.getPrice()
            ));
            createdRestaurants.get(newDish.getRestaurant().getId() - 1).getDishes().add(newDish);
        }

        // TODO
        // -- Ratings
        List<Rating> ratings = RatingDataGen.createRandomRatingList(createdRestaurants);
        for (Rating rating : ratings) {
            ratingService.addRestaurantRating(new RatingRequest(
                rating.getRestaurant().getId(),
                rating.getRating()
            ));
        }

        // TODO
        // -- Orders
        List<Order> orders = OrderDataGen.createRandomOrdersList(createdRestaurants);
        for (Order order : orders) {
            List<OrderItemDTO> orderItems = new ArrayList<>();
            for (OrderItem orderItem : order.getOrderItems()) {
                orderItems.add(
                    new OrderItemDTO(
                        orderItem.getDish().getId(),
                        orderItem.getAmount()
                    )
                );
            }
            orderService.addOrder(new OrderRequest(
                order.getRestaurant().getId(),
                orderItems
            ));
        }
    }
}
