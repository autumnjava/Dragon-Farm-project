package com.company;

import java.util.ArrayList;

public class Game {
    private int currentRound = 1;
    private int maxRounds = 30;
    private final int minRounds = 5;
    private int currentPlayer = 1;
    private FactoryStore factoryStore = new FactoryStore();

    private ArrayList<Player> players = new ArrayList<>();
    private boolean isRunning = true;

    private String[] names = {"Player 1", "Player 2", "Player 3", "Player 4"}; // default names of players


    public Game(String ...newNames){
        // start the game
        names = newNames.length < 4 ? names: newNames;
        Menu.print ("\n".repeat(20) + "Welcome to Dragon Farm\n" + "-".repeat(30));
        Menu.print("created by Aleksandr S.\n2020 Malmö.");




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
