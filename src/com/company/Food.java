package com.company;

public abstract class Food {
    protected String name;
    protected int price;
    protected int weight;
    protected int finalPrice;

    public Food(String name, int price, int weight, int finalPrice){
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.finalPrice = finalPrice;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
