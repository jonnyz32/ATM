// A super class for debt accounts.

import java.util.ArrayList;

public class CreditCardAcc implements AccountInterface {

    private ArrayList<double[]> past_trans;
    private double[] latest_trans;
    private double balance;
    private boolean owes;

    public CreditCardAcc() {
        // Array of double arrays with two values inside each double array.
        // First value is balance before transaction and second after.
        past_trans = new ArrayList<>();
        balance = 0;
        owes = false;
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

    public void transfer_out(double amount) {}

    public double[] getLatestTrans() {
        return latest_trans;
    }

}