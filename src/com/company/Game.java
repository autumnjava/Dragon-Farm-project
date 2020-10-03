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
        Menu.print("\n".repeat(20) + "Welcome to Dragon Farm\n" + "-".repeat(30));
        Menu.print("created by Aleksandr S.\n2020 Malmö.\n" + "-".repeat(30));
        new Menu();

    }









}
