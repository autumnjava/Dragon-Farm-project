package com.company;

import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static int usersInput, roundsInput;

    private boolean isRunning = true;
    // default names of players
    private static String[] names = {"Player 1", "Player 2", "Player 3", "Player 4"};



    public Menu(String ...newNames) {
        // start the game
        names = newNames.length < 4 ? names : newNames;


        while (isRunning) {
            howManyUsers();
            howManyRounds();
            isRunning= false;
        }

    }

    public static int howManyUsers(){
        usersInput = 0;
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
                        names[i] = Menu.prompt("Player " + (i + 1) + " name"
                                + " (space + enter for \"" + names[i] + "\"):", names[i]);
                    }
                }

            } catch (Exception e) {
                print(error);
                howManyUsers();
            }
        return usersInput;
    }

    private static int howManyRounds() {
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
        return roundsInput;

    }

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

    public static String getPlayer() {
        if (Game.currentPlayer == 1)
            return names[0];
        else if (Game.currentPlayer == 2)
            return names[1];
        else if (Game.currentPlayer == 3)
            return names[2];
        else if (Game.currentPlayer == 4)
            return names[3];
        else
            return null;
    }


    public static String[] getNames(){
        return names;
    }


}
