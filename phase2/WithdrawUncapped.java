import java.io.Serializable;

public class WithdrawUncapped implements IWithdrawable, Serializable {

    public double withdraw(double amount, double balance){
        ATM_machine.withdrawBills((int)amount);
        return balance - (int)amount;
    }
}
