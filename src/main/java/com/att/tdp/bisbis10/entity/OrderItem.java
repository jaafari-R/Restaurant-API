package com.att.tdp.bisbis10.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "RestaurantOrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer amount;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Dish dish;


    public OrderItem() {
    }

    public OrderItem(Integer amount, Order order, Dish dish) {
        this.amount = amount;
        this.order = order;
        this.dish = dish;
    }



    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }

        OrderItem other = (OrderItem) obj;

        return ((amount == null && other.getAmount() == null) || (amount != null && amount.equals(other.getAmount()))) &&
            ((dish == null && other.getDish() == null) || (dish != null && dish.equals(other.getDish())));
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", amount='" + getAmount() + "'" +
            ", order='" + getOrder().getId() + "'" +
            ", dish='" + getDish().getId() + "'" +
            "}";
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return this.dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

}
