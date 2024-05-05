package com.att.tdp.bisbis10.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrderRequest {
    @NotNull(message = "restaurantId must be provided!")
    Integer restaurantId;
    @Valid
    @NotEmpty(message = "orderItems must not be empty!")
    List<OrderItemDTO> orderItems;

    public OrderRequest() {
    }

    public OrderRequest(Integer restaurantId, List<OrderItemDTO> orderItems) {
        this.restaurantId = restaurantId;
        this.orderItems = orderItems;
    }


    public Integer getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItemDTO> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

}
