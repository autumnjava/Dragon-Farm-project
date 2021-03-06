package com.company.DragonSubclasses;

import com.company.Dragon;
import com.company.Player;

public class Viserion extends Dragon {
    public Viserion(){
        super(null, null, null);
        dragonPrice = 8000;
        healthPercent = 100;
        age = 0;
        maxAge = 15;
        litterSize = 4;
        isSick = false;
        vetCost = 400;
        foodDragonCanEat = addFood("fish", "meat", "grass");
    }
    public Viserion(String name, String gender, Player owner){
        super(name, gender, owner);
        dragonPrice = 8000;
        healthPercent = 100;
        age = 0;
        maxAge = 15;
        litterSize = 4;
        isSick = false;
        vetCost = 400;
        foodDragonCanEat = addFood("fish", "meat", "grass");
    }

}
