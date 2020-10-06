package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class FactoryStore {
    public HashMap<Object, Integer> dragonPrice = new HashMap<>();
    public ArrayList<Dragon> usedDragons = new ArrayList<>();
    public ArrayList<Player> players = new ArrayList<>();
    private int price;


    public static Dragon askAndCreateDragon(Player player) {
        var input = Game.prompt("Choose a dragon:\na. Lockheed price: \nb. Falkor\nc. Smaug" +
                "\nd. Toothless\ne. Viserion");
        var inputChar = input.charAt(0);
        if (inputChar != 'a' && inputChar != 'b' && inputChar != 'c'
                && inputChar != 'd' && inputChar != 'e') {
            System.out.println("ERROR! try again");
            askAndCreateDragon(player);
        } else {
            switch (inputChar) {
                case 'a' -> {
                    System.out.println("Ok so you want to buy a Lockheed");
                    //check if player has enough cash
//                    if(player.getMoneyBalance() < Dragon.p){
//                        System.out.println("Not enough money!");
//                    } else
                    return new Lockheed(askName(), askGender());
                }
                case 'b' -> {
                    return new Falkor(askName(), askGender());
                }
                case 'c' -> {
                    return new Smaug(askName(), askGender());
                }
                case 'd' -> {
                    return new Toothless(askName(), askGender());
                }
                case 'e' -> {
                    return new Viserion(askName(), askGender());
                }
            }
        }
        //Theoretically we should not get here.
        return null;
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
            askGender();
        }
            return null; //in theory we shall not get here!
        }


}
