package com.truextend.dev.recipes.model;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Expose
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Expose
    @Temporal(TemporalType.DATE)
    private Date    birthDate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Expose
    @Temporal(TemporalType.DATE)
    private Date    createDate = new Date();

    @Column
    @Expose
    @NotNull
    @NotEmpty
    @Pattern(regexp ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$")
    private String  email;

    @Column
    @Expose
    @NotNull
    @Size(min = 2, max = 100)
    private String  firstName;

    @Column
    @Expose
    @Size(max = 100)
    private String  lastName = "";

    @Column
    @Expose
    @NotNull
    @Size(min = 3, max = 15)
    private String  password;

    @Column
    @Expose
    @NotNull
    @Max(1)
    private Integer state = 1;

    @OneToMany(mappedBy = "accounts", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recipes>listRecipes = new ArrayList<Recipes>();

    @Expose
    @Transient
    private String token;

    //constructors
    public Accounts() {

    }

    public Accounts(Integer id, Date birthDate, String email, String firstName, String lastName, String password, Integer state) {
        this.id = id;
        this.birthDate = birthDate;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.state = state;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
