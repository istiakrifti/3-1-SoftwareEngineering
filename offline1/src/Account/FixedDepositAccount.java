package Account;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class FixedDepositAccount extends Accounts{
    private static double interestRateOfDeposit;
    private final static double initialAmountOfDeposit = 100000;
    private final double maximumLimitOfLoan = 100000;
    private final double validDepositAmount=50000;
    public FixedDepositAccount(String userName, String accountType, double balance) {
        super(userName, accountType, balance);
        interestRateOfDeposit = 15;
    }

    public static double getInterestRateOfDeposit() {
        return interestRateOfDeposit;
    }

    public static void setInterestRateOfDeposit(double interestRateOfDeposit) {
        FixedDepositAccount.interestRateOfDeposit = interestRateOfDeposit;
    }

    public static boolean isValidInitialAmount(double initialAmount)  //check the amount valid or not for deposit
    {
        if(initialAmount<initialAmountOfDeposit)
        {
            System.out.println("Can't Create Account!!! You can't deposit less than "+initialAmountOfDeposit+"$ in a Fixed deposit account");
            return false;
        }
        return true;
    }

    @Override
    public double withdraw(double amount,int clock) {

        if(clock<1 || getBalance()-amount<0)
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

    @Override
    public void deposit(double amount) {
        if(amount>=validDepositAmount)
        {
            setBalance(getBalance()+amount);
            System.out.println(amount+"$ deposited; current balance "+getBalance()+"$");
        }
        else {
            System.out.println("Can't deposit less than "+validDepositAmount+"$ in a fixed deposit account");
        }
    }
}
