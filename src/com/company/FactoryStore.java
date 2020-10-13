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
        System.out.println("Welcome to the MagicStore, " + player.getName());
        System.out.println("MagicStore - we never run out of stuff (C).");
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
            try{
                System.out.println("NOTE: enter 999 to next player / next round");
                var input = Game.prompt("Which food you want to buy? [1-"
                        + foodYouCanBuy().size() + "]");
                var inputInt = Integer.parseInt(input);
                if(inputInt == 999){ return; /* do nothing / skip round */ }
                if(inputInt>foodYouCanBuy().size()){
                    System.out.println("No cheating!");
                    buyFood();
                }
                var chosenFoodName = foodYouCanBuy().get(inputInt-1);
                var weight = askWeight();

                if(weight > 0){
                    Food foodToAdd;
                    switch(chosenFoodName){
                        case "grass" -> foodToAdd = new Grass(weight);
                        case "fish" -> foodToAdd = new Fish(weight);
                        case "meat" -> foodToAdd = new Meat(weight);
                        default -> throw new IllegalStateException("Unexpected value: " + chosenFoodName);
                    }
                    if(foodToAdd.price > player.getMoneyBalance()){
                        System.out.println("Trying to get a loan? Not today. Try again");
                        buyFood();
                    }else {
                        player.foodOwned.add(foodToAdd);
                        player.setMoneyBalance(player.getMoneyBalance() - foodToAdd.price);
                        buyMoreFood();
                    }

                } else {
                    System.out.println("You decided to skip round/player");

                }
            } catch (Exception e) {
                System.out.println("Only numbers!");
                buyFood();
            }
        } else {
            System.out.println("You cant buy anything, sorry!");
            if(player.getMoneyBalance() > 0){
                game.menuChoice(player);
            }
        }
    }


    //Note: enter 0 to skip round/player
    //0
    //You decided to skip round/player
    //Only numbers!

    public int askWeight(){
        int weight = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount of food you want to buy:");
        System.out.println("Note: enter 0 to skip round/player");
        String weightString = scanner.nextLine();

            try {
                weight = Integer.parseInt(weightString);

            } catch (Exception e) {
                System.out.println("Only numbers!");
                return askWeight();
            }
        return weight;
    }

    public void buyDragons(){
        if(dragonsYouCanBuy().size()>0 && player.getMoneyBalance()>0){
            printDragonsYouCanBuy();
            try{
                System.out.println("NOTE: enter 999 to next player / next round");
                var input = Game.prompt("Which one you want to buy? [1-"
                        + dragonsYouCanBuy().size() + "]");
                var inputInt = Integer.parseInt(input);
                if(inputInt>dragonsYouCanBuy().size() && inputInt != 999){
                    System.out.println("No cheating!");
                    buyDragons();
                }
                switch(inputInt){
                    case 1-> {
                        player.dragonsOwned.add(new Lockheed(askName(), askGender(), player));
                        setBalance("Lockheed");
                        buyMore();
                    }
                    case 2 -> {
                        player.dragonsOwned.add(new Falkor(askName(), askGender(), player));
                        setBalance("Falkor");
                        buyMore();
                    }
                    case 3 -> {
                        player.dragonsOwned.add(new Smaug(askName(), askGender(), player));
                        setBalance("Smaug");
                        buyMore();
                    }
                    case 4 -> {
                        player.dragonsOwned.add(new Toothless(askName(), askGender(), player));
                        setBalance("Toothless");
                        buyMore();
                    }
                    case 5 -> {
                        player.dragonsOwned.add(new Viserion(askName(), askGender(), player));
                        setBalance("Viserion");
                        buyMore();
                    }
                    case 999 -> System.out.println("you decided to skip round/player");
                }
            } catch (Exception e) {
                System.out.println("Only numbers!");
                buyDragons();
            }
        } else {
            System.out.println("You cant buy anything, sorry!");
            if(player.getMoneyBalance() > 0){
                game.menuChoice(player);
            }
        }
    }


    public void printDragonsYouCanBuy(){
        int counter = 1;
        System.out.println("\n".repeat(20) + "Dragons you can buy:");
        System.out.println("Your balance: " + player.getMoneyBalance());
        for(var dragon: dragonsYouCanBuy()){
            System.out.println(counter + ". " + dragon + " at price " +
                    dragonsForSale.get(dragon).dragonPrice);
            counter++;
        }
    }

    public void printFoodYouCanBuy(){
        int counter = 1;
        System.out.println("\n".repeat(20) + "Food you can buy:");
        System.out.println("Your balance: " + player.getMoneyBalance());
        for(var food: foodYouCanBuy()){
            System.out.println(counter + ". " + food + " at price " +
                    foodForSale.get(food).price + " kr/kg");
            counter++;
        }
    }

    public void buyMore(){
        if(dragonsYouCanBuy().size() > 0) {
            System.out.println("\n".repeat(20) + "You can buy more dragons if you want!");
            buyDragons();
        }
    }

    public void buyMoreFood(){
        if(foodYouCanBuy().size() > 0) {
            System.out.println("\n".repeat(20) + "You can buy more food if you want!");
            buyFood();
        }
    }

    public String askName(){
        return Game.prompt("Enter a name of a dragon:");
    }

    public String askGender(){
        var gender = Game.prompt("Enter a gender [male/female]:");
        if (gender.equals("male")){
            return "male";
        }
        else if(gender.equals("female")){
            return "female";
        }
        else{
            return askGender();
        }
    }

    /**
     * This is a method that updates players balance
     * @param dragon To get price of dragon
     */

    public void setBalance(String dragon){
        player.setMoneyBalance(player.getMoneyBalance() - dragonsForSale.get(dragon).dragonPrice);
    }
}
