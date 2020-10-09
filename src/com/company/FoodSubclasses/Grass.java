package com.company.FoodSubclasses;

import com.company.Food;

public class Grass extends Food {
    public Grass(){
        super("grass", 100, 1);
    }

    public Grass(int weight){
        super("grass", 100, weight);
    }
}
