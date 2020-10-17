package com.company;

import com.company.DragonSubclasses.*;

import java.util.*;

public class Player {
    private String name;
    private Game game;
    private int moneyBalance = 20000; //start amount of cash
    protected ArrayList<Dragon> dragonsOwned;
    protected ArrayList<Food> foodOwned;

    public Player(String name, Game game){
        this.name = name;
        this.game = game;
        this.dragonsOwned = new ArrayList<Dragon>();
        this.foodOwned = new ArrayList<Food>();
    }

    public String getName() {
        return name;
    }

    public int getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(int moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public void findAndRemoveSickDragons(){
        for(int i = dragonsOwned.size()-1; i>= 0; i--){
            if(dragonsOwned.get(i).healthPercent<=0){
                System.out.println("Removing " + dragonsOwned.get(i).name +
                        " as its health dropped below 0");
                dragonsOwned.remove(dragonsOwned.get(i));
            }
        }
    }

    public void getAllDragons(){
        if(dragonsOwned.size() > 0){
            Game.print("-".repeat(50));
            System.out.println("NOTE: if dragons health drops below 0, dragon dies.");
            findAndRemoveSickDragons();
            for(var dragon: dragonsOwned){
                System.out.println(dragon.name.toUpperCase() + " initial price " + dragon.dragonPrice + " its health: " + dragon.healthPercent +
                        " current price: " + dragon.currentPrice() + " kr.");
            }
            Game.print("-".repeat(50));
        } else {
            Game.print("-".repeat(50));
            System.out.println("You don't have any dragons.");
            Game.print("-".repeat(50));
        }
    }

    public void getAllFood(){
        if(foodOwned.size() > 0){
            for(var food: foodOwned){
                System.out.println(food.getClass().getSimpleName() + " at price " + food.price + " kr/kg. Amount: " + food.weight + " kg. SUM: " + food.weight*food.price);
            }
        } else {
            System.out.println("You don't have any food.");
        }
    }

    public void feedDragons(){
        if(dragonsOwned.size()>0 && foodOwned.size()>0){
            System.out.println("\n".repeat(20) + "You decided to feed your dragons! They are hungry!\n\nYour dragons:");
            int counter = 1;
            Game.print("-".repeat(50));
            for(var dragon: dragonsOwned) {
                System.out.println(counter + ". " + dragon.name + ". Its health: " + dragon.healthPercent
                        + ". It can eat: " + dragon.foodDragonCanEat);
                counter++;
            }
            Game.print("-".repeat(50));
            var inputInt = Game.promptInt("\n\nChoose a dragon to feed! [1-" + dragonsOwned.size() + "]" +
                    "\nNOTE: enter 0 to next player / next round", 0, dragonsOwned.size());

            if (inputInt == 0) { return; /* do nothing / skip round */ }
            var chosenDragon = dragonsOwned.get(inputInt-1);
            switch (chosenDragon.getClass().getSimpleName()){
                case "Lockheed", "Falkor", "Smaug", "Toothless", "Viserion" -> System.out.println(
                        "You decided to feed " + chosenDragon.name +
                                "\nIt can eat: " + chosenDragon.foodDragonCanEat);
                default -> throw new IllegalStateException("Unexpected value: " + chosenDragon.name);
            }

            var canEat = false;
            for(var eatable: chosenDragon.foodDragonCanEat){
                for(var ownable: foodOwned){
                    if (ownable.name.equals(eatable)) {
                        canEat = true;
                        break;
                    }
                }
            }
            if(canEat){
                System.out.println("\n".repeat(20) + "You have food that dragon can eat!\n");
                int c = 0;
                var foodMap = new HashMap<Integer, Food>();
                for(var food: chosenDragon.foodDragonCanEat){
                    for(var owned: foodOwned){
                        if(owned.name.equals(food)){
                            ++c;
                            foodMap.put(c, owned);
                            System.out.println(c + ". " + owned.name + " [You owe: " + owned.weight + " kg]");
                        }
                    }
                }

                var input = Game.promptInt("What type of food do you want to feed the dragon?", 1, c);
                var chosen = foodMap.get(input);
                int maxKg = (100 - chosenDragon.healthPercent) / 10;
                System.out.println("\n\nYou chose " +  chosen.name + ", I owe: " + chosen.weight + " kg \n" +
                        "Dragon " + chosenDragon.name + " can eat max: " + maxKg + " kg of " + chosen.name + "\n\n");

                var weight = Game.promptInt("Enter amount: ", 1, chosen.weight);
                var hungry = true;

                if(weight > maxKg) {
                    hungry = false;
                }

                if(!hungry){
                    System.out.println("Dragon is not that hungry!\n");
                    System.out.println(chosenDragon.name + " can max eat: " + maxKg + " kg of " + chosen.name);
                    feedDragons();
                }
                else{
                    chosenDragon.setHealthPercent(chosenDragon.healthPercent + 10*weight);
                    foodMap.get(input).setWeight(chosen.weight-weight);
                    chosenDragon.beenFed = true;
                }

                for(int i = foodOwned.size()-1; i >= 0; i--){
                    if(foodOwned.get(i).weight == 0){
                        System.out.println(foodOwned.get(i).name + " weight became 0. Removing from the list");
                        foodOwned.remove(foodOwned.get(i));
                    }
                }

            } else {
                System.out.println("You don't have food that dragon can eat! Try something else.");
                game.menuChoice(this);
            }

        } else {
            System.out.println("You have no dragons/no food. Try something else.");
            game.menuChoice(this);
        }
    }


    public void mateDragons(){
        if(dragonsOwned.size()>=2){
            System.out.println("\n".repeat(20) + "Your dragons: ");
            System.out.println("-".repeat(50));
            int counter = 1;
            for(var dragon: dragonsOwned){
                System.out.println(counter++ + ". Name: " + dragon.name + ". Gender: " + dragon.gender + ""
                + ". Dragon class: " + dragon.getClass().getSimpleName().toLowerCase());
            }
            System.out.println("-".repeat(50));
            var input1 = Game.promptInt("\n".repeat(2) + "Chose one dragon to mate!\n" +
                    "NOTE: you can only mate same types of dragons!\n" +
                    "[enter 0 to next player / next round]", 0, dragonsOwned.size());

            if (input1 == 0) { return; /* do nothing / skip round */ }
            var dragon1 = dragonsOwned.get(input1-1);

            var input2 = Game.promptInt("\n".repeat(2) + "Chose another dragon to mate!\n" +
                    "NOTE: You can't mate two dragons of the same gender!\n" +
                    "[enter 0 to next player / next round]", 0, dragonsOwned.size());
            var dragon2 = dragonsOwned.get(input2-1);
            if (input2 == 0) { return; /* do nothing / skip round */ }

            if(dragon1==dragon2) {
                System.out.println("No cheating! You are trying to mate the same dragon!");
                mateDragons();
            } else if(dragon1.gender.equals(dragon2.gender) && dragon1.getClass() == dragon2.getClass()){
                System.out.println("No homo in this game!");
                mateDragons();
            } else if(dragon1.getClass()!=dragon2.getClass()){
                System.out.println("It has to be two dragons of the same type!");
                mateDragons();
            } else {
                System.out.println("Trying to pair dragons!\nRemember its 50-50 chance to get baby dragons");
                var random = (int) (Math.random() * (2));
                if(random == 0){
                    System.out.println("Better luck next time!");
                } else {
                    System.out.println("The luck is on your side!\nCreating new dragons!\n");
                    var dragonClass = dragon1.getClass().getSimpleName();
                    var litterSize = dragon1.litterSize;
                    Dragon [] babyDragons = new Dragon[litterSize];

                    switch(dragonClass){
                        case "Lockheed" -> {
                            for(int i = 0; i<litterSize; i++){
                                babyDragons[i] = new Lockheed(askName(i, litterSize), generateGender(), this);
                            }
                        }
                        case "Falkor" -> {
                            for(int i = 0; i<litterSize; i++){
                                babyDragons[i] = new Falkor(askName(i, litterSize), generateGender(), this);
                            }
                        }
                        case "Smaug" -> {
                            for(int i = 0; i<litterSize; i++){
                                babyDragons[i] = new Smaug(askName(i, litterSize), generateGender(), this);
                            }
                        }
                        case "Toothless" -> {
                            for(int i = 0; i<litterSize; i++){
                                babyDragons[i] = new Toothless(askName(i, litterSize), generateGender(), this);
                            }
                        }
                        case "Viserion" -> {
                            for(int i = 0; i<litterSize; i++){
                                babyDragons[i] = new Viserion(askName(i, litterSize), generateGender(), this);
                            }
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + dragonClass);
                    }
                    dragonsOwned.addAll(Arrays.asList(babyDragons));
                }
            }
        } else {
            System.out.println("You need atleast two animals to pair them! Try something else.");
            game.menuChoice(this);
        }
    }

    public String askName(int i, int litterSize){
        var name = Game.prompt("Enter a name of a baby dragon nr " + (i+1) + " out of " + litterSize);
        return name.length() > 2 ? name : askName(i, litterSize);
    }

    public String generateGender(){
        return (int) (Math.random() * (2)) == 0 ? "male" : "female";
    }
}
