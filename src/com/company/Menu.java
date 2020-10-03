package com.company;

import java.util.Scanner;

public class Menu {
    private boolean isRunning = true;
    // default names of players
    private static String[] names = {"Player 1", "Player 2", "Player 3", "Player 4"};



    public Menu(String ...newNames){
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





            //restart the game
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


    static Scanner scanner = new Scanner(System.in);

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
