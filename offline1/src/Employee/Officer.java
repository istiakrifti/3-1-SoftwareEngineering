package Employee;

import Account.Accounts;

import java.util.List;
import java.util.Map;

public class Officer extends Employees{

    public Officer(String name) {
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

    public void approveLoanRequest(List<Map.Entry<Accounts, Double>> loanList) {
        System.out.print("Loan for ");
        for(int i=0;i<loanList.size();i++)
        {
            double loan = loanList.get(i).getValue();
            loanList.get(i).getKey().setLoan(loanList.get(i).getKey().getLoan()+loanList.get(i).getValue());
            loanList.get(i).getKey().setBalance(loanList.get(i).getKey().getBalance()+loan);
            System.out.print(loanList.get(i).getKey().getUserName());
            if(i!=loanList.size()-1)
            {
                System.out.print(",");
            }
        }
        System.out.print(" approved\n");
        loanList.clear();
    }
}
