package com.company;

import java.util.ArrayList;

public class Game {
    private int currentRound = 1;
    private int maxRounds = 30;
    private final int minRounds = 5;
    private FactoryStore factoryStore = new FactoryStore();

    private ArrayList<Player> players = new ArrayList<>();
    private boolean isRunning = true;


}
