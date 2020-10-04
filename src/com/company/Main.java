package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //new Game();

        //testing to create new Dragons
        Dragon[] temp = {
                new Falkor("Falkorito", "male"),
                new Falkor("Ok", "male")
        };
        ArrayList dragons = new ArrayList(Arrays.asList(temp));

        System.out.println("Asking dragons to fly");

        for(var dragon: dragons){
            var className = dragon.getClass().getSimpleName();
            switch(className){
                case "Falkor" -> ((Falkor) dragon).fly();
                //case "Cat" -> ((Cat) animal).meow();
            }
        }


    }
}
