package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public static int currentPlayer = 1;
    private FactoryStore factoryStore = new FactoryStore();

    private ArrayList<Player> players = new ArrayList<>();


    public Game(String ...newNames) {
        Menu.print("\n".repeat(20) + "Welcome to Dragon Farm\n" + "-".repeat(30));
        Menu.print("created by Aleksandr S.\n2020 Malmö.\n" + "-".repeat(30));
        new Menu();
        int test = 0;

        System.out.println("See if we get here?");
        for(int i = 0; i < Menu.input; i++){
            System.out.println(Arrays.toString(Menu.getNames()));
        }

        main(); //restart the game

    }


    private void main() {
        if(Menu.prompt("Play again? (y/n)?").equals("n")){
            System.exit(0);
        }
        // create a new game
        new Game(Menu.getNames());
    }

    }
