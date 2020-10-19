package com.company.DragonSubclasses;

import com.company.Dragon;
import com.company.Player;

public class Smaug extends Dragon {
    public Smaug(){
        super(null, null, null);
        dragonPrice = 6000;
        healthPercent = 100;
        age = 0;
        maxAge = 6;
        litterSize = 2;
        isSick = false;
        vetCost = 350;
        foodDragonCanEat = addFood("fish", "meat");
    }
    public Smaug(String name, String gender, Player owner){
        super(name, gender, owner);
        dragonPrice = 6000;
        healthPercent = 100;
        age = 0;
        maxAge = 6;
        litterSize = 2;
        isSick = false;
        vetCost = 350;
        foodDragonCanEat = addFood("fish", "meat");
    }
}
