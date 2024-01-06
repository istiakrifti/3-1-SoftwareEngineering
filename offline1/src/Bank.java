import Account.Accounts;
import Account.FixedDepositAccount;
import Account.SavingsAccount;
import Account.StudentAccount;
import Employee.Cashier;
import Employee.Employees;
import Employee.ManagingDirector;
import Employee.Officer;
import FundManager.FundManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bank {

    //necessary variables
    private static Bank bank;
    private final double internalFund=1000000;
    private int clock;
    private Accounts activeAccount;
    private Employees activeEmployee;
    private List<Accounts> accountsList;
    private List<Employees> employeesList;
    private List<Map.Entry<Accounts, Double>> loanList; //List of the accounts and amounts applied for loan


    private Bank(){
        clock = 0;
        accountsList = new ArrayList<>();
        employeesList = new ArrayList<>();
        loanList = new ArrayList<>();
        activeAccount = null;
        activeEmployee = null;

        createEmployee();

    }

    public static Bank getBank() {
        if (bank == null)
        {
            bank = new Bank();
        }
        return bank;
    }

    //some methods related to Customer of the bank
    public void createAccount(String user, String accountType, double initialAmount){ //create account of a new customer

        if(activeEmployee!=null || activeAccount!=null)
        {
            System.out.println("You don’t have permission for this operation");
            return;
        }


        if(IsAccountExistAlready(user)) return;

        if(initialAmount<0)
        {
            System.out.println("Invalid amount");
            return;
        }

        if(accountType.equalsIgnoreCase("Student"))
        {
            StudentAccount account = new StudentAccount(user,accountType,initialAmount);
            accountsList.add(account);
            setActiveAccount(account);
        }
        else if(accountType.equalsIgnoreCase("Fixed deposit"))
        {
            if(!FixedDepositAccount.isValidInitialAmount(initialAmount)) return;
            FixedDepositAccount account = new FixedDepositAccount(user,accountType,initialAmount);
            accountsList.add(account);
            setActiveAccount(account);
        }
        else if(accountType.equalsIgnoreCase("Savings"))
        {
            SavingsAccount account = new SavingsAccount(user,accountType,initialAmount);
            accountsList.add(account);
            setActiveAccount(account);
        }
        else
        {
            System.out.println("Can't Create Account!!! This bank doesn't provide '"+accountType+"' type account");
            return;
        }

        System.out.println(accountType.substring(0, 1).toUpperCase() + accountType.substring(1).toLowerCase()+
                " account for "+user+" Created; initial balance "+initialAmount);
    }

    public void deposit(double amount){       //deposit a valid amount of money into respective account

        if(amount<0)
        {
            System.out.println("Invalid amount");
            return;
        }
        if(activeEmployee!=null || activeAccount==null)
        {
            System.out.println("You don’t have permission for this operation");
            return;
        }

        if(activeAccount instanceof FixedDepositAccount)
        {
            System.out.println("You can't deposit in a Fixed deposit account");
        }
        else if(activeAccount instanceof SavingsAccount)
        {
            ((SavingsAccount) activeAccount).deposit(amount);
        }
        else if (activeAccount instanceof StudentAccount)
        {
            ((StudentAccount) activeAccount).deposit(amount);
        }

    }

    public void withdraw(double amount){    //withdraw a valid amount of money

        if(amount<0)
        {
            System.out.println("Invalid amount");
            return;
        }
        if(activeEmployee!=null || activeAccount==null)
        {
            System.out.println("You don’t have permission for this operation");
            return;
        }

        double previousBalance = activeAccount.getBalance();
        double currentBalance = activeAccount.withdraw(amount,clock);

        if(currentBalance==previousBalance)
        {
            System.out.println("Invalid transaction; current balance "+currentBalance+"$");
        }
        else
        {
            System.out.println("Transaction successful; current balance "+currentBalance+"$");
        }
    }

    public void requestForLoan(double amount){    //handle the loan request

        if(amount<0)
        {
            System.out.println("Invalid amount");
            return;
        }

        if(activeEmployee!=null || activeAccount==null)
        {
            System.out.println("You don’t have permission for this operation");
            return;
        }
        activeAccount.requestForLoan(amount,loanList);
    }

    public void queryOfDeposit(){   //show the current balance and loan amount of an account
        if(activeEmployee!=null || activeAccount==null)
        {
            System.out.println("You don’t have permission for this operation");
            return;
        }
        activeAccount.queryOfDeposit();
    }


    //common methods of Employees and Customer
    public void closeAccount(){   //to logout from an account

        if(activeAccount!=null)
        {
            String name = activeAccount.getUserName();
            activeAccount = null;
            System.out.println("Transaction Closed for "+name);
        }
        else if(activeEmployee!=null)
        {
            String name = activeEmployee.getName();
            activeEmployee = null;
            System.out.println("Operations for "+name+" closed");
        }
        else
        {
            System.out.println("You don’t have permission for this operation");
        }
    }

    public void login(String name) {   //to log in an account

        if(activeEmployee!=null || activeAccount!=null)
        {
            System.out.println("You don’t have permission for this operation");
            return;
        }
        int flag=0;
        for(int i=0;i<employeesList.size();i++)
        {
            if(employeesList.get(i).getName().equalsIgnoreCase(name))
            {
                setActiveEmployee(employeesList.get(i));
                flag=1;
                break;
            }
        }
        if(flag==1)
        {
            if(activeEmployee!=null)
            {
                if(loanList.size()==0)
                {
                    System.out.println(name+" active;");
                }
                else
                {
                    System.out.println(name+" active, there are loan approvals pending");
                }
            }
            else
            {
                System.out.println("Employee doesn't exist");
            }
        }
        else
        {
            for(int i=0;i<accountsList.size();i++)
            {
                if(accountsList.get(i).getUserName().equalsIgnoreCase(name))
                {
                    setActiveAccount(accountsList.get(i));
                    System.out.println("Welcome back, "+accountsList.get(i).getUserName());
                    return;
                }
            }
            System.out.println("Account doesn't exist");
        }
    }


    //methods related to Employees
    public void lookUpAccounts(String user)   //show the current balance of an account
    {
        if(activeEmployee==null || activeAccount!=null)
        {
            System.out.println("You don’t have permission for this operation");
            return;
        }

        double currentBalance = activeEmployee.lookUpAccountBalance(user,accountsList);
        if(currentBalance == -1)
        {
            System.out.println("Account doesn't exist");
        }
        else
        {
            System.out.println(user+"’s current balance "+currentBalance+"$");
        }
    }

    public void approveLoan()   //approve loan request of the accounts
    {
        if(activeEmployee==null || activeAccount!=null)
        {
            System.out.println("You don’t have permission for this operation");
            return;
        }

        if(loanList.size()!=0)
        {
            if(activeEmployee instanceof ManagingDirector)
            {

                ((ManagingDirector) activeEmployee).approveLoanRequest(loanList,internalFund);
            } else if (activeEmployee instanceof Officer) {
                ((Officer) activeEmployee).approveLoanRequest(loanList);
            } else if (activeEmployee instanceof Cashier) {
                System.out.println("You don’t have permission for this operation");
            }
        }
        else
        {
            System.out.println("No loan request pending");
        }

    }

    public void seeInternalFund(){    //show the internal fund of the bank

        if(activeEmployee==null || activeAccount!=null)
        {
            System.out.println("You don’t have permission for this operation");
            return;
        }

        if(activeEmployee instanceof ManagingDirector)
        {
            ((ManagingDirector) activeEmployee).seeInternalFund(internalFund);
        } else if (activeEmployee instanceof Officer) {
            System.out.println("You don’t have permission for this operation");
        } else if (activeEmployee instanceof Cashier) {
            System.out.println("You don’t have permission for this operation");
        }
    }

    public void changeInterestRate(String accountType,double intRate){  //change the interest rate of any account type

        if(activeEmployee==null || activeAccount!=null)
        {
            System.out.println("You don’t have permission for this operation");
            return;
        }

        int flag=0;
        for(int i=0;i<accountsList.size();i++)
        {
            if(accountsList.get(i).getAccountType().equalsIgnoreCase(accountType))
            {
                Accounts account = accountsList.get(i);
                if(activeEmployee instanceof ManagingDirector)
                {
                    ((ManagingDirector) activeEmployee).changeInterestRate(account,intRate);
                } else if (activeEmployee instanceof Officer) {
                    System.out.println("You don’t have permission for this operation");
                } else if (activeEmployee instanceof Cashier) {
                    System.out.println("You don’t have permission for this operation");
                }
                flag = 1;
                break;
            }
        }
        if(flag==0){
            System.out.println("There is no "+accountType+" type account yet");
        }
    }

    public void incrementYear() {

        if(activeEmployee!=null || activeAccount!=null)
        {
            System.out.println("You don’t have permission for this operation");
            return;
        }
        clock++;
        FundManagement fundManage=new FundManagement();
        fundManage.calculateServiceCharge(accountsList,internalFund);
        fundManage.calculateInterestRateOfDeposit(accountsList,internalFund);
        fundManage.calculateInterestRateOfLoan(accountsList,internalFund);
        System.out.println(clock+" years passed");
    }


    //setters,getters and some necessary private methods for bank

    public Accounts getActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(Accounts activeAccount) {
        this.activeAccount = activeAccount;
    }

    public Employees getActiveEmployee() {
        return activeEmployee;
    }

    public void setActiveEmployee(Employees activeEmployee) {
        this.activeEmployee = activeEmployee;
    }

    private void createEmployee()  //create initial employees
    {

        ManagingDirector md = new ManagingDirector("MD");
        employeesList.add(md);
        for(int i=1;i<=2;i++)
        {
            Officer officer = new Officer("O"+Integer.toString(i));
            employeesList.add(officer);
        }
        for(int i=1;i<=5;i++)
        {
            Cashier cashier = new Cashier("C"+Integer.toString(i));
            employeesList.add(cashier);
        }

        System.out.print("Bank Created; ");

        for(int i=0;i<employeesList.size();i++)
        {
            System.out.print(employeesList.get(i).getName());
            if(i!=employeesList.size()-1)
            {
                System.out.print(", ");
            }
        }
        System.out.println(" created");
    }

    private boolean IsAccountExistAlready(String user)
    {
        for(int i=0;i<accountsList.size();i++)
        {
            if(accountsList.get(i).getUserName().equalsIgnoreCase(user)){
                System.out.println("Can't Create Account!!! Account already exists");
                return true;
            }
        }
        return false;
    }
}
