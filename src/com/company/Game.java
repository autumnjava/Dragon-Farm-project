package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public static int currentPlayer = 1;
    private FactoryStore factoryStore = new FactoryStore();
    private ArrayList<Player> players = new ArrayList<>();


    public Game() {
        Menu.print("\n".repeat(20) + "Welcome to Dragon Farm\n" + "-".repeat(30));
        Menu.print("created by Aleksandr S.\n2020 Malmö.\n" + "-".repeat(30));
        new Menu();
        createPlayers();


        System.out.println("Testing:");
        System.out.println(players.size());
        System.out.println(players.get(0).getName());

        main(); //restart the game

    }


    private void main() {
        if(Menu.prompt("Play again? (y/n)?").equals("n")){
            System.exit(0);
        }
        // create a new game
        new Game();
    }

    public void createPlayers(){
        System.out.println("Creating players");
        var names = Menu.getNames();
        for(int i = 0; i < Menu.usersInput; i++){
            System.out.println(names[i]);
            players.add(new Player(names[i]));
        }
    }

    }
