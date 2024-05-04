package com.att.tdp.bisbis10.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.tdp.bisbis10.dto.OrderItemDTO;
import com.att.tdp.bisbis10.dto.OrderRequest;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.OrderRepository;

import jakarta.validation.Valid;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantService restaurantService;
    private final DishService dishService;
    private final OrderItemService orderItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantService restaurantService, DishService dishService, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.restaurantService = restaurantService;
        this.dishService = dishService;
        this.orderItemService = orderItemService;
    }

	public Order addOrder(@Valid OrderRequest orderRequest) {
        Restaurant restaurant = restaurantService.getRestaurantById(
            orderRequest.getRestaurantId()
        );
        Order order = new Order(restaurant);

        System.out.println();

        // create order Items
        Map<Integer, OrderItem> orderItems = new HashMap<>();
        for (OrderItemDTO orderItemDTO : orderRequest.getOrderItems()) {
            Integer amount = orderItemDTO.getAmount();
            Integer dishId = orderItemDTO.getDishId();

            Dish dish = dishService.getDishByRestaurantAndDishIds(
                restaurant.getId(),
                orderItemDTO.getDishId()
            );

            if(!orderItems.containsKey(orderItemDTO.getDishId())) {
                orderItems.put(
                    dishId,
                    new OrderItem(amount, order, dish)
                );
            }
            else {
                Integer currentAmount = orderItems.get(orderItemDTO.getDishId()).getAmount();
                orderItems.get(dishId)
                    .setAmount(
                        currentAmount +amount
                    );
            }
        }

        List<OrderItem> orderItemsList = new ArrayList<OrderItem>(orderItems.values());
        order.setOrderItems(orderItemsList);

        Order newOrder = orderRepository.save(order);
        orderItemsList.forEach(orderItem -> orderItemService.addOrderItem(orderItem));
        
        return newOrder;
	}

}
