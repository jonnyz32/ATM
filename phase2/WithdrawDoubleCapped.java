import java.io.Serializable;

public class WithdrawDoubleCapped implements IWithdrawable, Serializable {
    private double minBalance;
    WithdrawDoubleCapped(int minBalance){
        this.minBalance = minBalance;
    }

    public double withdraw(int amount, double balance){
        if (balance > 0 && balance + minBalance >= amount){
            ATM_machine.withdrawBills(amount);
            return balance - amount;
        }
        else {
            System.out.println("You don't have enough money remaining to withdraw that much! Balance: " + balance);
            return -1;
        }
    }
}
