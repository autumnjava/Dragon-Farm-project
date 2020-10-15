package com.company;

import com.company.DragonSubclasses.*;
import com.company.FoodSubclasses.*;

import java.util.*;

public class FactoryStore {
    private Player player;
    private Game game;
    public LinkedHashMap<String, Dragon> dragonsForSale;
    public LinkedHashMap<String, Food> foodForSale;
    private Scanner scanner = new Scanner(System.in);

    public FactoryStore(Game game, Player player){
        System.out.println("\n".repeat(20) +"Welcome to the MagicStore, " + player.getName());
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
                var input = Game.prompt("Which food you want to buy? [1-"
                        + foodYouCanBuy().size() + "]\n" +
                        "NOTE: enter 999 to next player / next round");
                var inputInt = Integer.parseInt(input);
                if(inputInt == 999){ return; /* do nothing / skip round */ }
                if(inputInt>foodYouCanBuy().size()){
                    System.out.println("No cheating!");
                    buyFood();
                }
                var chosenFoodName = foodYouCanBuy().get(inputInt-1);
                var weight = askWeight();

                Food foodToAdd;
                if(weight > 0) {
                    switch (chosenFoodName) {
                        case "grass" -> foodToAdd = new Grass(weight);
                        case "fish" ->  foodToAdd = new Fish(weight);
                        case "meat" ->  foodToAdd = new Meat(weight);
                        default -> throw new IllegalStateException("Unexpected value: " + chosenFoodName);
                    }
                    if (foodToAdd.price > player.getMoneyBalance()) {
                        System.out.println("Trying to get a loan? Not today. Try again");
                        buyFood();
                    } else {

                        var add = true;
                        for(int i = player.foodOwned.size()-1; i>=0; i--){
                            if(player.foodOwned.get(i).name.equals(foodToAdd.name)){
                                System.out.println("BINGO! Already exists");
                                player.foodOwned.get(i).setWeight(player.foodOwned.get(i).weight + foodToAdd.weight);
                                add = false;
                            }
                        }
                        if(add){
                            player.foodOwned.add(foodToAdd);
                        }


                        player.setMoneyBalance(player.getMoneyBalance() - foodToAdd.finalPrice);
                        buyMoreFood();
                    }
                }
                else { System.out.println("You decided to skip round/player"); }
            } catch (Exception e) {
                System.out.println(e);
                buyFood();
            }
        } else {
            System.out.println("You cant buy anything, sorry!");
            if(player.getMoneyBalance() > 0 || player.dragonsOwned.size()>1){ //need atleast two dragons to pair them
                System.out.println("Maybe you can pair you dragons atleast?");
                game.menuChoice(player);
            }
        }
    }

    public int askWeight(){
        int weight;

        System.out.println("Enter amount of food you want to buy:\nNote: enter 0 to skip round/player");
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
                var input = Game.prompt("Which one you want to buy? [1-"
                        + dragonsYouCanBuy().size() + "]\n" +
                        "NOTE: enter 999 to next player / next round");
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
        }
    }


    public void sellDragons(){
        if(player.dragonsOwned.size() > 0){
            int counter = 1;
            System.out.println("Dragons you can sell:");
            for(var dragon: player.dragonsOwned) {
                System.out.println(counter + ". " + dragon.name + ". You would earn: " + dragon.currentPrice());
                counter++;
            }

            var input = Game.prompt("Which one you want to sell? [1-"
                        + player.dragonsOwned.size() + "]\n" +
                    "NOTE: enter 999 to next player / next round");
                try {
                    var inputInt = Integer.parseInt(input);
                    if(inputInt == 999){ return; /* do nothing / skip round */ }
                    if(inputInt>player.dragonsOwned.size()){
                        System.out.println("No cheating!");
                        sellDragons();
                    } else {
                        Dragon sold = player.dragonsOwned.get(inputInt-1);
                        System.out.println("Successfully removing: " + sold.name);
                        player.dragonsOwned.remove(sold);
                        player.setMoneyBalance(player.getMoneyBalance() + sold.currentPrice());
                        if(player.dragonsOwned.size() > 0){
                            System.out.println("You can sell more dragons if you want!");
                            sellDragons();
                        }
                    }

                }catch (Exception e) {
                        System.out.println("Only numbers!");
                        sellDragons();
                    }

        } else {
            System.out.println("Unfortunately you have nothing to sell.\nTry something else maybe?");
            game.menuChoice(player);
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
            System.out.println(counter + ". " + foodForSale.get(food).getClass().getSimpleName() + " at price " +
                    foodForSale.get(food).price + " kr/kg" + "." +
                    " [MAX: " + (player.getMoneyBalance()/foodForSale.get(food).price) + " kg.]");
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
            System.out.println("You can buy more food if you want!");
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

    public void setBalance(String dragon){
        player.setMoneyBalance(player.getMoneyBalance() - dragonsForSale.get(dragon).dragonPrice);
    }
}
