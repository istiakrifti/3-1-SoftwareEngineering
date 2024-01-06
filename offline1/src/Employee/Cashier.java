package Employee;

import Account.Accounts;

import java.util.List;

public class Cashier extends Employees{

    public Cashier(String name) {
        super(name);
    }

    @Override
    public double lookUpAccountBalance(String user, List<Accounts> accountsList) {
        for(int i=0;i<accountsList.size();i++)
        {
            if(accountsList.get(i).getUserName().equalsIgnoreCase(user))
            {
                Accounts userAccount = accountsList.get(i);
                return userAccount.getBalance();
            }
        }
        return -1;
    }

}
