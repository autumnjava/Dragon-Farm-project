package com.company;

import java.util.*;

public class Player {
    private String name;
    private Game game;
    private int moneyBalance = 20000; //start summa
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
                System.out.println(food.getClass().getSimpleName() + " at price " + food.price + " kr/kg. Amount: " + food.weight + " kg. SUM: " + food.weight*food.price);
            }
        } else {
        System.out.println("You don't have any food.");
    }
    }

    public void feedDragons(){
        if(dragonsOwned.size()>0 ){ //&& foodOwned.size()>0
            System.out.println("\n".repeat(5) + "You decided to feed your dragons! They are hungry!");
            int counter = 1;
            for(var dragon: dragonsOwned) {
                System.out.println(counter + ". " + dragon.name + ". Its health: " + dragon.healthPercent
                + ". It can eat: " + dragon.foodDragonCanEat);
                counter++;
            }
            System.out.println("Choose a dragon to feed!");
            var inputDragon = Game.prompt("Which one you want to feed? [1-"
                    + dragonsOwned.size() + "]\n" +
                    "NOTE: enter 999 to next player / next round");
            try {
                var inputInt = Integer.parseInt(inputDragon);
                if (inputInt == 999) { return; /* do nothing / skip round */ }
                if(inputInt>dragonsOwned.size()){
                    System.out.println("No cheating!" + "\n".repeat(5));
                    feedDragons();
                }
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
                        if (ownable.name.toLowerCase().equals(eatable.toLowerCase())) {
                            canEat = true;
                            break;
                        }
                    }
                }
                if(canEat){
                    System.out.println("\n".repeat(5) + "You have food that dragon can eat!");
                    int c = 0;
                    var foodMap = new HashMap<Integer, Food>();
                    for(var food: chosenDragon.foodDragonCanEat){
                        for(var owned: foodOwned){
                            if(owned.name.equals(food)){
                                ++c;
                                foodMap.put(c, owned);
                                System.out.println(c + ". " + owned.name + " [" + owned.weight + " kg]");
                            }
                        }
                    }

                    var input = Game.promptInt("What type of food do you want to feed the dragon?", 1, c);
                    var chosen = foodMap.get(input);
                    System.out.println("You entered " + input + " which is:" + chosen.name + ", I owe: " + chosen.weight);
                    var weight = Game.promptInt("Enter amount: ", 1, chosen.weight);
                    foodMap.get(input).setWeight(chosen.weight-weight);

                    for(int i = foodOwned.size()-1; i >= 0; i--){
                        if(foodOwned.get(i).weight == 0){
                            System.out.println(foodOwned.get(i).name + " amount became 0. Removing from the list");
                            foodOwned.remove(foodOwned.get(i));
                        }
                    }


                } else {
                    System.out.println("You dont have food that dragon can eat!");
                }



            } catch (Exception e) {
                System.out.println("\n".repeat(5));
                System.out.println(e);
                feedDragons();
            }

        } else {
            System.out.println("You have no dragons/no food. Try something else?");
            game.menuChoice(this);
        }
    }


}
