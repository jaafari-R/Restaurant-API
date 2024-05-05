package com.att.tdp.bisbis10.controller;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.att.tdp.bisbis10.dto.RestaurantRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateRestaurant() throws Exception {
        RestaurantRequest restaurantRequest = new RestaurantRequest(
            "A Restaurant",
            false,
            Set.of("Maxican", "Italian")
        );
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(restaurantRequest);

        mockMvc.perform(MockMvcRequestBuilders.put("/restaurants/{restaurantId}", 1)
               .content(jsonRequest)
               .contentType("application/json"))
               .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(
            MockMvcRequestBuilders.post("/restaurants")
                .content(jsonRequest)
                .contentType("application/json"))
            .andExpect(MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    void testCreateRestaurant_MissingData() throws Exception {
        RestaurantRequest restaurantRequest = new RestaurantRequest(
            null, // "A Restaurant",
            false,
            Set.of("Maxican", "Italian")
        );
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(restaurantRequest);

        mockMvc.perform(
            MockMvcRequestBuilders.post("/restaurants")
                .content(jsonRequest)
                .contentType("application/json"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testGetRestaurantById() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/restaurants/{restaurantId}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetRestaurantById_NotFound() throws Exception {
        List<Integer> ids = List.of(-1, 105);

        for (Integer id : ids) {
            mockMvc.perform(
                MockMvcRequestBuilders.get("/restaurants/{restaurantId}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
            }

    }

    @Test
    void testGetRestaurantById_InvalidIdDatatype() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/restaurants/{restaurantId}", "abc"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
