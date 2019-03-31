import java.io.Serializable;

public class WithdrawUncapped implements IWithdrawable, Serializable {

    public double withdraw(int amount, double balance){
        new BillHandler().withdrawBills(amount);
        return balance - amount;
    }
}
