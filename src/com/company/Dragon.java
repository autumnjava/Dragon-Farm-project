package com.company;

import java.util.*;

public abstract class Dragon {
    protected String name;
    protected String gender;
    protected Player owner;

    protected int healthPercent;
    protected int dragonPrice;
    protected int age;
    protected int maxAge;
    protected ArrayList<String> foodDragonCanEat;

    public Dragon (String name, String gender, Player owner) {
        this.name = name;
        this.gender = gender;
        this.owner = owner;
    }

    public ArrayList<String> addFood(String... args){
        ArrayList<String> foodDragonCanEat = new ArrayList<>();
        for(var x: args){
            foodDragonCanEat.add(x);
        }
        return foodDragonCanEat;
    }

}
