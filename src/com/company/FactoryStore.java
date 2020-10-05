package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class FactoryStore {
    public String nameOfStore = "Dragons and Food Outlet Factory";
    public HashMap<String, Integer> dragonPrice = new HashMap<>();
    public ArrayList<Dragon> usedDragons = new ArrayList<>();
    public ArrayList<Player> players = new ArrayList<>();
    private int price;


    public static Dragon askAndCreateDragon() {
        var input = Game.prompt("Choose a dragon:\na. Lockheed\nb. Falkor\nc. Smaug" +
                "\nd. Toothless\ne. Viserion");
        var inputChar = input.charAt(0);
        if (inputChar != 'a' && inputChar != 'b' && inputChar != 'c'
                && inputChar != 'd' && inputChar != 'e') {
            System.out.println("ERROR! try again");
            askAndCreateDragon();
        } else {
            var name = Game.prompt("Enter a name of a dragon:");
            var gender = Game.prompt("Enter a gender [male/female]:");
            if (!gender.equals("male") && !gender.equals("female")) {
                System.out.println("Wrong input! Try again");
                gender = Game.prompt("Enter a gender [male/female]:");
            }
            switch (inputChar) {
                case 'a' -> {
                    return new Lockheed(name, gender);
                }
                case 'b' -> {
                    return new Falkor(name, gender);
                }
                case 'c' -> {
                    return new Smaug(name, gender);
                }
                case 'd' -> {
                    return new Toothless(name, gender);
                }
                case 'e' -> {
                    return new Viserion(name, gender);
                }
            }
        }
        return null;
    }
}
