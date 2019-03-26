public class WithdrawUncapped implements IWithdrawable{

    public double withdraw(double amount, double balance){
        int dollars = (int)amount;
        ATM_machine.withdrawBills(dollars);
        return balance - dollars;
    }
}
