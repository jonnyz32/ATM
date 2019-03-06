// An abstract Class for Acounts


import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public abstract class GenericAccount extends TextInterface {

    double balance;
    boolean asset;
    String name;

    public GenericAccount() {
        super();
        addAction(1, ()->showBalance(), "View Balance");
        addAction(2, ()->depositCash(), "Deposit Cash");
        addAction(3, ()->transferToSelf(), "Transfer to another account");
        addAction(4, ()->transferToOther(), "Transfer to another user");
        addAction(5, ()->transferToExternal(), "Pay external bill");
        addAction(6, ()->withdraw(), "Withdraw money");
    }

    void showBalance() {
        System.out.print("This account has a ");
        if(asset) {
            System.out.print("debit");
        } else {
            System.out.print("credit");
        }
        System.out.println(" balance of:");
        System.out.println("$"+balance);
    }

    void depositCash() {
        //Todo: This format is wrong. It should be in a text file.
        Scanner s = new Scanner(System.in);
        System.out.println("How many Fives?");
        int fives = s.nextInt();
        System.out.println("How many Tens?");
        int tens = s.nextInt();
        System.out.println("How many Twenties?");
        int twenties = s.nextInt();
        System.out.println("How many Fifties?");
        int fifties = s.nextInt();
        int total = fives*5 + tens*10 + twenties*20 + fifties*50;
        if(asset) {
            balance += total;
        } else {
            balance -= total;
        }
        ATM_machine.setFives(ATM_machine.getNumFives()+fives);
        ATM_machine.setTens(ATM_machine.getNumTens()+tens);
        ATM_machine.setTwenties(ATM_machine.getNumTwenties()+twenties);
        ATM_machine.setFifties(ATM_machine.getNumFifties()+fifties);
    }

    void transferToSelf() {
        //TODO: Select an account, somehow
    }

    void transferToOther() {
        //Todo: Select a user, then one of their accounts.
    }

    void transferToExternal() {
        //Todo: Request name of bill.
    }

    void withdraw() {
        Scanner s = new Scanner(System.in);
    }

    /*
    * Internal methods.
    */

    // Set the balance of the account
    void setBalance(double balance) {
        this.balance = balance;
    }

    // True if customer owes money, false otherwise
    void setAsset(boolean asset) {
        this.asset = asset;
    }

    double getBalance() {
        return balance;
    }

    boolean isAsset() {
        return asset;
    }

    // Add to the balance
    abstract void transferIn(double amount);

    // Subtract from the balance
    abstract void transferOut(double amount);

    // Returns the latest transaction.
    abstract double getLatestTransaction();

    // Revert last transaction.
    abstract void revertTransaction();

    //Get a string representation
    abstract String getSummary();

    // Check if balance is equal to amount
    abstract boolean checkAmount(double amount);

    // Get date account was created.
    abstract Calendar getCreation_date();
}