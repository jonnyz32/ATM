package AccountClasses.Withdrawals;

import java.io.Serializable;

public class CannotWithdraw implements IWithdrawable, Serializable {
    public double withdraw(int amount, double balance){
        return balance;
    }
}
