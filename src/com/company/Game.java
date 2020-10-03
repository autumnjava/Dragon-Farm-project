package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private int currentRound = 1;
    private int maxRounds = 30;
    private final int minRounds = 5;
    public static int currentPlayer = 1;
    private FactoryStore factoryStore = new FactoryStore();

    private ArrayList<Player> players = new ArrayList<>();


    public Game(String ...newNames) {

        new Menu();

    }









}
