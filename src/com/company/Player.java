package com.company;

import java.util.ArrayList;

public class Player {
    private Game game;
    private String name;
    private int moneyBalance = 10000; //start summa
    private ArrayList<Dragon> dragonsOwned;
    private ArrayList<Food> foodOwned;

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


    public void addDragonToList(Dragon dragon, Player player){
        if(dragon != null){
            this.dragonsOwned.add(dragon);
        } else
            game.menuChoice(player);
    }

    public void getAllDragons(){
        for(var dragon: dragonsOwned){
            System.out.println(dragon.getName() + " at price " + dragon.getDragonPrice() + " its health: " + dragon.getHealthPercent());
        }
    }
}
