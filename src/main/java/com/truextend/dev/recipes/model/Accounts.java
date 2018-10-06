package com.truextend.dev.recipes.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * This is to save data from clients accounts
 */
@Entity
@Table(name = "accounts")
public class Accounts {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Expose
    private Integer id;

    @Column
    @Expose
    private Date    birthDate;

    @Column
    @Expose
    private String  email;

    @Column
    @Expose
    private String  name;

    @Column
    @Expose
    private String  password;

    @Column
    @Expose
    private Integer state;

    @OneToMany(mappedBy = "accounts", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recipes>listRecipes = new ArrayList<Recipes>();

    @Transient
    @Expose
    private String token;

    //constructors
    public Accounts() {
    }

    public Accounts(Integer id, Date birthDate, String email, String name, String password, Integer state) {
        this.id = id;
        this.birthDate = birthDate;
        this.email = email;
        this.name = name;
        this.password = password;
        this.state = state;
    }

    public Accounts(Integer id, Date birthDate, String email, String name, String password, Integer state, List<Recipes> listRecipes) {
        this.id = id;
        this.birthDate = birthDate;
        this.email = email;
        this.name = name;
        this.password = password;
        this.state = state;
        this.listRecipes = listRecipes;
    }

    //methods get and set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Recipes> getListRecipes() {
        return listRecipes;
    }

    public void setListRecipes(List<Recipes> listRecipes) {
        this.listRecipes = listRecipes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
