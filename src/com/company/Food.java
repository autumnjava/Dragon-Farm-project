package com.company;

public abstract class Food {
    protected String name;
    protected int price;
    protected int weight;

    public Food(String name, int price, int weight){
        this.name = name;
        this.price = price;
        this.weight = weight;
    }
}
