package com.example.cs6733802658;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffees")

public class CoffeeController {
    private final CoffeeService coffeeService;
    // Injection ชั้น Service ผ่าน Constructor
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    // GET /api/coffees -> ดึงรายการกาแฟทั้งหมด
    @GetMapping
    public List<Coffee> getAllCoffees() {
        return coffeeService.getAllCoffees();
    }

    // POST /api/coffees -> เพิ่มรายการกาแฟใหม่
    @PostMapping
    public Coffee createCoffee(@RequestBody Coffee coffee) {
        return coffeeService.addCoffee(coffee);
    }
}
