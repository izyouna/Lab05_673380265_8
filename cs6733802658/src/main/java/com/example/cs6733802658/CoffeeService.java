package com.example.cs6733802658;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CoffeeService {
    private final List<Coffee> coffeeList = new ArrayList<>();

    public CoffeeService(){
        coffeeList.add(new Coffee(4, "Americano", 2.0));
        coffeeList.add(new Coffee(2, "Espresso", 50.0));
        coffeeList.add(new Coffee(3, "Cappuccino", 60.0));
    }   

    public List<Coffee> getAllCoffees() {
        return coffeeList;
    }
    public Coffee addCoffee(Coffee coffee){
        coffeeList.add(coffee);
        return coffee;
    }
}
