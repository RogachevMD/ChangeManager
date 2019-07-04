package com.topstrejiok.changemanager.Controller;

import com.topstrejiok.changemanager.model.SessionListItem;

import java.util.ArrayList;

public class SessionController{

    private ArrayList<SessionListItem> SessionItem = new ArrayList<>();

    public ArrayList<SessionListItem> getSessionItem() {
        return SessionItem;
    }

    public void setSessionItem(ArrayList<SessionListItem> sessionItem) {
        SessionItem = sessionItem;
    }

}
