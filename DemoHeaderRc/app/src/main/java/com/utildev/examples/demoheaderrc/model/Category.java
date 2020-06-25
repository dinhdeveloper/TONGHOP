package com.utildev.examples.demoheaderrc.model;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

public class Category implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private int id;
    private  String name;
    private String image;
}
