package com.company;

public class Falkor extends Dragon {
    public static int initialPrice = 5000;

    public Falkor(String name, String gender){
        super(name, gender);
    }

    public void fly(){
        System.out.println("Falkor " + this.name + " flies! You better fly high because you cost " + initialPrice + " kr.");
    }


}
