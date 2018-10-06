package com.truextend.dev.recipes.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ingredients")
public class Ingredients {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Expose
    private Integer id;

    @Column
    @Expose
    private String name;

    @Column
    @Expose
    private Integer state;

    @ManyToOne
    @NotNull
    @Valid
    private Recipes recipes;

    //contructors
    public Ingredients() {
    }

    public Ingredients(Integer id, String name, Integer state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public Ingredients(Integer id, String name, Integer state, Recipes recipes) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.recipes = recipes;
    }

    //gets and seters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipes getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipes recipes) {
        this.recipes = recipes;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
