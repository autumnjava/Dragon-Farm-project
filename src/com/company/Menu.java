package com.company;

import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    private boolean isRunning = true;
    // default names of players
    private static String[] names = {"Player 1", "Player 2", "Player 3", "Player 4"};



    public Menu(String ...newNames){
        // start the game
        names = newNames.length < 4 ? names : newNames;


        while(isRunning){
            howManyUsers();
            System.out.println("Test:");
            System.out.println(names[0]);
            System.out.println(names[1]);
            System.out.println(names[2]);
            System.out.println(names[3]);

            howManyRounds();
        }

        main(); //restart the game

    }


    private void main() {
        if(Menu.prompt("Play again? (y/n)?").equals("n")){
            System.exit(0);
        }
        // create a new game
        new Game(names);
    }

    private void howManyUsers(){
        String error = "ONLY DIGITS [1-4]! Try again";
            print("\n\nEnter how many users are going to play this game [1-4]");
            try {
                var userInput = scanner.nextLine();
                int input = Integer.parseInt(userInput);
                if (input < 1 || input > 4) {
                    System.out.println(error);
                    howManyUsers();
                } else {
                    print("You have entered " + input);
                    for(var i = 0; i < input; i++){
                        names[i] = Menu.prompt("Player " + (i + 1) + " name"
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
                print("Enter how many rounds you want to play! [5-30]");
                var userInput = scanner.nextLine();
                int input = Integer.parseInt(userInput);
                if (input < 5 || input > 30) {
                    System.out.println(error);
                    howManyRounds();
                } else {
                    print("You have entered " + input);
                }

            } catch (Exception e) {
                System.out.println(error);
                howManyRounds();
            }

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


}
