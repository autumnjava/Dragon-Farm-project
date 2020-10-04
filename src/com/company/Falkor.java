package com.company;

public class Falkor extends Dragon {
    private int price = 4000;

    public Falkor(String name, String gender){
        super(name, gender);
    }

    public void fly(){
        System.out.println("Falkor " + this.name + " flies! You better fly high because you cost " + price + " kr.");
    }


}
