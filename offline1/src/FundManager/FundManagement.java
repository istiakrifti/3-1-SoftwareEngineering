package FundManager;

import Account.Accounts;
import Account.FixedDepositAccount;
import Account.SavingsAccount;
import Account.StudentAccount;

import java.util.List;

public class FundManagement {

    private final double interestRateOfLoan = 10;
    private final double serviceChargeEachYear = 500;

    public FundManagement(){}

    public void calculateInterestRateOfDeposit(List<Accounts> accountsList,double internalFund) {

        for(int i=0;i<accountsList.size();i++)
        {
            double preBalance = accountsList.get(i).getBalance();
            if(accountsList.get(i) instanceof StudentAccount)
            {
                double balance = preBalance+preBalance*(StudentAccount.getInterestRateOfDeposit()/100);
                accountsList.get(i).setBalance(balance);
                internalFund -= preBalance*(StudentAccount.getInterestRateOfDeposit()/100);
            }
            else if(accountsList.get(i) instanceof SavingsAccount)
            {
                double balance = preBalance+preBalance*(SavingsAccount.getInterestRateOfDeposit()/100);
                accountsList.get(i).setBalance(balance);
                internalFund -= preBalance*(StudentAccount.getInterestRateOfDeposit()/100);
            }
            else if(accountsList.get(i) instanceof FixedDepositAccount)
            {
                double balance = preBalance+preBalance*(FixedDepositAccount.getInterestRateOfDeposit()/100);
                accountsList.get(i).setBalance(balance);
                internalFund -= preBalance*(StudentAccount.getInterestRateOfDeposit()/100);
            }
        }
    }


    public void calculateInterestRateOfLoan(List<Accounts> accountsList,double internalFund) {
        for(int i=0;i<accountsList.size();i++)
        {
            if(accountsList.get(i).getLoan()>0)
            {
                double preBalance = accountsList.get(i).getBalance();
                double preLoan = accountsList.get(i).getLoan();
                double balance = preLoan*(interestRateOfLoan/100);
                accountsList.get(i).setBalance(preBalance-balance);
                internalFund += balance;
            }
        }
    }

    public void calculateServiceCharge(List<Accounts> accountsList,double internalFund) {
        for(int i=0;i<accountsList.size();i++)
        {
            if(!(accountsList.get(i) instanceof StudentAccount))
            {
                double preBalance = accountsList.get(i).getBalance();
                double balance = preBalance-serviceChargeEachYear;
                internalFund +=serviceChargeEachYear;
                accountsList.get(i).setBalance(balance);
            }

        }
    }

}
