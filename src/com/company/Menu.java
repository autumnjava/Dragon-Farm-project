package com.company;

import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);


    private boolean isRunning = true;
    // default names of players




    public Menu(String ...newNames) {
        // start the game
        names = newNames.length < 4 ? names : newNames;

            howManyUsers();
            howManyRounds();

    }



/*    public static String getPlayer() {
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
    }*/





}
