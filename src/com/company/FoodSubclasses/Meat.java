package com.company.FoodSubclasses;

import com.company.Food;

public class Meat extends Food {
    public Meat(){
        super("meat", 300, 1, 300);
    }

    public Meat(int weight){
        super("meat", 300, weight, weight*300);
    }
}
