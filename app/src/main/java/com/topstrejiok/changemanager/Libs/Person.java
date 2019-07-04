package com.topstrejiok.changemanager.Libs;

public class Person {
    public String ID;
    public String Name;

    private double donated, orderedOn, balance;

    public void SetDonated(double value)
    {
        donated = value;
        balance = donated - orderedOn;
    }

    public double GetDonated()
    {
        return donated;
    }

    public void SetOrderedOn(double value)
    {
        orderedOn = value;
        balance = donated - orderedOn;
    }

    public double GetOrderedOn()
    {
        return orderedOn;
    }

    public double GetBalance()
    {
        return balance;
    }

    public void SetBalance(double value)
    {
        balance = value;
    }

    public Person(String ID, String Name,double orderedOn,double donated)
    {
        this.ID = ID;
        this.Name = Name;
        this.orderedOn = orderedOn;
        this.donated = donated;
        balance = donated - orderedOn;
    }
}
