package com.company.DragonSubclasses;

import com.company.Dragon;
import com.company.Player;

public class Lockheed extends Dragon {
    public Lockheed(){
        super(null, null, null);
        dragonPrice = 4000;
        healthPercent = 100;
        age = 0;
        maxAge = 5;
        litterSize = 3;
        isSick = false;
        vetCost = 300;
        foodDragonCanEat = addFood("meat");
    }
    public Lockheed(String name, String gender, Player owner){
        super(name, gender, owner);
        dragonPrice = 4000;
        healthPercent = 100;
        age = 0;
        maxAge = 5;
        litterSize = 3;
        isSick = false;
        vetCost = 300;
        foodDragonCanEat = addFood("meat");
    }


}
