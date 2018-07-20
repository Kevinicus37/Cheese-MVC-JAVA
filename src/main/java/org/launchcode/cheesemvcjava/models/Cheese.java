package org.launchcode.cheesemvcjava.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

/**
 * Created by dogma on 6/15/2018.
 */

@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message="Description must not be empty.")
    private String description;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus;

    @Min(1)
    @Max(5)
    private int rating;

    public Cheese(){
    }

    public Cheese(String aName){
        this(aName, "");
    }

    public Cheese(String aName, String aDescription){
        name = aName;
        description = aDescription;
    }

    public int getId() {
        return id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
