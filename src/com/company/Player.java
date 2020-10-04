package com.company;

import java.util.ArrayList;

public class Player {

    private String name;
    private int moneyBalance = 15000; //start summa
    private ArrayList<Dragon> dragonsOwned = new ArrayList<>();
    private ArrayList<Food> foodOwned = new ArrayList<>();
    private Game game;

    public Player(String name){
        this.name = name;
        this.dragonsOwned = new ArrayList<Dragon>();
        this.foodOwned = new ArrayList<Food>();
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

    public void addDragon(){
        var falkor = new Falkor("Falkorito", "male");
        this.dragonsOwned.add(falkor);

    }

    public void getAllDragonsNames(){
        for(var dragon: dragonsOwned){
            System.out.println(dragon.presentYourself());
        }
    }
}
