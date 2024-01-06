package Account;

import java.util.List;
import java.util.Map;

public abstract class Accounts{

    //necessary variables
    private String userName;
    private String accountType;
    private double balance;
    private double loan;
    private boolean activeStatus;

    public Accounts(String userName, String accountType, double balance) {
        this.userName = userName;
        this.accountType = accountType;
        this.balance = balance;
        this.loan = 0;
    }

    //some abstract methods
    public abstract double withdraw(double amount,int clock);
    public abstract void requestForLoan(double amount, List<Map.Entry<Accounts, Double>> loanList);
    public abstract void deposit(double amount);


    public void queryOfDeposit(){
        if(loan>0){
            System.out.println("Current balance, "+balance+"$, loan "+loan+"$");
        }
        else
        {
            System.out.println("Current balance, "+balance+"$");
        }
    }

    //setters and getters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }


}
