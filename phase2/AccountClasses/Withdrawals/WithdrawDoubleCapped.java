package AccountClasses.Withdrawals;

import TopClasses.BillHandler;

import java.io.Serializable;

public class WithdrawDoubleCapped implements IWithdrawable, Serializable {
    private double minBalance;
    private double softMinBalance;
    public WithdrawDoubleCapped(int minBalance, int softMinBalance){
        this.minBalance = minBalance;
        this.softMinBalance = softMinBalance;
    }

    public double withdraw(int amount, double balance){
        if (balance > softMinBalance && balance + minBalance >= amount){
            new BillHandler().withdrawBills(amount);
            return balance - amount;
        }
        else {
            System.out.println("You don't have enough money remaining to withdraw that much! Balance: " + balance);
            return -1;
        }
    }
}
