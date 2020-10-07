package com.company;

public class Toothless extends Dragon{
    public static int initialPrice = 7000;
    public Toothless(){

    }
    public Toothless(String name, String gender){
        super(name, gender);
    }

    public void fly(){
        System.out.println("Toothless flies!");
    }

}
