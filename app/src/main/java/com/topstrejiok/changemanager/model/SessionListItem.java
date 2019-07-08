package com.topstrejiok.changemanager.model;

import java.text.SimpleDateFormat;

public class SessionListItem {
    private Long id;
    private String name;
    private String dateTime;

    public SessionListItem(String name, Long id) {
        this.name = name;
        this.id = id;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMMM/yyyy\nHH:mm");
        this.dateTime = simpleDateFormat.format(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
