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
    protected int litterSize;
    protected ArrayList<String> foodDragonCanEat;
    protected boolean beenFed = false;
    protected boolean died = false;

    public Dragon (String name, String gender, Player owner) {
        this.name = name;
        this.gender = gender;
        this.owner = owner;
    }

    public ArrayList<String> addFood(String... args){
        ArrayList<String> foodDragonCanEat = new ArrayList<>();
        foodDragonCanEat.addAll(Arrays.asList(args));
        return foodDragonCanEat;
    }

    public void decreaseHealthOfDragon() {
        if (owner.dragonsOwned.size() > 0) {
            var randomNr = (int) ((Math.random() * (31 - 10)) + 10);
            //var randomNr = 1;
            healthPercent -= randomNr;
        }
    }

    public void setHealthPercent(int healthPercent) {
        this.healthPercent = healthPercent;
    }


    public int currentPrice(){
        return dragonPrice * healthPercent / 100;
    }

}
