public class WithdrawCapped implements IWithdrawable{
    double minBalance;
    WithdrawCapped(int minBalance){
        this.minBalance = minBalance;
    }

    public double withdraw(double amount, double balance){
        if (balance + minBalance >= (int)amount){
            ATM_machine.withdrawBills((int)amount/5);
            return balance - (int)amount;
        }
        else {
            System.out.println("You don't have enough money remaining to withdraw that much! Balance: " + balance);
            return -1;
        }
    }
}
