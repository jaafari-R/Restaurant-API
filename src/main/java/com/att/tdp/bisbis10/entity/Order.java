package com.att.tdp.bisbis10.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "RestaurantOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;


    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }

        Order other = (Order) obj;

        return (orderItems == null && other.getOrderItems() == null) || orderItems != null && orderItems.equals(other.getOrderItems()) &&
            ((restaurant == null && other.getRestaurant() == null) || (restaurant != null && restaurant.getId() != null && restaurant.getId().equals(other.getRestaurant().getId())));
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", restaurant='" + getRestaurant().getId() + "'" +
            ", orderItems='" + getOrderItems() + "'" +
            "}";
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
