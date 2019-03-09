// An abstract Class for Acounts


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericAccount extends TextInterface implements Serializable {

    Customer owner;
    double balance;
    boolean asset;
    String name;
    ArrayList<String> past_trans;
    Calendar creation_date;

    Runnable lastTransReverter;
    String lastTransText;

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

    //Updates this balance and other balance
    void transferBetweenHelper(GenericAccount other_acc, Double amount, String u) {
        if (asset) {
            balance -= amount;
        }
        else {
            balance += amount;
        }
        lastTransText = "Transferred $" + Math.abs(amount) + " From:" + name + " to " + u + ": " + other_acc.name;
        past_trans.add(lastTransText);

        // Run the transaction in the other acc
        if (other_acc.asset) {
            other_acc.balance += amount;
        }
        else {
            other_acc.balance -= amount;
        }
        ATM_machine.update_user(owner);
    }

    void depositCash() {
        Scanner s = new Scanner(System.in);
        System.out.println("How many Fives?");
        int fives = s.nextInt();
        System.out.println("How many Tens?");
        int tens = s.nextInt();
        System.out.println("How many Twenties?");
        int twenties = s.nextInt();
        System.out.println("How many Fifties?");
        int fifties = s.nextInt();
        Double total = (double) (fives*5 + tens*10 + twenties*20 + fifties*50);
        if(asset) {
            balance += total;
        } else {
            balance -= total;
        }
        ATM_machine.setFives(ATM_machine.getNumFives()+fives);
        ATM_machine.setTens(ATM_machine.getNumTens()+tens);
        ATM_machine.setTwenties(ATM_machine.getNumTwenties()+twenties);
        ATM_machine.setFifties(ATM_machine.getNumFifties()+fifties);

        FileManager.writeOutgoing(owner.getUsername(), name, total);

        lastTransReverter = ()-> revertDeposit();
        lastTransText = "Deposited cash amount of $"+total + " to: " + name;
        past_trans.add(lastTransText);

        ATM_machine.update_user(owner);
    }
    void revertDeposit() {
        if(asset) {
            balance -= getLastAmount();
        } else {
            balance += getLastAmount();
        }
        past_trans.remove(past_trans.size() - 1);
        lastTransText = getLatestTransaction();
        ATM_machine.update_user(owner);
    }

    void transferToSelf() {
        Scanner i = new Scanner(System.in);
        System.out.println("Amount to transfer?");
        Double amount = i.nextDouble();
        System.out.println("Account to transfer to?");
        String other_acc_name = i.next();
        GenericAccount other_acc = owner.getAccountByName(other_acc_name);
        // Maybe print an error message if other_acc_name does not exist.
        // Add this transaction to this account
        transferBetweenHelper(other_acc, amount, "");
        lastTransReverter = ()-> revertSelfTransfer();

    }
    void revertSelfTransfer() {
        String other_acc_name = lastTransText.split(" ")[lastTransText.length() - 1];
        Double amount = getLastAmount();
        GenericAccount other_acc = owner.getAccountByName(other_acc_name);
        if (asset) {
            balance += amount;
        }
        else {
            balance -=amount;
        }
        if (other_acc.asset) {
            other_acc.balance -= amount;
        }
        else {
            other_acc.balance += amount;
        }
        past_trans.remove(past_trans.size() - 1);
        lastTransText = getLatestTransaction();
        ATM_machine.update_user(owner);
    }

    Customer transferToOther_helper() {
        // Get the user and the account
        Scanner s = new Scanner(System.in);
        System.out.println("Who would you like to transfer to");
        String other_username = s.next();
        boolean other_exists = false;
        Customer other_user = null;
        ArrayList<ATM_User> all_users = ATM_machine.users;
        for (ATM_User u: all_users) {
            if (u.getUsername().equals(other_username)) {
                other_user = (Customer) u;
                other_exists = true;
                break;
            }
        }
        if (!other_exists) {
            System.out.println("That username does not exist, please try another user.");
            transferToOther_helper();
        }
        return other_user;
    }

    void transferToOther() {
        Scanner s = new Scanner(System.in);
        Customer other_user = transferToOther_helper();
        String message = "Which account of " + other_user.getUsername() + " would you like to transfer to?";
        System.out.println(message);
        String other_acc_name = s.next();
        GenericAccount other_acc = other_user.getAccountByName(other_acc_name);
        System.out.println("How much would you like to transfer?");
        Double amount = s.nextDouble();
        transferBetweenHelper(other_acc, amount, other_acc.owner.getUsername());
        lastTransReverter = () -> revertTransferOther();
        ATM_machine.update_user(other_user);
    }

    void revertTransferOther() {




    }
    void transferToExternal() {
        Scanner s = new Scanner(System.in);
        System.out.println("What bill would you like to pay?");
        String name = s.nextLine();
        System.out.println("How much are you paying?");
        double amount = s.nextDouble();
        if(asset) {balance-=amount;}
        else {balance+=amount;}
        //Todo: output to outbound.txt and update user entry
    }

    void withdraw() {
        Scanner s = new Scanner(System.in);
        System.out.println("How much would you like to withdraw?");
        System.out.println("Amount should be a multiple of 5");
        int amount = s.nextInt();
        int[] bills = get_bill_split(amount);
        int fifties = bills[0];
        int twenties = bills[1];
        int tens = bills[2];
        int fives = bills[3];
        balance -= (fifties*50 + twenties*20 + tens*10 + fives*5);

        ATM_machine.setFifties(ATM_machine.getNumFifties() - fifties);
        ATM_machine.setTwenties(ATM_machine.getNumTwenties()- twenties);
        ATM_machine.setTens(ATM_machine.getNumTens() - tens);
        ATM_machine.setFives(ATM_machine.getNumFives()- fives);
    }

    int[] get_bill_split(int amount) {
        int[] bill_split = new int[4];
        int fifties = amount / 50;
        bill_split[0] = fifties;
        int rem_50 = amount % 50;
        int twenties = rem_50 / 20;
        bill_split[1] = twenties;
        int rem_10 = rem_50 % 20;
        int tens = rem_10 / 10;
        bill_split[2] = tens;
        int rem_5 = rem_10 % 10;
        int fives = rem_5 / 5;
        bill_split[3] = fives;
        return bill_split;
    }

    // Return the amount the was last transferred.
    Double getLastAmount() {
        Matcher m = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+").matcher(getLatestTransaction());
        Double amount;
        if (m.find()) {
            return Double.valueOf(m.group(0));
        }
        else {
            return 0.0;
        }
    }

    boolean isAsset() {
        return this.asset;
    }

    Double getBalance() {
        return this.balance;
    }

    // Set the balance of the account
    void setBalance(double balance) {
        this.balance = balance;
    }

    // Returns the latest transaction.
    String getLatestTransaction() {return past_trans.get(past_trans.size() - 1);}


    //Get a string representation
    String getSummary() {
        return "name: " + name + "\n" + "Owner: " + owner.getUsername() + "\n"
                + "Asset: " + asset + "Balance: " + balance + "Last Transaction: " + lastTransText;
    }
}