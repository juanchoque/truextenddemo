package com.truextend.dev.recipes.model;

import com.google.gson.annotations.Expose;
import com.truextend.dev.recipes.util.ConstantsRecipes;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipes {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Integer id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Expose
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column
    @Expose
    @NotNull
    @Size(max = 100)
    private String  name = ConstantsRecipes.EMPTY_VALUE;

    @Column
    @Expose
    @NotNull
    @Size(max = 1000)
    private String  preparation = ConstantsRecipes.EMPTY_VALUE;

    @Column
    @Expose
    @NotNull
    @Max(1)
    private Integer state = ConstantsRecipes.STATE_ACTIVE;

    @Expose
    @ManyToOne
    @NotNull
    private Accounts accounts;

    @Expose
    @OneToMany(mappedBy = "recipes")
    private List<Ingredients> listIngredients = new ArrayList<Ingredients>();

    @Transient
    private List<Ingredients> listTIngredients = new ArrayList<Ingredients>();

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setListIngredients(List<Ingredients> listIngredients) {
        this.listIngredients = listIngredients;
    }

    public List<Ingredients> getListTIngredients() {
        return listTIngredients;
    }

    public void setListTIngredients(List<Ingredients> listTIngredients) {
        this.listTIngredients = listTIngredients;
    }
}
