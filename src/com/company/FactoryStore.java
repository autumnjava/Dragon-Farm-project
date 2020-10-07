package com.company;

import java.util.*;

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
        dragonsForSale.put("Smaug", new Falkor());
        dragonsForSale.put("Toothless", new Toothless());
        dragonsForSale.put("Viserion", new Viserion());
    }

    public ArrayList<String> dragonsYouCanBuy(){
        System.out.println("Dragons you can buy:");
        ArrayList<String> dragonsAvailable = new ArrayList<>();

            for (var dragon: dragonsForSale.keySet()){
                if(player.getMoneyBalance() >= dragonsForSale.get(dragon).getDragonPrice()){
                    dragonsAvailable.add(dragon);
                    System.out.println(dragonsAvailable.size() + ". " + dragon + " at price " +
                            dragonsForSale.get(dragon).getDragonPrice());
                }
            }

        if(dragonsAvailable.size() == 0){
            System.out.println("You cant buy anything, sorry!");
            //jumps to next player
        }
return dragonsAvailable;
    }

    public static String askName(){
        return Game.prompt("Enter a name of a dragon:");
    }

    public static String askGender(){
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
}
