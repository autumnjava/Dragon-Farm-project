package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static Scanner scanner = new Scanner(System.in);
    private FactoryStore factoryStore = new FactoryStore();
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Dragon> dragonsOwned = new ArrayList<>();
    private ArrayList<Food> foodOwned = new ArrayList<>();
    private boolean isRunning = true;
    private static String[] names = {"Player 1", "Player 2", "Player 3", "Player 4"};
    private static int usersInput, roundsInput;

    public Game(String ...newNames) {
        print("\n".repeat(20) + "Welcome to Dragon Farm\n" + "-".repeat(30));
        print("created by Aleksandr S.\n2020 Malmö.\n" + "-".repeat(30));
        // start the game
        names = newNames.length < 4 ? names : newNames;

        howManyUsers();
        howManyRounds();

/*        System.out.println("test see if we get here:");
        System.out.println(roundsInput + " roundsInput static int ");
        System.out.println(usersInput + " usersInput static int");
        */

        createPlayers(); //creating players ArrayList

        main();

    }


    private void main() {
        int roundsCounter = 1;
        do{
            for(var player: players){
                print("\n".repeat(20) + "-".repeat(50));
                print( "Current round number: " + roundsCounter);
                print("Right now playing: " + player.getName().toUpperCase() +
                        ". Your balance is: " + player.getMoneyBalance());
                System.out.print(dragonsOwned.size() > 0 ? "Dragons owned : " + dragonsOwned + "\n" : "");
                System.out.print(foodOwned.size() > 0 ? "Food owned : " + foodOwned + "\n" : "");
                print("-".repeat(50));
                menuChoice();
            }
            roundsCounter++;
        } while(roundsCounter <= roundsInput);

        System.out.println("We have now played " + roundsInput + " rounds. Which was maximum for this game.");

        playAgain(); //asks user if he wants to play again and creates a new game if so.


    }

    public void howManyUsers(){
        String error = "ONLY DIGITS [1-4]! Try again";
        print("\n\nEnter how many users are going to play this game [1-4]");
        try {
            var userInput = scanner.nextLine();
            usersInput = Integer.parseInt(userInput);
            if (usersInput < 1 || usersInput > 4) {
                System.out.println(error);
                howManyUsers();
            } else {
                print("You have entered " + usersInput);

                for(var i = 0; i < usersInput; i++){
                    names[i] = prompt("Player " + (i + 1) + " name"
                            + " (space + enter for \"" + names[i] + "\"):", names[i]);
                }
            }

        } catch (Exception e) {
            print(error);
            howManyUsers();
        }
    }

    private void howManyRounds() {
        String error = "ONLY DIGITS [5-30]! Try again";
        try {
            print("Enter how many rounds you want to play! [5-30].");
            var userInput = scanner.nextLine();
            roundsInput = Integer.parseInt(userInput);
            if (roundsInput < 5 || roundsInput > 30) {
                System.out.println(error);
                howManyRounds();
            } else {
                print("You have entered " + roundsInput);
            }

        } catch (Exception e) {
            System.out.println(error);
            howManyRounds();
        }
    }

    public void createPlayers(){
        System.out.println("Creating player(s)");
        for(int i = 0; i < usersInput; i++){
            System.out.println(names[i]);
            players.add(new Player(names[i]));
        }
    }

    public void menuChoice(){
        System.out.println("You will now get 5 menu choices:");
        System.out.println("a. Buy dragons" +
                "\nb. Buy food" +
                "\nc. Feed dragons" +
                "\nd. Pair dragons (50-50 chance to succeed)" +
                "\ne. Sell dragons");
        var input = scanner.nextLine();
        switch (input) {
            case "a" -> print("You decided to buy dragons!");
            case "b" -> print("You decided to buy food");
            case "c" -> print("You decided to feed your dragons");
            case "d" -> print("You decided to pair dragons");
            case "e" -> print("You decided to sell dragons");
            default -> {
                print("Wrong input! Try again!\n"); //start the method again
                menuChoice();
            }
        }
    }


    public void playAgain(){
        var input = (prompt("Play again? (y/n)?"));
                switch(input){
            case "y" -> new Game();
            case "n" -> System.exit(0);
            default -> {
                print("Wrong input! Try again");
                playAgain();
            }
                }
    }


    //just a bunch of help methods
    static public void print(String x){
        // print a string if it is not empty
        if(!x.equals("")){ System.out.println(x); }
    }

    static public String prompt(String message, String ..._default){
        // prompt the user for an answer - use default answer (if such) if none
        print(message);
        var answer = scanner.nextLine().trim(); // trim removes spaces at start & end
        return answer.equals("") && _default.length > 0 ? _default[0] : answer;
    }





    }
