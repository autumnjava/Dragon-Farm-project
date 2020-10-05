package com.company;

import java.util.ArrayList;

public class Player {

    private String name;
    private int moneyBalance = 15000; //start summa
    private ArrayList<Dragon> dragonsOwned = new ArrayList<>();
    private ArrayList<Food> foodOwned = new ArrayList<>();
    private Game game;

    public Player(String name){
        this.name = name;
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

    public ArrayList<Dragon> getDragonsOwned() {
        return dragonsOwned;
    }

    public ArrayList<Food> getFoodOwned() {
        return foodOwned;
    }

    public void buyDragon(){
        var input = Game.prompt("Choose a dragon:\na. Lockheed\nb. Falkor\nc. Smaug" +
                "\nd. Toothless\ne. Viserion");
        var inputChar = input.charAt(0);
        if(inputChar != 'a' && inputChar != 'b' && inputChar != 'c'
        && inputChar != 'd' && inputChar != 'e') {
            System.out.println("ERROR! try again");
            buyDragon();
        } else {
            System.out.println("Creating dragon!");
            askAndCreateDragon(inputChar);
        }
    }

    public void askAndCreateDragon(char input){
        var name = Game.prompt("Enter a name of a dragon:");
        var gender = Game.prompt("Enter a gender [male/female]:");
        if(!gender.equals("male") && !gender.equals("female")){
            System.out.println("Wrong input! Try again");
            gender = Game.prompt("Enter a gender [male/female]:");
        }
        switch(input){
            case 'a' -> this.dragonsOwned.add(new Lockheed(name, gender));
            case 'b' -> this.dragonsOwned.add(new Falkor(name, gender));
            case 'c' -> this.dragonsOwned.add(new Smaug(name, gender));
            case 'd' -> this.dragonsOwned.add(new Toothless(name, gender));
            case 'e'-> this.dragonsOwned.add(new Viserion(name, gender));
            default -> System.out.println("Error");
        }
    }

    public void getAllDragonsNames(){
        for(var dragon: dragonsOwned){
            System.out.println(dragon.presentYourself());
        }
    }
}
