package com.topstrejiok.changemanager.Controller;

import android.util.Log;

import com.topstrejiok.changemanager.Libs.Group;
import com.topstrejiok.changemanager.Libs.Person;
import com.topstrejiok.changemanager.model.NameItem;
import com.topstrejiok.changemanager.model.OrderItem;

import java.lang.reflect.Array;
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


        */return  nameItems;
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

    public void removeName(String name, String id){
        Log.d(LogD, "removeName");
        int n = 0;
        for (int i = 0; i < nameItems.size(); i++){
            if (nameItems.get(i).getName().equals(name) && nameItems.get(i).getId().equals(id)){
                nameItems.remove(i);
                n++;
            }
        }
        Log.d(LogD,n + " items was removed in NameItems");
        n = 0;
        for (int i = 0; i < orderItems.size(); i++){
           for (int j = 0; j < orderItems.get(i).getNames().size(); j++){
               if (orderItems.get(i).getNames().get(j).getName().equals(name) &&
                       orderItems.get(i).getNames().get(j).getId().equals(id)){
                   orderItems.get(i).getNames().remove(j);
                   n++;
               }
           }
        }
        Log.d(LogD,n + " items was removed in OrderItems");
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

    public Person FindPerson(String id)
    {

    }


    public void Calculate()
    {
        ArrayList<Person> people = new ArrayList<>();
        for (NameItem ni : getNameItems())
        {
            people.add(new Person(ni.getId(),ni.getName(),0,0));
        }

        for (OrderItem oi : getOrderItems())
        {
            if (oi.getForeach())
            {
                for (NameItem locNi : oi.getNames())
                {
                    for (Person p : people)
                    {
                        if (p.ID == locNi.getId())
                        {
                            p.SetOrderedOn(p.GetOrderedOn()+oi.getItemPrice());
                        }
                    }

                }
            }
            else {
                for (NameItem locNi : oi.getNames())
                {
                    for (Person p : people)
                    {
                        if (p.ID == locNi.getId())
                        {
                            p.SetOrderedOn(p.GetOrderedOn()+oi.getItemPrice()/oi.getNames().size()+1);
                        }
                    }
                }
            }
        }
    }
}