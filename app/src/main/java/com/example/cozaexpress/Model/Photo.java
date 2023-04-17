package com.example.cozaexpress.Model;

import java.io.Serializable;

public class Photo implements Serializable {
    private String resources;

    public Photo(String resources) {
        this.resources = resources;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }
}
