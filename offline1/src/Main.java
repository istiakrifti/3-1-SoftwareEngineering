import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Bank bank = Bank.getBank();

//        bank.employeeLogin("O1");
//        bank.createAccount("Alice","fixed deposit",120000);
//        bank.createAccount("Alice","Student",1000);
//        bank.closeAccount();
//        bank.createAccount("alic","savings",150000);
//        bank.deposit(20000);
//        bank.withdraw(12000);
//        bank.queryOfDeposit();
//        bank.requestForLoan(500);
//        bank.closeAccount();
//        bank.login("O1");
//        bank.approveLoan();
//        bank.changeInterestRate("Student",7.5);
//        bank.lookUpAccounts("Alice");
//        bank.seeInternalFund();
//        bank.closeAccount();
//        bank.login("Alice");
//        bank.queryOfDeposit();
//        bank.closeAccount();
//        bank.incrementYear();
//        bank.login("Alice");
//        bank.queryOfDeposit();
//        bank.closeAccount();


//        Scanner scanner = new Scanner(new File("D:/308/offline1/src/file.txt"));
        Scanner scanner = new Scanner(System.in);

//        while(scanner.hasNextLine())
        while(true)
        {
            String inputLine = scanner.nextLine();

            String[] command = inputLine.split(" ");

            switch (command[0]){
                case "Create":
                    bank.createAccount(command[1],command[2],Double.parseDouble(command[3]));
                    break;
                case "Deposit":
                    bank.deposit(Double.parseDouble(command[1]));
                    break;
                case "Withdraw":
                    bank.withdraw(Double.parseDouble(command[1]));
                    break;
                case "Query":
                    bank.queryOfDeposit();
                    break;
                case "Request":
                    bank.requestForLoan(Double.parseDouble(command[1]));
                    break;
                case "Close":
                    bank.closeAccount();
                    break;
                case "Open":
                    bank.login(command[1]);
                    break;
                case "Approve":
                    bank.approveLoan();
                    break;
                case "Change":
                    bank.changeInterestRate(command[1],Double.parseDouble(command[2]));
                    break;
                case "Lookup":
                    bank.lookUpAccounts(command[1]);
                    break;
                case "See":
                    bank.seeInternalFund();
                    break;
                case "INC":
                    bank.incrementYear();
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }


    }
}
