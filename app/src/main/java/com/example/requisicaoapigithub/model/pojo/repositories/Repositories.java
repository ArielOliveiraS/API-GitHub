
package com.example.requisicaoapigithub.model.pojo.repositories;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Repositories {

    @Expose
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
