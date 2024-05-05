package com.att.tdp.bisbis10.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.utils.PaginationUtils;

@DataJpaTest
public class DishRepositoryTests {
    @Autowired
    private DishRepository dishRepository;

    @Test
    void itShouldReturnDishesByRestaurantId() {
        List<Dish> actual = dishRepository.findByRestaurantId(1);
        assertEquals(15, actual.size());
    }

    @Test
    void itShouldReturnDishesPageByRestaurantId() {
        Page<Dish> actual1 = dishRepository.findByRestaurantId(1, PaginationUtils.createPagable(0, 10));
        Page<Dish> actual2 = dishRepository.findByRestaurantId(1, PaginationUtils.createPagable(1, 10));
        
        assertEquals(10, actual1.getNumberOfElements());
        assertEquals(5, actual2.getNumberOfElements());
    }
}
