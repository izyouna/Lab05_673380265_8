package com.example.cs6733802658;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Cs6733802658ApplicationTests {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        CoffeeService coffeeService = new CoffeeService();
        CoffeeController coffeeController = new CoffeeController(coffeeService);
        mockMvc = MockMvcBuilders.standaloneSetup(coffeeController).build();
    }

    @Test
    void testGetAllCoffees() throws Exception {
        mockMvc.perform(get("/coffees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetCoffeeByIdSuccess() throws Exception {
        mockMvc.perform(get("/coffees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Espresso"))
                .andExpect(jsonPath("$.price").value(45.0));
    }

    @Test
    void testGetCoffeeByIdNotFound() throws Exception {
        mockMvc.perform(get("/coffees/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCoffee() throws Exception {
        String json = "{\"name\":\"Cappuccino\",\"price\":60.0}";
        mockMvc.perform(post("/coffees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Cappuccino"))
                .andExpect(jsonPath("$.price").value(60.0));
    }

    @Test
    void testUpdateCoffeeSuccess() throws Exception {
        String json = "{\"name\":\"Latte Special\",\"price\":50.0}";
        mockMvc.perform(put("/coffees/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Latte Special"))
                .andExpect(jsonPath("$.price").value(50.0));
    }

    @Test
    void testUpdateCoffeeNotFound() throws Exception {
        String json = "{\"name\":\"Unknown\",\"price\":50.0}";
        mockMvc.perform(put("/coffees/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteCoffeeSuccess() throws Exception {
        mockMvc.perform(delete("/coffees/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/coffees/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteCoffeeNotFound() throws Exception {
        mockMvc.perform(delete("/coffees/999"))
                .andExpect(status().isNotFound());
    }
}

