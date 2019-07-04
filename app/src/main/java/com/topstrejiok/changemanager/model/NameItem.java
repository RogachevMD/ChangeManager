package com.topstrejiok.changemanager.model;

public class NameItem {
    private String name;
    private String id;
    private Boolean checked = false;

    public NameItem(String name) {
        this.name = name;
        id = Long.toString(System.currentTimeMillis());
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
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
