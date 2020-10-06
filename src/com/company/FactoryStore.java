package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class FactoryStore {
    public HashMap<Object, Integer> dragonPrice = new HashMap<>();
    public ArrayList<Dragon> usedDragons = new ArrayList<>();
    public ArrayList<Player> players = new ArrayList<>();


    public static Dragon askAndCreateDragon(Player player) {

        var input = Game.prompt("Choose a dragon:\na. Lockheed [4000]\nb. Falkor [5000]\nc. Smaug [6000]" +
                "\nd. Toothless[7000]\ne. Viserion[8000]");
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
                    if (player.getMoneyBalance() < Lockheed.initialPrice) {
                        System.out.println("Not enough money!");
                    } else {
                        player.setMoneyBalance(player.getMoneyBalance() - Lockheed.initialPrice);
                        return new Lockheed(askName(), askGender());
                    }
                }
                case 'b' -> {
                    if (player.getMoneyBalance() < Falkor.initialPrice) {
                        System.out.println("Not enough money!");
                    } else {
                        player.setMoneyBalance(player.getMoneyBalance() - Falkor.initialPrice);
                        return new Falkor(askName(), askGender());
                    }
                }
                case 'c' -> {
                    if (player.getMoneyBalance() < Smaug.initialPrice) {
                        System.out.println("Not enough money!");
                    } else {
                        player.setMoneyBalance(player.getMoneyBalance() - Smaug.initialPrice);
                        return new Smaug(askName(), askGender());
                    }
                }
                case 'd' -> {
                    if (player.getMoneyBalance() < Toothless.initialPrice) {
                        System.out.println("Not enough money!");
                    } else {
                        player.setMoneyBalance(player.getMoneyBalance() - Toothless.initialPrice);
                        return new Toothless(askName(), askGender());
                    }
                }
                case 'e' -> {
                    if (player.getMoneyBalance() < Viserion.initialPrice) {
                        System.out.println("Not enough money!");
                    } else {
                        player.setMoneyBalance(player.getMoneyBalance() - Viserion.initialPrice);
                        return new Viserion(askName(), askGender());
                    }
                }
            }
        }
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
            return askGender();
        }
    }
}
