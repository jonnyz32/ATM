// An abstract Class for Acounts


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public abstract class GenericAccount extends TextInterface {

    Customer owner;
    double balance;
    boolean asset;
    String name;
    ArrayList<String> past_trans;
    Calendar creation_date;

    //ToDo: How to track last transaction?
    Runnable lastTransReverter;
    // lastTrans is negative if money was taking out and positive if money went in
    String lastTrans;
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
        lastTransText = getLatestTransaction();
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

        lastTransReverter = ()-> revertDeposit();
        lastTransText = "Deposited cash amount of $"+total + " to " + name;
        past_trans.add(lastTrans);
        lastTransOtherAcc = null;
    }
    void revertDeposit() {
        if(asset) {
            balance -= getLastAmount();
        } else {
            balance += getLastAmount();
        }
        lastTrans = past_trans.get(past_trans.size() - 2);
        past_trans.remove(past_trans.size() - 1);
    }

    void transferToSelf() {
        //TODO: Select an account, somehow
        Scanner i = new Scanner(System.in);
        System.out.println("Amount to transfer?");
        Double amount = i.nextDouble();
        System.out.println("Account to transfer to?");
        String other_acc_name = i.next();
        GenericAccount other_acc = owner.getAccountByName(other_acc_name);
        // Maybe print an error message if other_acc_name does not exist.
        lastTransText = "Transferred $" + Math.abs(amount) + " From:" + name + " to "+other_acc.name;
        // Add this transaction to this account
        if (asset) {
            balance -= amount;
            past_trans.add(lastTransText);
        }
        else {
            balance += amount;
            past_trans.add(lastTransText);
        }
        if (other_acc.asset) {
            other_acc.balance += amount;
            other_acc.past_trans.add(lastTransText);
        }
        else {
            other_acc.balance -= amount;
            other_acc.past_trans.add(lastTransText);

        }
        lastTransReverter = ()-> revertSelfTransfer();
        //ToDo: Presumably, this influences the other account's last transaction too. Have to to that.
    }
    void revertSelfTransfer() {
        String other_acc = lastTransText;


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

    // Return the amount the was last transferred, with appropriate sign.
    Double getLastAmount() {
        String[] a = lastTransText.split(" ");
        String s1 = a[0].split(" ")[a.length - 1];
        return Double.valueOf(s1.substring(1));


    }


    // True if asset account, false is debt account
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
    String getLatestTransaction() {return past_trans.get(past_trans.size() - 1);}


    //Get a string representation
    abstract String getSummary();

    // Check if balance is equal to amount
    abstract boolean checkAmount(double amount);

    // Get date account was created.
    abstract Calendar getCreation_date();
}