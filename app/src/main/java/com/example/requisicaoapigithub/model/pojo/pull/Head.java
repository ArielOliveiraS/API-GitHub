
package com.example.requisicaoapigithub.model.pojo.pull;

import com.google.gson.annotations.Expose;


public class Head {

    @Expose
    private Repo repo;

    public Repo getRepo() {
        return repo;
    }

}
