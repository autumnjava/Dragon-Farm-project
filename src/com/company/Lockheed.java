package com.company;

public class Lockheed extends Dragon {
    private int initialPrice = 4000;

    public Lockheed(String name, String gender){
        super(name, gender);
    }

    public void fly(){
        System.out.println("Lockheed flies!");
    }
}
