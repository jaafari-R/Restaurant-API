package com.att.tdp.bisbis10.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.att.tdp.bisbis10.entity.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> findByRestaurantId(Integer restaurantId);

    Page<Dish> findByRestaurantId(Integer restaurantId, Pageable pageable);

    
}
