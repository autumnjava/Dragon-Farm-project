package com.company;

import java.util.Arrays;

public abstract class Dragon {
    static String[] dragonClasses = {"Lockheed", "Falkor", "Smaug", "Toothless", "Viserion"};
    static int[] prices = {4000, 5000, 6000, 7000, 8000};
    static String[] acceptedFood = {"Meat, Fish", "Meat, Grass", "Grass, Metal",
            "Fish", "Metal"};


    private String name;
    private String gender;
    private int healthPercent = 100;
    private int dragonPrice;


    public Dragon (String name, String gender) {
        var classIndex = Arrays.asList(dragonClasses).indexOf(this.getClass().getSimpleName());
        this.name = name;
        this.gender = gender;
        this.dragonPrice = prices[classIndex];
    }

    public String getName(){
        return name;
    }


    public int getPrice(){
        var classIndex = Arrays.asList(dragonClasses).indexOf(this.getClass().getSimpleName());
        return dragonPrice = prices[classIndex];
    }

    public int getHealthPercent() {
        return healthPercent;
    }

    public int decreaseHealth() {
        var randomNr = (int) ((Math.random() * (31 - 10)) + 10);
        return healthPercent -= randomNr;
    }
}
