// An abstract Class for Acounts


import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public abstract class GenericAccount extends TextInterface {

    double balance;
    boolean asset;
    String name;

    //ToDo: How to track last transaction?
    Runnable lastTransReverter;
    double lastTransTotal;
    String lastTransText;
    GenericAccount lastTransOtherAcc;

    public GenericAccount() {
        super();
        addAction(1, ()->showBalance(), "View Balance");
        addAction(2, ()->showLastTransaction(), "See Last Transaction");
        addAction(3, ()->depositCash(), "Deposit Cash");
        addAction(4, ()->transferToSelf(), "Transfer to another account");
        addAction(5, ()->transferToOther(), "Transfer to another user");
        addAction(6, ()->transferToExternal(), "Pay external bill");
        addAction(7, ()->withdraw(), "Withdraw money");
    }

    void showLastTransaction() {
        System.out.println("Last Transaction:");
        System.out.println(lastTransText);
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

        lastTransTotal = total;
        lastTransReverter = revertDeposit();
        lastTransText = "Deposited cash amount of $"+lastTransTotal;
        lastTransOtherAcc = null;
    }
    void revertDeposit() {
        if(asset) {
            balance -= lastTransTotal;
        } else {
            balance += lastTransTotal;
        }
        lastTransText = "Reverted cash deposit of $"+lastTransTotal;
        lastTransTotal *= -1;
    }

    void transferToSelf(double total) {
        //TODO: Select an account, somehow
        GenericAccount other;
        
        lastTransTotal = total;
        lastTransReverter = ()->revertSelfTransfer();
        lastTransText = "Transferred $"+lastTransTotal+" to "+other.name;
        lastTransOtherAcc = other;
        //ToDo: Presumably, this influences the other account's last transaction too. Have to to that.
    }
    void revertSelfTransfer() {

    }

    void transferToOther() {
        //Todo: Select a user, then one of their accounts.
    }

    void transferToExternal() {
        Scanner s = new Scanner(System.in);
        System.out.println("What bill would you like to pay?");
        String name = s.nextLine();
        System.out.println("How much are you paying?");
        double amount = s.nextDouble();
        if(asset) {balance-=amount;}
        else {balance+=amount;}
        //Todo: output to outbound.txt
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