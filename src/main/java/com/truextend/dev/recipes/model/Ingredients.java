package com.truextend.dev.recipes.model;

import com.google.gson.annotations.Expose;
import com.truextend.dev.recipes.util.ConstantsRecipes;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ingredients")
public class Ingredients {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Integer id;

    @Column
    @Expose
    @NotNull
    @Size(max = 500)
    private String name = ConstantsRecipes.EMPTY_VALUE;

    @Column
    @Expose
    @NotNull
    @Max(1)
    private Integer state = ConstantsRecipes.STATE_ACTIVE;

    @ManyToOne
    @NotNull
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
