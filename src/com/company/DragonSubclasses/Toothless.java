package com.company.DragonSubclasses;

import com.company.Dragon;
import com.company.Player;

public class Toothless extends Dragon {
    public Toothless(){
        super(null, null, null);
        dragonPrice = 7000;
        healthPercent = 100;
        age = 0;
        maxAge = 5;
        foodDragonCanEat = addFood("grass");
    }
    public Toothless(String name, String gender, Player owner){
        super(name, gender, owner);
        dragonPrice = 7000;
        healthPercent = 100;
        age = 0;
        maxAge = 5;
        foodDragonCanEat = addFood("grass");
    }

}
