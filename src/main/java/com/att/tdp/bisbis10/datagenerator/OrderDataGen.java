package com.att.tdp.bisbis10.datagenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;

public class OrderDataGen {
    static private final int ordersPerRestaurant = 10;
    static private final int orderItemsPerOrder = 3;
    static private final int dishesPerRestaurant = 15;

    static public List<Order> createRandomOrdersList(List<Restaurant> restaurants) {
        List<Order> orders = new ArrayList<>();
        Random random = new Random();

        for (Restaurant restaurant : restaurants) {
            for (int i = 0; i < ordersPerRestaurant; i++) {
                Order order = new Order(restaurant);
                List<OrderItem> orderItems = new ArrayList<>();
                for(int j = 0; j < orderItemsPerOrder; ++j) {
                    int amount = random.nextInt(4) + 1;
                    int idx = random.nextInt(dishesPerRestaurant);
                    Dish dish = restaurant.getDishes().get(idx);
                    orderItems.add(
                        new OrderItem(
                            amount,
                            order,
                            dish
                        )
                    );
                }
                order.setOrderItems(orderItems);
                orders.add(order);
            }
        }

        return orders;
    }
}
