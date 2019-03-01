// A class for savings accounts

import java.util.ArrayList;

public class SavingAcc implements AccountInterface {
    private ArrayList<double[]> past_trans;
    private double[] latest_trans;
    private double balance;

    public SavingAcc() {
        past_trans = new ArrayList<>();
        balance = 0;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOwes(boolean owes) {}

    public double getBalance() {
        return this.balance;
    }

    public boolean getOwes() {return false;}

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