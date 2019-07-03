package com.topstrejiok.changemanager.model;

public class NameItem {
    private String name;
    private String id;

    public NameItem(String name) {
        this.name = name;
        id = Long.toString(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
