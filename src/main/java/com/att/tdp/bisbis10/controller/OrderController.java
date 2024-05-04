package com.att.tdp.bisbis10.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.tdp.bisbis10.dto.OrderRequest;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @PostMapping
    public ResponseEntity<Order> addOrder(
        @Valid @RequestBody OrderRequest orderRequest
    ) {
        Order newOrder = orderService.addOrder(orderRequest);
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }
}
