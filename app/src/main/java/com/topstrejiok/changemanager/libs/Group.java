package com.topstrejiok.changemanager.libs;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Group {
    public static ArrayList<Person> People = new ArrayList<>();
    public static ArrayList<OwnSingle> Owns = new ArrayList<>();

    public static void Calculate()
    {
        double don = 0;
        double ord = 0;
        Owns = new ArrayList<>();

        for(Person p : People)
        {
            Log.i("kek", p.Name + " got " + p.GetDonated() + " and ordered on " + p.GetOrderedOn() + " balance = " + p.GetBalance() );
        }

        for (Person p: People)
        {
            don +=  p.GetDonated();
            ord += p.GetOrderedOn();
        }
        if (don> ord)
        {
            People.add(new Person(People.get(People.size() - 1).ID + 1, "Change", don - ord, 0));
        }
        else if (don<ord)
        {
            People.add(new Person(People.get(People.size() - 1).ID + 1, "Need", 0, ord - don));
        }

        if (! CheckDone())
        {
            for (Person p: People)
            {
                CalculateSingleFor(p);
            }
        }
    }

    public static void OutOwns()
    {
        for (OwnSingle o : Owns)
        {
            Log.i("kek",o.from.Name + " -> " + o.to.Name + " " + o.money);
        }
    }

    public static boolean CheckDone()
    {
        boolean flag = true;
        for(Person p: People)
        {
            if (p.GetBalance() < 0)
                flag = false;
        }

        return flag;
    }

    public static void CalculateSingleFor(Person p)
    {
        if (p.GetBalance() < 0)
        {
            for (Person other : GetOther(p))
            {
                OwnSingle os = OwnBuilder.TryCreate(p, other);
                if (os != null)
                {
                    Owns.add(os);
                }
            }

        }
    }

    public static List<Person> GetOther(Person p)
    {
        ArrayList<Person> Res = new ArrayList<>();
        for (Person per: People)
        {
            if (per.ID != p.ID)
            {
                Res.add(per);
            }
        }
        return Res;
    }
}
