package com.company;

import java.util.Arrays;

public abstract class Dragon {
    static String[] dragonClasses = {"Lockheed", "Falkor", "Smaug", "Toothless", "Viserion"};
    static int[] prices = {4000, 5000, 6000, 7000, 8000, 9000};
    static String[] acceptedFood = {"Meat, Fish", "Meat, Grass", "Grass, Metal",
            "Fish", "Metal"};

    private String name;
    private String gender;
    private int healthPercent = 100;
    private int price;

    public Dragon(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public Dragon create(String name, String gender) {
        var classIndex = Arrays.asList(dragonClasses).indexOf(this.getClass().getSimpleName());

        if (!gender.equals("male") && !gender.equals("female")) {
            throw new RuntimeException("Gender must be male or female!");
        }
        this.name = name;
        this.gender = gender;
        this.price = prices[classIndex];
        return this;
    }
}
