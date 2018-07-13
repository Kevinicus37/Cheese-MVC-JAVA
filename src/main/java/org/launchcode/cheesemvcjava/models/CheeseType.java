package org.launchcode.cheesemvcjava.models;

public enum CheeseType {

    HARD ("Hard"),
    SOFT ("Soft"),
    FAKE ("Fake");

    private final String name;

    CheeseType(String aName){
        name = aName;
    }

    public String getName(){
        return name;
    }
}
