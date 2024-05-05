package com.att.tdp.bisbis10.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.utils.PaginationUtils;

@DataJpaTest
public class RestaurantRepositoryTests {
    
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void itShouldReturnRestaurantsContainingCuisine() {
        List<Restaurant> actual = restaurantRepository.findByCuisinesContaining("Indian");

        assertEquals(34, actual.size());
    }

    @Test
    void itShouldReturnRestaurantsPageContainingCuisine() {
        Page<Restaurant> actual1 = restaurantRepository.findByCuisinesContaining("Indian", PaginationUtils.createPagable(0, 10));
        Page<Restaurant> actual2 = restaurantRepository.findByCuisinesContaining("Indian", PaginationUtils.createPagable(3, 4));

        assertEquals(10, actual1.getNumberOfElements());
        assertEquals(4, actual2.getNumberOfElements());
    }
}
