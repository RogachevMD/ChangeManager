package com.topstrejiok.changemanager.model;

public class NameItem {
    private String name;
    private String id;
    private Boolean checked = false;
    private Double donate = 0.0;

    public NameItem(String name) {
        this.name = name;
        id = Long.toString(System.currentTimeMillis());
    }

    public Double getDonate() {
        return donate;
    }

    public void setDonate(Double donate) {
        this.donate = donate;
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

    public void setId(String id) {
        this.id = id;
    }

    public NameItem GetClone() {
        NameItem ni = new NameItem(name);
        ni.setChecked(false);
        ni.setId(id);
        return ni;
    }
}
