// An abstract Class for Acounts


import java.util.Calendar;
import java.util.Date;

public class GenericAccount {

    double balance;
    boolean asset;

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
    void transferIn(double amount);

    // Subtract from the balance
    void transferOut(double amount);

    // Returns the latest transaction.
    double getLatestTransaction();

    // Revert last transaction.
    void revertTransaction();

    //Get a string representation
    String getSummary();

    // Check if balance is equal to amount
    boolean checkAmount(double amount);

    // Get date account was created.
    Calendar getCreation_date();
}