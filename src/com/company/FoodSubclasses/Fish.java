package com.company.FoodSubclasses;

import com.company.Food;

public class Fish extends Food {
    public Fish(){
        super("fish", 200, 1, 200);
    }

    public Fish(int weight){
        super("fish", 200, weight, 200*weight);
    }
}
