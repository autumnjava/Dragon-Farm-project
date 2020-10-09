package com.company;

import java.util.ArrayList;

public class Player {
    private String name;
    private int moneyBalance = 10000; //start summa
    protected ArrayList<Dragon> dragonsOwned;
    protected ArrayList<Food> foodOwned;

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

    public Dragon findSickDragon(){
        Dragon sickDragon = null;
        if(dragonsOwned.size() > 0){
            for(var dragon: dragonsOwned){
                if(dragon.healthPercent<=0){
                    sickDragon = dragon;
                    System.out.println("Dragon " + dragon.name + " is sick.");
                }
            }
        }
 return sickDragon;
    }

    public void removeSickDragonIfFound(){
        findSickDragon();
        if(findSickDragon() != null && dragonsOwned.size() > 0){
            dragonsOwned.remove(findSickDragon());
        }
    }

    public void decreaseHealthOfDragon() {
        if (dragonsOwned.size() > 0) {
            for (var dragon : dragonsOwned) {
                var randomNr = (int) ((Math.random() * (31 - 10)) + 10);
                dragon.healthPercent -= randomNr;
            }
        }
    }

    public void getAllDragons(){
        if(dragonsOwned.size() > 0){
            Game.print("-".repeat(50));
            for(var dragon: dragonsOwned){
                System.out.println(dragon.name + " at price " + dragon.dragonPrice + " its health: " + dragon.healthPercent);
                Game.print("-".repeat(50));
            }
        } else {
            Game.print("-".repeat(50));
            System.out.println("You don't have any dragons.");
            Game.print("-".repeat(50));
        }
    }

    public void getAllFood(){
        if(foodOwned.size() > 0){
            for(var food: foodOwned){
                System.out.println(food.name + " at price " + food.price);
            }
        } else {
        System.out.println("You don't have any food.");
    }
    }


}
