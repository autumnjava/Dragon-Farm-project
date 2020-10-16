package com.company;

import java.util.*;

public class Player {
    private String name;
    private Game game;
    private int moneyBalance = 20000; //start amount of cash
    protected ArrayList<Dragon> dragonsOwned;
    protected ArrayList<Food> foodOwned;

    public Player(String name, Game game){
        this.name = name;
        this.game = game;
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

    public void findAndRemoveSickDragons(){
        if(dragonsOwned.size() > 0){
            for(int i = dragonsOwned.size()-1; i>= 0; i--){
                if(dragonsOwned.get(i).healthPercent<=0){
                    System.out.println("Removing " + dragonsOwned.get(i).name +
                            " as its health dropped below 0");
                    dragonsOwned.remove(dragonsOwned.get(i));
                }
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
                System.out.println(food.getClass().getSimpleName() + " at price " + food.price + " kr/kg. Amount: " + food.weight + " kg. SUM: " + food.weight*food.price);
            }
        } else {
        System.out.println("You don't have any food.");
    }
    }

    public void feedDragons(){
        if(dragonsOwned.size()>0 && foodOwned.size()>0){
            System.out.println("\n".repeat(20) + "You decided to feed your dragons! They are hungry!\n\nYour dragons:");
            int counter = 1;
            Game.print("-".repeat(50));
            for(var dragon: dragonsOwned) {
                System.out.println(counter + ". " + dragon.name + ". Its health: " + dragon.healthPercent
                + ". It can eat: " + dragon.foodDragonCanEat);
                counter++;
            }
            Game.print("-".repeat(50));
            var inputInt = Game.promptInt("\n\nChoose a dragon to feed! [1-" + dragonsOwned.size() + "]" +
                    "\nNOTE: enter 0 to next player / next round", 0, dragonsOwned.size());

                if (inputInt == 0) { return; /* do nothing / skip round */ }
                var chosenDragon = dragonsOwned.get(inputInt-1);
                switch (chosenDragon.getClass().getSimpleName()){
                    case "Lockheed", "Falkor", "Smaug", "Toothless", "Viserion" -> System.out.println(
                            "You decided to feed " + chosenDragon.name +
                                    "\nIt can eat: " + chosenDragon.foodDragonCanEat);
                    default -> throw new IllegalStateException("Unexpected value: " + chosenDragon.name);
                }

                var canEat = false;
                for(var eatable: chosenDragon.foodDragonCanEat){
                    for(var ownable: foodOwned){
                        if (ownable.name.equals(eatable)) {
                            canEat = true;
                            break;
                        }
                    }
                }
                if(canEat){
                    System.out.println("\n".repeat(20) + "You have food that dragon can eat!\n");
                    int c = 0;
                    var foodMap = new HashMap<Integer, Food>();
                    for(var food: chosenDragon.foodDragonCanEat){
                        for(var owned: foodOwned){
                            if(owned.name.equals(food)){
                                ++c;
                                foodMap.put(c, owned);
                                System.out.println(c + ". " + owned.name + " [You owe: " + owned.weight + " kg]");
                            }
                        }
                    }

                    var input = Game.promptInt("What type of food do you want to feed the dragon?", 1, c);
                    var chosen = foodMap.get(input);
                    int maxKg = (100 - chosenDragon.healthPercent) / 10;
                    System.out.println("\n\nYou chose " +  chosen.name + ", I owe: " + chosen.weight + " kg \n" +
                            "Dragon " + chosenDragon.name + " can eat max: " + maxKg + " kg of " + chosen.name + "\n\n");

                    var weight = Game.promptInt("Enter amount: ", 1, chosen.weight);
                    var hungry = true;

                    if(weight > maxKg) {
                        hungry = false;
                    }

                    if(!hungry){
                        System.out.println("Dragon is not that hungry!\n");
                        System.out.println(chosenDragon.name + " can max eat: " + maxKg + " kg of " + chosen.name);
                        feedDragons();
                    }
                    else{
                        chosenDragon.setHealthPercent(chosenDragon.healthPercent + 10*weight);
                        foodMap.get(input).setWeight(chosen.weight-weight);
                        chosenDragon.beenFed = true;
                    }

                    for(int i = foodOwned.size()-1; i >= 0; i--){
                        if(foodOwned.get(i).weight == 0){
                            System.out.println(foodOwned.get(i).name + " amount became 0. Removing from the list");
                            foodOwned.remove(foodOwned.get(i));
                        }
                    }

                } else {
                    System.out.println("You don't have food that dragon can eat! Try something else?");
                    game.menuChoice(this);
                }

        } else {
            System.out.println("You have no dragons/no food. Try something else?");
            game.menuChoice(this);
        }
    }

}
