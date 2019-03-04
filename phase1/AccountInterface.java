// An abstract Class for Acounts


import java.util.Calendar;
import java.util.Date;

public interface AccountInterface {


    // Set the balance of the account
    void setBalance(double balance);

    // True if customer owes money, false otherwise
    void setOwes(boolean owes);

    double getBalance();

    boolean getOwes();

    // Add to the balance
    void transfer_in(double amount);

    // Subtract from the balance
    void transfer_out(double amount);

    // Returns the latest transaction.
    double[] getLatestTrans();

    // Revert last transaction.
    void revertTransaction();

    //Get a string representation
    String getSummary();

    // Check if balance is equal to amount
    boolean checkAmount(double amount);

    // Get date account was created.
    Calendar getCreation_date();
}