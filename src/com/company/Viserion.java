package com.company;

public class Viserion extends Dragon {
    public static int initialPrice = 8000;
    public Viserion(String name, String gender){
        super(name, gender);
    }

    public void fly(){
        System.out.println("Viserion flies!");
    }

}
