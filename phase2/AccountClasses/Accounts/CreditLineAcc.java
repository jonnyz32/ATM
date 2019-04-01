package AccountClasses.Accounts;// A class for the credit line

import AccountClasses.Withdrawals.WithdrawCapped;
import UserClasses.Users.IAccountHolder;

import java.io.Serializable;

public class CreditLineAcc extends GenericAccount implements Serializable {

    static int maxDebt = 1000;

    public double points;
    public CreditLineAcc(String name, IAccountHolder o) {
        super(name, o);
        asset = false;
        type = "CREDIT LINE";
        withdrawable = new WithdrawCapped(-2000000000,maxDebt);
        points = 0;
    }
    @Override
    public String getSummary() {
        return "name: " + name + "\n" + "Owner: " + owner.getUsername() + "\n"
                + "Asset: " + asset + "\n" + "Balance: " + balance + "\n" + "Points: " + points + "\n" +
                "Last Transaction: " + getLatestTransaction();
    }
}