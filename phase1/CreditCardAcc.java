// A super class for debt accounts.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CreditCardAcc implements AccountInterface {

    private ArrayList<double[]> past_trans;
    private double[] latest_trans;
    private double balance;
    private boolean owes;
    private Date creation_date;

    public CreditCardAcc() {
        // Array of double arrays with two values inside each double array.
        // First value is balance before transaction and second after.
        // Last item in ArrayList is latest transaction.
        past_trans = new ArrayList<>();
        balance = 0;
        owes = false;
        creation_date = new Date();
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOwes(boolean owes) {
        this.owes = owes;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean getOwes() {
        return this.owes;
    }

    public void transfer_in(double amount) {

        double past_bal = this.getBalance();
        this.balance += amount;
        double[] curr_trans = {past_bal, this.balance};
        past_trans.add(curr_trans);
        latest_trans = curr_trans;
    }

    public void transfer_out(double amount) {
    }

    public double[] getLatestTrans() {
        return latest_trans;
    }

    public void revertTransaction() {
        double[] revert_trans = past_trans.get(past_trans.size() - 1);
        this.setBalance(revert_trans[0]);
        latest_trans = past_trans.get(past_trans.size() - 2);
        past_trans.remove(past_trans.size() - 1);
    }

    public String getSummary() {
        String lt = Arrays.toString(latest_trans);
        return "Latest Transaction: " + lt + "\r\n" +
                "Balance:" + balance;
    }

    public boolean checkAmount(double amount) {
        return amount == balance;
    }

    public Date getCreation_date() {
        return creation_date;
    }

}