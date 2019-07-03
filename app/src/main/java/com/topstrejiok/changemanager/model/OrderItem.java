package com.topstrejiok.changemanager.model;

import java.util.ArrayList;

public class OrderItem {
    private String itemName;
    private Double itemPrice;
    private ArrayList<NameItem> Names;

    public OrderItem(String itemName, Double price, ArrayList<NameItem> names) {
        this.itemName = itemName;
        this.itemPrice = price;
        Names = names;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return itemPrice;
    }

    public void setPrice(Double price) {
        this.itemPrice = price;
    }

    public ArrayList<NameItem> getNames() {
        return Names;
    }

    public void setNames(ArrayList<NameItem> names) {
        Names = names;
    }
}
