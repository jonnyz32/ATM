package AccountClasses.Withdrawals;

import TopClasses.BillHandler;

import javax.swing.*;
import java.io.Serializable;

public class WithdrawCappedNonNegative implements IWithdrawable, Serializable {
    double minBalance;
    public WithdrawCappedNonNegative(int minBalance){
        this.minBalance = minBalance;
    }

    public double withdraw(int amount, double balance){
        if (balance > 0 && balance-amount >= minBalance){
            new BillHandler().withdrawBills(amount);
            return balance - amount;
        }
        else {
            String infoMessage = "Not enough money.";
            JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
            return balance;
        }
    }
}
