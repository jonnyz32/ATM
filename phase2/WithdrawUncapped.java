public class WithdrawUncapped implements IWithdrawable{

    public double withdraw(int amount, double balance){
        ATM_machine.withdrawBills(amount);
        return balance - amount;
    }
}
