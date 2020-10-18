package com.company;

import com.company.DragonSubclasses.*;
import com.company.FoodSubclasses.*;

import java.util.*;

public class FactoryStore {
    private Player player;
    private Game game;
    public LinkedHashMap<String, Dragon> dragonsForSale;
    public LinkedHashMap<String, Food> foodForSale;

    public FactoryStore(Game game, Player player){
        this.game = game;
        this.player = player;
        createDragons();
        createFood();
    }

    private void createDragons(){
        dragonsForSale = new LinkedHashMap<>();
        dragonsForSale.put("Lockheed", new Lockheed());
        dragonsForSale.put("Falkor", new Falkor());
        dragonsForSale.put("Smaug", new Smaug());
        dragonsForSale.put("Toothless", new Toothless());
        dragonsForSale.put("Viserion", new Viserion());
    }

    private void createFood(){
        foodForSale = new LinkedHashMap<>();
        foodForSale.put("grass", new Grass());
        foodForSale.put("fish", new Fish());
        foodForSale.put("meat", new Meat());
    }

    public ArrayList<String> dragonsYouCanBuy(){
        ArrayList<String> dragonsAvailable = new ArrayList<>();

        for (var dragon: dragonsForSale.keySet()){
            if(player.getMoneyBalance() >= dragonsForSale.get(dragon).dragonPrice){
                dragonsAvailable.add(dragon);
            }
        }
        return dragonsAvailable;
    }
    public ArrayList<String> foodYouCanBuy(){
        ArrayList<String> foodAvailable = new ArrayList<>();

        for(var food: foodForSale.keySet()){
            if(player.getMoneyBalance() >= foodForSale.get(food).price){
                foodAvailable.add(food);
            }
        }
        return foodAvailable;
    }

    public void buyFood(){
        if(foodForSale.size() > 0 && player.getMoneyBalance() > 0){
            printFoodYouCanBuy();
            var inputInt = Game.promptInt("Which food you want to buy? [1-"
                    + foodYouCanBuy().size() + "]\n" +
                    "NOTE: enter 0 to next player / next round", 0, foodYouCanBuy().size());

            if(inputInt == 0){ return; /* do nothing / skip round */ }

            var chosenFoodName = foodYouCanBuy().get(inputInt-1);
            var maxAmount = 999; //change later
            var weight = Game.promptInt("Enter amount of food you want to buy:" +
                    "\nNote: enter 0 to skip round/player", 0, maxAmount);
            if(weight==0) {
                System.out.println("You decided to skip round/player");
            } else{
                Food foodToAdd;
                switch (chosenFoodName) {
                    case "grass" -> foodToAdd = new Grass(weight);
                    case "fish" ->  foodToAdd = new Fish(weight);
                    case "meat" ->  foodToAdd = new Meat(weight);
                    default -> throw new IllegalStateException("Unexpected value: " + chosenFoodName);
                }
                if (foodToAdd.finalPrice > player.getMoneyBalance()) {
                    System.out.println("Trying to get a loan? Not today. Try again");
                    buyFood();
                } else {
                    var add = true;
                    for(int i = player.foodOwned.size()-1; i>=0; i--){
                        if(player.foodOwned.get(i).name.equals(foodToAdd.name)){
                            player.foodOwned.get(i).setWeight(player.foodOwned.get(i).weight + foodToAdd.weight);
                            add = false;
                        }
                    }
                    if(add){
                        player.foodOwned.add(foodToAdd);
                    }

                    player.setMoneyBalance(player.getMoneyBalance() - foodToAdd.finalPrice);
                    if(foodYouCanBuy().size() > 0) {
                        System.out.println("You can buy more food if you want!");
                        buyFood();
                    }
                }
            }
        } else {
            System.out.println("You cant buy anything, sorry!");
            game.menuChoice(player);
        }
    }


    public void buyDragons(){
        if(dragonsYouCanBuy().size()>0){
            printDragonsYouCanBuy();
            var inputInt = Game.promptInt("Which one you want to buy? [1-"
                    + dragonsYouCanBuy().size() + "]\n" +
                    "NOTE: enter 0 to next player / next round", 0, dragonsYouCanBuy().size());
            if(inputInt == 0){ return; /* do nothing / skip round */ }
            var chosenDragonName = dragonsYouCanBuy().get(inputInt-1);
            var name = askName();
            var gender = askGender();
            Dragon dragonToAdd;
            switch(chosenDragonName){
                case "Lockheed"-> dragonToAdd = new Lockheed(name, gender, player);
                case "Falkor" -> dragonToAdd = new Falkor(name, gender, player);
                case "Smaug" -> dragonToAdd = new Smaug(name, gender, player);
                case "Toothless" -> dragonToAdd = new Toothless(name, gender, player);
                case "Viserion" -> dragonToAdd = new Viserion(name, gender, player);
                default -> throw new IllegalStateException("Unexpected value: " + chosenDragonName);
            }
            player.dragonsOwned.add(dragonToAdd);
            player.setMoneyBalance(player.getMoneyBalance()-dragonToAdd.dragonPrice);
            if(dragonsYouCanBuy().size() > 0) {
                System.out.println("\n".repeat(20) + "You can buy more dragons if you want!");
                buyDragons();
            }
        } else {
            System.out.println("You cant buy anything, sorry!");
        }
    }


    public void sellDragons(){
        if(player.dragonsOwned.size() > 0){
            int counter = 1;
            System.out.println("Dragons you can sell:");
            for(var dragon: player.dragonsOwned) {
                System.out.println(counter++ + ". " + dragon.name + ". You would earn: " + dragon.currentPrice());
            }

            var inputInt = Game.promptInt("Which one you want to sell? [1-"
                    + player.dragonsOwned.size() + "]\n" +
                    "NOTE: enter 0 to next player / next round", 0, player.dragonsOwned.size());
            if(inputInt == 0){ return; /* do nothing / skip round */ }

            Dragon sold = player.dragonsOwned.get(inputInt-1);
            System.out.println("Successfully removing: " + sold.name);
            player.dragonsOwned.remove(sold);
            player.setMoneyBalance(player.getMoneyBalance() + sold.currentPrice());
            if(player.dragonsOwned.size() > 0){
                System.out.println("You can sell more dragons if you want!");
                sellDragons();
            }
        } else {
            System.out.println("Unfortunately you have nothing to sell.\nTry something else maybe?");
            game.menuChoice(player);
        }
    }

    public void sellAllDragons(){
        if(player.dragonsOwned.size()>0){
            for(int i = player.dragonsOwned.size()-1; i>=0; i--){
                var dragon = player.dragonsOwned.get(i);
                if(dragon.currentPrice()>0){
                    player.setMoneyBalance(dragon.currentPrice() + player.getMoneyBalance());
                } else {
                    continue;
                }
                player.dragonsOwned.remove(dragon);
            }
        }
    }

    public void printDragonsYouCanBuy(){
        int counter = 1;
        System.out.println("Dragons you can buy:");
        System.out.println("Your balance: " + player.getMoneyBalance());
        for(var dragon: dragonsYouCanBuy()){
            System.out.println(counter++ + ". " + dragon + " at price " +
                    dragonsForSale.get(dragon).dragonPrice);
        }
    }

    public void printFoodYouCanBuy(){
        int counter = 1;
        System.out.println("Food you can buy:\nYour balance: " + player.getMoneyBalance());
        for(var food: foodYouCanBuy()){
            System.out.println(counter++ + ". " + foodForSale.get(food).getClass().getSimpleName() + " at price " +
                    foodForSale.get(food).price + " kr/kg" + "." +
                    " [MAX: " + (player.getMoneyBalance()/foodForSale.get(food).price) + " kg.]");
        }
    }

    public String askName(){
        var name = Game.prompt("Enter a name of a dragon:");
        return name.length() > 1 ? name : askName();
    }

    public String askGender(){
        var gender = Game.prompt("Enter a gender [male/female]:");
        if (gender.equals("male")){
            return "male";
        } else if(gender.equals("female")){
            return "female";
        } else{
            return askGender();
        }
    }
}
