package com.company;

import java.util.ArrayList;

public class Player {

    private String name;
    private int moneyBalance = 15000; //start summa
    private static ArrayList<Dragon> dragonsOwned = new ArrayList<>();
    private ArrayList<Food> foodOwned = new ArrayList<>();
    private Game game;

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(int moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public ArrayList<Dragon> getDragonsOwned() {
        return dragonsOwned;
    }

    public ArrayList<Food> getFoodOwned() {
        return foodOwned;
    }

    public static void addDragon(Dragon dragon){
        dragonsOwned.add(dragon);
    }
}
