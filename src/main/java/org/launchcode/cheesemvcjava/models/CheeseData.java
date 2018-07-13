package org.launchcode.cheesemvcjava.models;

import java.util.ArrayList;

/**
 * Created by dogma on 6/29/2018.
 */
public class CheeseData {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    public static ArrayList<Cheese> getAll(){
        return cheeses;
    }

    public static void add(Cheese aCheese){
        cheeses.add(aCheese);
    }

    public static void remove(int id){
        Cheese delCheese = getById(id);
        cheeses.remove(delCheese);
    }

    public static Cheese getById(int id){
        for (Cheese cheese : cheeses){
            if (cheese.getCheeseId() == id) return cheese;
        }
        return null;
    }
}
