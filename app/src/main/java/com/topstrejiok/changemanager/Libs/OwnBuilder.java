package com.topstrejiok.changemanager.Libs;

public class OwnBuilder {

    public static OwnSingle TryCreate(Person from, Person to)
    {
        if (from.GetBalance() == 0 || to.GetBalance() == 0)
        {
            return null;
        }

        double money = 0;
        if (from.GetBalance() > 0 && to.GetBalance() < 0)
        {
            Person buf = from;
            from = to;
            to = buf;
        }

        if (from.GetBalance() < 0 && to.GetBalance() > 0)
        {
            if (Math.abs(from.GetBalance()) < to.GetBalance())
            {
                money = Math.abs(from.GetBalance());
                from.SetBalance(from.GetBalance() + money) ;
                to.SetBalance(to.GetBalance() - money) ;
            }
            else
            {
                money = to.GetBalance();
                from.SetBalance(from.GetBalance() + money) ;
                to.SetBalance(to.GetBalance() - money);
            }
            return new OwnSingle(from, to, money);
        }
        else
        {
            return null;
        }
    }
}
