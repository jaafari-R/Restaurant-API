package com.att.tdp.bisbis10.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.att.tdp.bisbis10.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>  {

    List<Restaurant> findByCuisinesContaining(String cuisine);

    Page<Restaurant> findByCuisinesContaining(String cuisine, Pageable pageable);
    
}
