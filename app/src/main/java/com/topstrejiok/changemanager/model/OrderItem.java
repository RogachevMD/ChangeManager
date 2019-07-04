package com.topstrejiok.changemanager.model;

import java.util.ArrayList;

public class OrderItem {
    private String itemName;
    private Double itemPrice;
    private Boolean foreach;
    private ArrayList<NameItem> Names;

    public OrderItem(String itemName, Double itemPrice, Boolean foreach, ArrayList<NameItem> names) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.foreach = foreach;
        Names = names;
    }

    public Boolean getForeach() {
        return foreach;
    }

    public void setForeach(Boolean foreach) {
        this.foreach = foreach;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double price) {
        this.itemPrice = price;
    }

    public ArrayList<NameItem> getNames() {
        return Names;
    }

    public void setNames(ArrayList<NameItem> names) {
        Names = new ArrayList<>(names);
    }
}
