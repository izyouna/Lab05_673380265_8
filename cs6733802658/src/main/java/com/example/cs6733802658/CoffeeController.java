package com.example.cs6733802658;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    // GET /coffees -> ดึงทั้งหมด
    @GetMapping
    public List<Coffee> getAllCoffees(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return coffeeService.getCoffeeById(id)
                    .map(java.util.Collections::singletonList)
                    .orElse(java.util.Collections.emptyList());
        }
        return coffeeService.getAllCoffees();
    }

    // GET /coffees/{id} -> ดึงตาม ID
    // ใช้ @PathVariable ดึงค่า id จาก URL
    @GetMapping("/{id}")
    public Coffee getCoffeeById(@PathVariable int id) {
        return coffeeService.getCoffeeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coffee not found with id: " + id));
    }

    // POST /coffees -> เพิ่มข้อมูลใหม่
    // ใช้ @RequestBody รับ JSON ใน Request Body
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee createCoffee(@RequestBody Coffee coffee) {
        return coffeeService.addCoffee(coffee);
    }

    // PUT /coffees/{id} -> แก้ไขข้อมูล
    // ใช้ทั้ง @PathVariable (ระบุตัวที่จะแก้) และ @RequestBody (ข้อมูลใหม่)
    @PutMapping("/{id}")
    public Coffee updateCoffee(@PathVariable int id, @RequestBody Coffee coffee) {
        return coffeeService.updateCoffee(id, coffee)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coffee not found with id: " + id));
    }

    // DELETE /coffees/{id} -> ลบข้อมูล
    // ใช้ @PathVariable ระบุ ID ที่ต้องการลบ
    @DeleteMapping("/{id}")
    public String deleteCoffee(@PathVariable int id) {
        boolean isDeleted = coffeeService.deleteCoffee(id);
        if (isDeleted) {
            return "Coffee with id " + id + " deleted successfully.";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Coffee not found with id: " + id);
    }
}