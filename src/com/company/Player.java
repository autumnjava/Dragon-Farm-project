package com.company;

import java.util.*;

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

    public ArrayList<Dragon> findSickDragons(){
        ArrayList<Dragon> sickDragons = new ArrayList<>();
        if(dragonsOwned.size() > 0){
            for(var dragon: dragonsOwned){
                if(dragon.healthPercent<=0){
                    sickDragons.add(dragon);
                }
            }
        }
 return sickDragons;
    }

    public void removeSickDragonIfFound(){
        if(findSickDragons().size() > 0){
            for(var sickDragon : findSickDragons()){
                dragonsOwned.remove(sickDragon);
                System.out.println("Successfully removed " + sickDragon.name + " as its health dropped below 0");
            }
        }
    }



    public void getAllDragons(){
        if(dragonsOwned.size() > 0){
            Game.print("-".repeat(50));
            System.out.println("NOTE: if health drops below 0, dragon dies.");
            for(var dragon: dragonsOwned){
                System.out.println(dragon.name.toUpperCase() + " initial price " + dragon.dragonPrice + " its health: " + dragon.healthPercent +
                        " current price: " + dragon.currentPrice() + " kr.");
            }
            Game.print("-".repeat(50));
        } else {
            Game.print("-".repeat(50));
            System.out.println("You don't have any dragons.");
            Game.print("-".repeat(50));
        }
    }

    public void getAllFood(){
        if(foodOwned.size() > 0){
            for(var food: foodOwned){
                System.out.println(food.name + " at price " + food.price/food.weight + " kr/kg. Amount: " + food.weight + " kg. SUM: " + food.price);
            }
        } else {
        System.out.println("You don't have any food.");
    }
    }


}
