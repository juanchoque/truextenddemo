package com.truextend.dev.recipes.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipes {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Expose
    private Integer id;

    @Column
    @Expose
    private String  name;

    @Column
    @Expose
    private String  preparation;

    @Column
    @Expose
    private Integer state;

    @ManyToOne
    @NotNull
    @Valid
    private Accounts accounts;

    @OneToMany(mappedBy = "recipes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ingredients> listIngredients = new ArrayList<Ingredients>();

    //constructos
    public Recipes() {
    }

    public Recipes(Integer id, String name, String preparation, Integer state) {
        this.id = id;
        this.name = name;
        this.preparation = preparation;
        this.state = state;
    }

    public Recipes(Integer id, String name, String preparation, Integer state, Accounts accounts) {
        this.id = id;
        this.name = name;
        this.preparation = preparation;
        this.state = state;
        this.accounts = accounts;
    }

    public Recipes(Integer id, String name, String preparation, Integer state, Accounts accounts, List<Ingredients> listIngredients) {
        this.id = id;
        this.name = name;
        this.preparation = preparation;
        this.state = state;
        this.accounts = accounts;
        this.listIngredients = listIngredients;
    }

    //methods gets and sets
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

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Ingredients> getListIngredients() {
        return listIngredients;
    }

    public void setListIngredients(List<Ingredients> listIngredients) {
        this.listIngredients = listIngredients;
    }
}
