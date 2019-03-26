public class WithdrawCapped implements IWithdrawable{
    private double minBalance;
    WithdrawCapped(int minBalance){
        this.minBalance = minBalance;
    }

    public double withdraw(double amount, double balance){
        int dollars = (int)amount;
        if (balance + minBalance >= dollars){
            ATM_machine.withdrawBills(dollars/5);
            return balance - dollars;
        }
        else {
            System.out.println("You don't have enough money remaining to withdraw that much! Balance: " + balance);
            return -1;
        }
    }
}
