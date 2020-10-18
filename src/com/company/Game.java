package com.company;

import java.util.*;

public class Game {
    static Scanner scanner = new Scanner(System.in);
    private static String[] names = {"Player 1", "Player 2", "Player 3", "Player 4"};
    private static int roundsInput;
    private static int roundsCounter = 1;

    private final ArrayList<Player> players = new ArrayList<>();


    public Game(String... newNames) {
        print("\n".repeat(20) + "Welcome to Dragon Farm\n" + "-".repeat(30));
        print("created by Aleksandr S.\n2020 Malmö.\n" + "-".repeat(30));
        // start the game
        names = newNames.length < 4 ? names : newNames;

        int usersInput = Game.promptInt("\n\nEnter how many users are going to play this game [1-4]", 1, 4);
        for(var i = 0; i < usersInput; i++){
            names[i] = prompt("Player " + (i + 1) + " enter your name: "
                    + " (space + enter for \"" + names[i] + "\"):", names[i]);
        }

        //creating players
        for (int i = usersInput-1; i >= 0 ; i--) {
            System.out.println("Creating player: " + names[i]);
            players.add(new Player(names[i], this));
        }
        roundsInput = Game.promptInt("How many rounds do you want to play? [5-30]", 5, 30); //ask user how many rounds they want to play
        makeMove();
        sellDragonsPrintWinners();


        playAgain(); //asks user if he wants to play again and creates a new game if so.

    }

    public void makeMove(){
        roundsCounter = 1;
        do {
            for (int i = players.size()-1; i>=0; i--) {
                var player = players.get(i);
                if(player.getMoneyBalance() <= 0 && player.dragonsOwned.size()==0){
                    System.out.println("Player " + player.getName() + " looks like it is over for you. Bye bye.");
                    players.remove(player);
                    continue;
                }
                if(players.size() > 0){
                    menuChoice(player);
                    for(var dragon: player.dragonsOwned){
                        if(!dragon.beenFed && roundsCounter != roundsInput){
                            dragon.decreaseHealthOfDragon(); //dont do that if dragon been fed in previous round
                                                             //or it is last round
                        }
                    }
                }
            }
            roundsCounter++;
        } while (roundsCounter <= roundsInput);

    }

    public void menuChoice(Player player) {
        print("\n".repeat(20) + "-".repeat(50));
        print("Current round number: " + roundsCounter + "/" + roundsInput);
        print("Right now playing: " + player.getName().toUpperCase() +
                ". Your balance is: " + player.getMoneyBalance() + " kr");
        player.getAllDragons();
        player.getAllFood();

        print("-".repeat(50));

        System.out.println(player.getName() + ", you will now get 5 menu choices:");
        System.out.println("a. Buy dragons" +
                "\nb. Buy food" +
                "\nc. Feed dragons" +
                "\nd. Pair dragons (50-50 chance to succeed)" +
                "\ne. Sell dragons" +
                "\nx. Do nothing. Jump to next player"
        );
        var input = scanner.nextLine();
        switch (input) {
            case "a" -> {
                FactoryStore store = new FactoryStore(this, player);
                store.buyDragons();
            }
            case "b" -> {
                FactoryStore store = new FactoryStore(this, player);
                store.buyFood();
            }
            case "c" -> player.feedDragons();
            case "d" -> player.mateDragons();
            case "e" -> {
                FactoryStore store = new FactoryStore(this, player);
                store.sellDragons();
            }
            case "x" -> print("Better luck next time!");
            default -> {
                print("Wrong input! Try again!\n");
                menuChoice(player);
            }
        }
    }

    public void sellDragonsPrintWinners(){
        if(players.size()>0){
            //selling dragons
            for(var player: players){
                FactoryStore store = new FactoryStore(this, player);
                store.sellAllDragons();
            }
            players.sort((Player a, Player b) -> a.getMoneyBalance() > b.getMoneyBalance() ? -1 : 1);
            int c = 1;
            System.out.println("\n".repeat(20) + "Game ended!\nPrinting out the winner(s): ");
            for(var player: players){
                System.out.println(c++ + ". " + player.getName() + " balance: " + player.getMoneyBalance());
            }
        } else{
            System.out.println("\n".repeat(20) +"No winners today! Better luck next time!");
        }
    }


    public void playAgain(){
        var input = (prompt("\n".repeat(5) + "Play again? (y/n)?"));
        switch(input){
            case "y" -> new Game();
            case "n" -> System.exit(0);
            default -> {
                print("Wrong input! Try again");
                playAgain();
            }
        }
    }

    //just a bunch of help methods
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

    static public int promptInt(String question, int min, int max){
        var num = min - 1;
        try {
            num = Integer.parseInt(prompt(question));
        }
        catch(Exception ignore){}
        // if illegal choice show the prompt again (recursion)
        // otherwise return the choice
        return num < min || num > max ?
                promptInt(question, min, max) : num;
    }
}
