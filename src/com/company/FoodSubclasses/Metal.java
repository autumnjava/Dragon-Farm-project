package com.company.FoodSubclasses;

import com.company.Food;

public class Metal extends Food {
    public Metal(){
        super("metal", 400, 1);
    }


    public Metal(String name, int weight){
        super("metal", 400, weight);
    }
}
