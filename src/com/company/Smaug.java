package com.company;

public class Smaug extends Dragon{
    public static int initialPrice = 6000;
    public Smaug(){

    }

    public Smaug(String name, String gender){
        super(name, gender);
    }

    public void fly(){
        System.out.println("Smaug flies!");
    }
}
