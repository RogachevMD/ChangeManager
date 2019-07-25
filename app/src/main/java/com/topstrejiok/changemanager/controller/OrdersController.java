package com.topstrejiok.changemanager.controller;

import android.util.Log;

import com.topstrejiok.changemanager.libs.Group;
import com.topstrejiok.changemanager.libs.Person;
import com.topstrejiok.changemanager.model.NameItem;
import com.topstrejiok.changemanager.model.OrderItem;

import java.util.ArrayList;

public class OrdersController {

    private String LogD = "OrdersController";

    private ArrayList<NameItem> nameItems = new ArrayList<>();
    private ArrayList<OrderItem> orderItems = new ArrayList<>();

    public ArrayList<NameItem> getNameItems() {
        /*ArrayList<NameItem> newNameItems = new ArrayList<>();
        for (NameItem ni:nameItems)
        {
            NameItem newni = ni.GetClone();
            newNameItems.add(newni);
        }
        return newNameItems;


        */
        return nameItems;
    }

    public void setNameItems(ArrayList<NameItem> nameItems) {
        this.nameItems = nameItems;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void removeName(String name, String id) {
        Log.d(LogD, "removeName");
        int n = 0;
        for (int i = 0; i < nameItems.size(); i++) {
            if (nameItems.get(i).getName().equals(name) && nameItems.get(i).getId().equals(id)) {
                nameItems.remove(i);
                n++;
            }
        }
        Log.d(LogD, n + " items was removed in NameItems\nName: " + name + " \nId: " + id);
        n = 0;
        for (int i = 0; i < orderItems.size(); i++) {
            for (int j = 0; j < orderItems.get(i).getNames().size(); j++) {
                if (orderItems.get(i).getNames().get(j).getName().equals(name) &&
                        orderItems.get(i).getNames().get(j).getId().equals(id)) {
                    orderItems.get(i).getNames().remove(j);
                    n++;
                }
            }
        }
        Log.d(LogD, n + " items was removed in OrderItems\nName: " + name + " \nId: " + id);
    }

    public void renameName(String oldName, String newName, String id) {
        Log.d(LogD, "renameName");
        int n = 0;
        for (int i = 0; i < nameItems.size(); i++) {
            if (nameItems.get(i).getName().equals(oldName) && nameItems.get(i).getId().equals(id)) {
                nameItems.get(i).setName(newName);
                n++;
            }
        }
        Log.d(LogD, n + " items was renamed in NameItems\nOld Name: " + oldName + " => new Name: " + newName);
        n = 0;
        for (int i = 0; i < orderItems.size(); i++) {
            for (int j = 0; j < orderItems.get(i).getNames().size(); j++) {
                if (orderItems.get(i).getNames().get(j).getName().equals(oldName) &&
                        orderItems.get(i).getNames().get(j).getId().equals(id)) {
                    orderItems.get(i).getNames().get(j).setName(newName);
                    n++;
                }
            }
        }
        Log.d(LogD, n + " items was renamed in OrderItems\nOld Name: " + oldName + " => new Name: " + newName);
    }

    public void addNewName(String name) {
        Log.d(LogD, "addName");
        NameItem newNameItem = new NameItem(name);
        int n = 1;
        nameItems.add(newNameItem.GetClone());
        Log.d(LogD, n + " items was added in NameItems\nName: " + name + " \nId: " + newNameItem.getId());
        n = 0;
        for (OrderItem oi : orderItems) {
            oi.getNames().add(newNameItem.GetClone());
            n++;
        }
        Log.d(LogD, n + " items was added in OrderItems\nName: " + name + " \nId: " + newNameItem.getId());
    }

    public void PrintOrders() {
        for (OrderItem OI : getOrderItems()) {
            Log.d(LogD, "Order: " + OI.getItemName());
            for (NameItem NI : OI.getNames()) {
                Log.d(LogD, NI.getName() + " " + NI.getChecked());

            }
        }
        StringBuilder S = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            S.append("##");
        }
        Log.d(LogD, S.toString());
    }

    public void Calculate() {
        ArrayList<Person> people = new ArrayList<>();
        for (NameItem ni : getNameItems()) {
            people.add(new Person(ni.getId(), ni.getName(), 0, ni.getDonate()));
        }

        for (OrderItem oi : getOrderItems()) {
            if (oi.getForeach()) {
                for (NameItem locNi : oi.GetCheckedNameItems()) {
                    for (Person p : people) {
                        if (p.ID.equals(locNi.getId()) && locNi.getChecked()) {
                            p.SetOrderedOn(p.GetOrderedOn() + oi.getItemPrice());
                        }
                    }
                }
            } else {
                for (NameItem locNi : oi.GetCheckedNameItems()) {
                    for (Person p : people) {
                        if (p.ID.equals(locNi.getId())) {
                            p.SetOrderedOn(p.GetOrderedOn() + oi.getItemPrice() / oi.GetCheckedNameItems().size());
                        }
                    }
                }
            }
        }


        Group.People = people;
        Group.Calculate();
        Group.OutOwns();
    }

    public Double getFullPrice() {
        double sum = 0.0;
        for (OrderItem oi : orderItems) {
            if (oi.getForeach()){
                sum += oi.getItemPrice()*oi.getNames().size();
            }else {
                sum += oi.getItemPrice();
            }
        }
        return sum;
    }
}
