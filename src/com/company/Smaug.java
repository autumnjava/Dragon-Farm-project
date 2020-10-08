package com.company;

public class Smaug extends Dragon{
    public Smaug(){
        super(null, null, null, 6000, 100, 0, 5, "Meat, fish");
    }
    public Smaug(String name, String gender, Player owner){
        super(name, gender, owner, 6000, 100, 0, 5, "Meat, fish");
    }
}
