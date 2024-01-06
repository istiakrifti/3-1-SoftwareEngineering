package Employee;

import Account.Accounts;

import java.util.List;

public abstract class Employees {
    private String name;



    public Employees(String name)
    {
        this.name = name;
    }


    //setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //abstract methods
    public abstract double lookUpAccountBalance(String user, List<Accounts> accountsList);
}
