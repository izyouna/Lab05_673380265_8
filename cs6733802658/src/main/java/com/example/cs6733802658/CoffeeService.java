package com.example.cs6733802658;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CoffeeService {
    private final List<Coffee> coffeeList = new ArrayList<>();

    public CoffeeService(){
        coffeeList.add(new Coffee(1, "Espresso", 45.0));
        coffeeList.add(new Coffee(2, "Latte", 55.0));
    }   

    public List<Coffee> getAllCoffees() {
        return coffeeList;
    }

    public Optional<Coffee> getCoffeeById(int id) {
        return coffeeList.stream().filter(coffee -> coffee.getId() != null && coffee.getId() == id).findFirst();
    }

    public Coffee addCoffee(Coffee coffee){
        if (coffee.getId() == null || coffee.getId() <= 0) {
            int maxId = coffeeList.stream()
                    .mapToInt(c -> c.getId() != null ? c.getId() : 0)
                    .max()
                    .orElse(0);
            coffee.setId(maxId + 1);
        }
        coffeeList.add(coffee);
        return coffee;
    }

    public Optional<Coffee> updateCoffee(int id, Coffee updatedCoffee) {
        return getCoffeeById(id).map(existingCoffee -> {
            existingCoffee.setName(updatedCoffee.getName());
            existingCoffee.setPrice(updatedCoffee.getPrice());
            return existingCoffee;
        });
    }

    public boolean deleteCoffee(int id) {
        return coffeeList.removeIf(coffee -> coffee.getId() != null && coffee.getId() == id);
    }
}

