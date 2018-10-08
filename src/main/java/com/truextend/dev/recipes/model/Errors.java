package com.truextend.dev.recipes.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "errors")
public class Errors {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Integer id;

    @Column
    @Expose
    @NotNull
    private Date dateError = new Date();

    @Column
    @Expose
    @NotNull
    private String description;

    @Column
    @Expose
    @NotNull
    private Integer idUser;

    @Column
    @Expose
    @NotNull
    private String module;

    @Column
    @Expose
    @NotNull
    private String typeProject;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Date getDateError() {
        return dateError;
    }

    public void setDateError(Date dateError) {
        this.dateError = dateError;
    }

    public String getTypeProject() {
        return typeProject;
    }

    public void setTypeProject(String typeProject) {
        this.typeProject = typeProject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
