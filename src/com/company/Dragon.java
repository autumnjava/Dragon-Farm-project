package com.company;

import java.util.Arrays;

public abstract class Dragon {
//    static String[] dragonClasses = {"Lockheed", "Falkor", "Smaug", "Toothless", "Viserion"};
//    static int[] prices = {4000, 5000, 6000, 7000, 8000};
    static String[] acceptedFood = {"Meat, Fish", "Meat, Grass", "Grass, Metal",
            "Fish", "Metal"};


    public String name;
    private String gender;
    private int healthPercent = 100;
    //private int dragonPrice;

    public Dragon (String name, String gender) {
//        var classIndex = Arrays.asList(dragonClasses).indexOf(this.getClass().getSimpleName());

        this.name = name;
        this.gender = gender;
        //this.dragonPrice = prices[classIndex];
    }

    public String presentYourself(){
        return name;
    }

    public int decreaseHealth(){
        return healthPercent-10;
    }

    public int getHealthPercent() {
        return healthPercent;
    }
}
