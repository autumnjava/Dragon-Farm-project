package com.company.DragonSubclasses;

import com.company.Dragon;
import com.company.Player;

public class Falkor extends Dragon {

    public Falkor(){
        super(null, null, null);
        dragonPrice = 5000;
        healthPercent = 100;
        age = 0;
        maxAge = 11;
        litterSize = 3;
        isSick = false;
        vetCost = 400;
        foodDragonCanEat = addFood("fish");
    }
    public Falkor(String name, String gender, Player owner){
        super(name, gender, owner);
        dragonPrice = 5000;
        healthPercent = 100;
        age = 0;
        maxAge = 11;
        litterSize = 3;
        isSick = false;
        vetCost = 400;
        foodDragonCanEat = addFood("fish");
    }
}
