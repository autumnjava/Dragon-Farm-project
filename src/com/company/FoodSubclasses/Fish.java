package com.company.FoodSubclasses;

import com.company.Food;

public class Fish extends Food {
    public Fish(){
        super("fish", 200, 0);
    }

    public Fish(int weight){
        super("fish", 200, weight);
    }
}
