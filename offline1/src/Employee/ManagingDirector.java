package Employee;

import Account.Accounts;
import Account.FixedDepositAccount;
import Account.SavingsAccount;
import Account.StudentAccount;

import java.util.List;
import java.util.Map;

public class ManagingDirector extends Employees{

    public ManagingDirector(String name) {
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

    public void approveLoanRequest(List<Map.Entry<Accounts, Double>> loanList,double internalFund) {
        System.out.print("Loan for ");
        for(int i=0;i<loanList.size();i++)
        {
            if(internalFund<0)
            {
                return;
            }
            double loan = loanList.get(i).getValue();
            loanList.get(i).getKey().setLoan(loanList.get(i).getKey().getLoan()+loanList.get(i).getValue());
            loanList.get(i).getKey().setBalance(loanList.get(i).getKey().getBalance()+loan);
            internalFund -= loan;
            System.out.print(loanList.get(i).getKey().getUserName());
            if(i!=loanList.size()-1)
            {
                System.out.print(",");
            }
        }
        System.out.print(" approved\n");
        loanList.clear();
    }

    public void changeInterestRate(Accounts accounts,double intRate) {
        if(accounts instanceof StudentAccount)
        {
            StudentAccount.setInterestRateOfDeposit(intRate);
            System.out.println("Interest Rate of Student Account changes to "+intRate);
        }
        else if(accounts instanceof SavingsAccount)
        {
            SavingsAccount.setInterestRateOfDeposit(intRate);
            System.out.println("Interest Rate of Savings Account changes to "+intRate);
        }
        else if(accounts instanceof FixedDepositAccount)
        {
            FixedDepositAccount.setInterestRateOfDeposit(intRate);
            System.out.println("Interest Rate of Fixed deposit Account changes to "+intRate);
        }
    }

    public void seeInternalFund(double amount) {

        System.out.println("Internal fund "+amount+"$");
    }


}
