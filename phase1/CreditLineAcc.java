// A class for the credit line

import java.util.ArrayList;

public class CreditLineAcc implements AccountInterface {

    private ArrayList<double[]> past_trans;
    private double[] latest_trans;
    private double balance;
    private boolean owes;

    public CreditLineAcc() {
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

    public void transfer_out(double amount) {
        double past_bal = this.getBalance();
        this.balance -= amount;
        double[] curr_trans = {past_bal, this.balance};
        past_trans.add(curr_trans);
        latest_trans = curr_trans;
    }


    public double[] getLatestTrans() {
        return latest_trans;
    }
}