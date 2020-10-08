package com.company;

public abstract class Dragon {
    private String name;
    private String gender;
    private int healthPercent;
    private int dragonPrice;
    private Player owner;
    private int age;
    private int maxAge;
    private String foodDragonCanEat;

    public Dragon (String name, String gender, Player owner, int dragonPrice, int healthPercent, int age, int mageAxe, String foodDragonCanEat) {
        this.name = name;
        this.gender = gender;
        this.owner = owner;
        this.dragonPrice = dragonPrice;
        this.healthPercent = healthPercent;
        this.age = age;
        this.maxAge = mageAxe;
        this.foodDragonCanEat = foodDragonCanEat;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getHealthPercent() {
        return healthPercent;
    }

    public int getDragonPrice() {
        return dragonPrice;
    }

    public Player getOwner() {
        return owner;
    }

    public int getAge() {
        return age;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int decreaseHealth() {
        var randomNr = (int) ((Math.random() * (31 - 10)) + 10);
        return healthPercent -= randomNr;
    }
}
