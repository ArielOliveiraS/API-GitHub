
package com.example.requisicaoapigithub.model.pojo.pull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("avatar_url")
    private String avatarUrl;
    @Expose
    private Long id;
    @Expose
    private String login;


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
