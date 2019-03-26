public class WithdrawCappedNonNegative implements IWithdrawable{
    double minBalance;
    WithdrawCappedNonNegative(int minBalance){
        this.minBalance = minBalance;
    }

    public double withdraw(double amount, double balance){
        int dollars = (int)amount;
        if (balance > 0 && balance + minBalance >= dollars){
            ATM_machine.withdrawBills(dollars);
            return balance - dollars;
        }
        else {
            System.out.println("You don't have enough money remaining to withdraw that much! Balance: " + balance);
            return -1;
        }
    }
}
