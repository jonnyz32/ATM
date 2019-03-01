// A class for Chequing accounts

import java.util.ArrayList;
import java.util.Arrays;

public class ChequingAcc implements AccountInterface {
    private ArrayList<double[]> past_trans;
    private double[] latest_trans;
    private double balance;
    private boolean owes;

    public ChequingAcc() {
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

    public void revertTransaction() {
        double[] revert_trans = past_trans.get(past_trans.size() - 1);
        this.setBalance(revert_trans[0]);
        latest_trans = past_trans.get(past_trans.size() - 2);
        past_trans.remove(past_trans.size() - 1);
    }

    public String getSummary() {
        String lt = Arrays.toString(latest_trans);
        return "Latest Transaction: " + lt + "\r\n" +
                "Balance: " + balance;
    }

    public boolean checkAmount(double amount) {
        return amount == balance;
    }

}
