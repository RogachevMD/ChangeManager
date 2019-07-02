package com.topstrejiok.changemanager.model;

import java.util.ArrayList;

public class ListItem {
   private String name;
   private double price;
   private ArrayList<Person> Group;

    public ListItem(String name, double price, ArrayList<Person> group) {
        this.name = name;
        this.price = price;
        Group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Person> getGroup() {
        return Group;
    }

    public void setGroup(ArrayList<Person> group) {
        Group = group;
    }

    private class Person{
       private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
