package Account;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class SavingsAccount extends Accounts{

    private static double interestRateOfDeposit;
    private final double maximumLimitOfLoan = 10000;
    public SavingsAccount(String userName, String accountType, double balance) {
        super(userName, accountType, balance);
        interestRateOfDeposit=10;
    }

    public static double getInterestRateOfDeposit() {
        return interestRateOfDeposit;
    }

    public static void setInterestRateOfDeposit(double interestRateOfDeposit) {
        SavingsAccount.interestRateOfDeposit = interestRateOfDeposit;
    }

    @Override
    public void deposit(double amount) {
        setBalance(getBalance()+amount);
        System.out.println(amount+"$ deposited; current balance "+getBalance()+"$");
    }

    @Override
    public double withdraw(double amount,int clock) {
        if(getBalance()-amount<1000)
        {
            return getBalance();
        }

        setBalance(getBalance()+amount);
        return getBalance();
    }

    @Override
    public void requestForLoan(double amount, List<Map.Entry<Accounts, Double>> loanList) {
        if(amount<=maximumLimitOfLoan)
        {
            System.out.println("Loan request successful, sent for approval");
            Map.Entry<Accounts, Double> entry = new AbstractMap.SimpleEntry<>(this, amount);
            loanList.add(entry);
        }
        else
        {
            System.out.println("Loan request failed");
        }
    }
}
