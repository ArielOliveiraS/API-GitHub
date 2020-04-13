
package com.example.requisicaoapigithub.model.pojo.pull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Repo implements Serializable {

    @SerializedName("full_name")
    private String fullName;
    @Expose
    private Long id;
    @Expose
    private Owner owner;
    @Expose
    private Boolean privateBool;
    @SerializedName("pulls_url")

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Boolean getPrivate() {
        return privateBool;
    }

    public void setPrivate(Boolean privateBool) {
        this.privateBool = privateBool;
    }
}
