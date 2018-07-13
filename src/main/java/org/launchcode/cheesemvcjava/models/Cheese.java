package org.launchcode.cheesemvcjava.models;

import javax.validation.constraints.*;

/**
 * Created by dogma on 6/15/2018.
 */
public class Cheese {

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message="Description must not be empty.")
    private String description;

    @Min(1)
    @Max(5)
    private int rating;

    private int cheeseId;
    private CheeseType type;


    private static int nextId = 1;

    public Cheese(String aName, String aDescription){
        this();
        name = aName;
        description = aDescription;
    }



    public Cheese(){
        cheeseId = nextId;
        nextId++;
    }

    public int getCheeseId() { return cheeseId; }

    public void setCheeseId(int cheeseId) { this.cheeseId = cheeseId; }

    public Cheese(String aName){
        this(aName, "");
    }

    public String getName(){
        return name;
    }

    public void setName(String aName){
        name = aName;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String aDescription){
        description = aDescription;
    }

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
