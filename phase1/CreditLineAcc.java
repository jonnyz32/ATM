// A class for the credit line

import java.util.*;

public class CreditLineAcc extends GenericAccount {


    public CreditLineAcc() {
        name = "New Line of Credit";
        past_trans = new ArrayList<Double>();
        balance = 0;
        asset = false;
        creation_date = new GregorianCalendar();
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAsset(boolean asset) {
        this.asset = asset;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean getasset() {
        return this.asset;
    }

    public void transferIn(double amount) {
        this.balance += amount;
        past_trans.add(amount);
        lastTrans = amount;

    }

    public void transferOut(double amount) {
        this.balance -= amount;
        past_trans.add(-amount);
        lastTrans = -amount;
    }

    public Double getLatestTransaction() {
        lastTrans = past_trans.get(past_trans.size() - 1);
        return lastTrans;
    }

    public void revertTransaction() {
        Double to_revert = this.getLatestTransaction();
        if (to_revert >= 0) {
            // If latest trans is positive, it was a transfer in, so subtract from balance to revert
            this.balance -= to_revert;
        }
        else {
            this.balance += to_revert;
        }
        lastTrans = past_trans.get(past_trans.size() - 2);
        past_trans.remove(past_trans.size() - 1);
    }

    public String getSummary() {
        String lt = lastTrans.toString();
        return "Latest Transaction: " + lt + "\r\n" +
                "Balance:" + balance;
    }

    public boolean checkAmount(double amount) {
        return amount == balance;
    }

    public Calendar getCreation_date() {
        return creation_date;
    }
}