package com.company;

import java.util.*;
import java.util.concurrent.locks.Lock;

public class FactoryStore {
    private Player player;
    private Game game;
    public LinkedHashMap<String, Dragon> dragonsForSale;
    public LinkedHashMap<String, Food> foodForSale;

    public FactoryStore(Game game, Player player){
        System.out.println("Welcome to the store, " + player.getName());
        this.game = game;
        this.player = player;
        createDragons();
    }

    private void createDragons(){
        dragonsForSale = new LinkedHashMap<>();
        dragonsForSale.put("Lockheed", new Lockheed());
        dragonsForSale.put("Falkor", new Falkor());
        dragonsForSale.put("Smaug", new Smaug());
        dragonsForSale.put("Toothless", new Toothless());
        dragonsForSale.put("Viserion", new Viserion());
    }

    public ArrayList<String> dragonsYouCanBuy(){
        ArrayList<String> dragonsAvailable = new ArrayList<>();

            for (var dragon: dragonsForSale.keySet()){
                if(player.getMoneyBalance() >= dragonsForSale.get(dragon).getDragonPrice()){
                    dragonsAvailable.add(dragon);
                }
            }
return dragonsAvailable;
    }

    public void buyDragons(){
        if(dragonsYouCanBuy().size()>0 && player.getMoneyBalance()>0){
            int counter = 1;
            System.out.println("\n".repeat(20) + "Dragons you can buy:");
            for(var dragon: dragonsYouCanBuy()){
                System.out.println(counter + ". " + dragon + " at price " +
                        dragonsForSale.get(dragon).getDragonPrice());
                counter++;
            }
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
                        player.getDragonsOwned().add(new Lockheed(askName(), askGender(), player));
                        setBalance("Lockheed");

                        if(dragonsYouCanBuy().size() > 0) {
                            System.out.println("\n".repeat(20) + "You can buy more dragons if you want!");
                            buyDragons();
                        }

                    }
                    case 2 -> {
                        player.getDragonsOwned().add(new Falkor(askName(), askGender(), player));
                        setBalance("Falkor");

                        if(dragonsYouCanBuy().size() > 0) {
                            System.out.println("\n".repeat(20) + "You can buy more dragons if you want!");
                            buyDragons();
                        }
                    }
                    case 3 -> {
                        player.getDragonsOwned().add(new Smaug(askName(), askGender(), player));
                        setBalance("Smaug");

                        if(dragonsYouCanBuy().size() > 0) {
                            System.out.println("\n".repeat(20) + "You can buy more dragons if you want!");
                            buyDragons();
                        }
                    }
                    case 4 -> {
                        player.getDragonsOwned().add(new Toothless(askName(), askGender(), player));
                        setBalance("Toothless");
                        if(dragonsYouCanBuy().size() > 0) {
                            System.out.println("\n".repeat(20) + "You can buy more dragons if you want!");
                            buyDragons();
                        }

                    }
                    case 5 -> {
                        player.getDragonsOwned().add(new Viserion(askName(), askGender(), player));
                        setBalance("Viserion");
                        if(dragonsYouCanBuy().size() > 0) {
                            System.out.println("\n".repeat(20) + "You can buy more dragons if you want!");
                            buyDragons();
                        }

                    }
                    case 999 -> {
                        System.out.println("you decided to skip round/player");
                    }
                }

            } catch (Exception e) {
                System.out.println("Only numbers!");
                buyDragons();
            }
        } else {
            System.out.println("You cant buy anything, sorry!");
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
        player.setMoneyBalance(player.getMoneyBalance() - dragonsForSale.get(dragon).getDragonPrice());
    }


}
