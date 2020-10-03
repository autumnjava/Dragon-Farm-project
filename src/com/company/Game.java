package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private int currentRound = 1;
    private int maxRounds = 30;
    private final int minRounds = 5;
    private int currentPlayer = 1;
    private FactoryStore factoryStore = new FactoryStore();
    private String input; // as a field since it must survive between method calls to main

    private ArrayList<Player> players = new ArrayList<>();
    private boolean isRunning = true;

    private String[] names = {"Player 1", "Player 2", "Player 3", "Player 4"}; // default names of players


    public Game(String ...newNames) {
        // start the game
        names = newNames.length < 4 ? names : newNames;
        Menu.print("\n".repeat(20) + "Welcome to Dragon Farm\n" + "-".repeat(30));
        Menu.print("created by Aleksandr S.\n2020 Malmö.\n" + "-".repeat(30));


        while (isRunning) {
            System.out.println("\n\nEnter how many users are going to play this game [1-4]");
            try {
                var userInput = scanner.nextLine();
                int input = Integer.parseInt(userInput);
                if (input < 1 || input > 4) {
                    System.out.println("ONLY DIGITS [1-4]! Try again");
                    continue;
                } else {
                    System.out.println("You have entered " + input);
                    for(var i = 0; i < input; i++){
                        names[i] = Menu.prompt("Player " + (i + 1) + " name"
                                + " (space + enter for \"" + names[i] + "\"):", names[i]);
                    }
                }
            } catch (Exception e) {
                System.out.println("ONLY DIGITS [1-4]! Try again");
                continue;
            }

            System.out.println(names[0]);
            System.out.println(names[1]);
            System.out.println(names[2]);
            System.out.println(names[3]);
            main();

        }
    }


        private void main() {
                    if(Menu.prompt("Play again? (y/n)?").equals("n")){
                        System.exit(0);
                    }
                    // create a new game
                    new Game(names);
                }







    public String getPlayer() {
        if (currentPlayer == 1)
            return names[0];
        else if (currentPlayer == 2)
            return names[1];
        else if (currentPlayer == 3)
            return names[2];
        else if (currentPlayer == 4)
            return names[3];
        else
            return null;
    }

}
