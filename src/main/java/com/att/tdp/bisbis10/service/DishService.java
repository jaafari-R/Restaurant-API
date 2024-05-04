package com.att.tdp.bisbis10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.tdp.bisbis10.repository.DishRepository;

@Service
public class DishService {
    private final DishRepository dishRepository;
    
    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

}
