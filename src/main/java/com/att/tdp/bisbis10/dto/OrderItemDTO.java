package com.att.tdp.bisbis10.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemDTO {
    @NotNull(message = "dishId must be provided!")
    private Integer dishId;
    @NotNull(message = "amount must be provided!")
    @Min(value = 0, message = "amount can not be negative!")
    private Integer amount;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Integer dishId, Integer amount) {
        this.dishId = dishId;
        this.amount = amount;
    }


    public Integer getDishId() {
        return this.dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
