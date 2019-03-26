public class WithdrawCappedNonNegative implements IWithdrawable{
    double minBalance;
    WithdrawCappedNonNegative(int minBalance){
        this.minBalance = minBalance;
    }

    public double withdraw(double amount, double balance){
        if (balance > 0 && balance + minBalance >= (int)amount){
            ATM_machine.withdrawBills((int)amount);
            return balance - (int)amount;
        }
        else {
            System.out.println("You don't have enough money remaining to withdraw that much! Balance: " + balance);
            return -1;
        }
    }
}
