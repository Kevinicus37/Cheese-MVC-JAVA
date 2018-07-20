package org.launchcode.cheesemvcjava.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @ManyToMany
    private List<Cheese> cheeses;

    public Menu() {}

    public Menu(String aName){
        name = aName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public void addCheese(Cheese cheese){
        cheeses.add(cheese);
    }
}
