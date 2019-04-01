package AccountClasses.Accounts;// A super class for debt accounts.

import AccountClasses.Withdrawals.CannotWithdraw;
import UserClasses.Users.IAccountHolder;

import java.io.Serializable;

public class CreditCardAcc extends GenericAccount implements Serializable{

    public CreditCardAcc(String name, IAccountHolder o) {
        super(name, o);
        asset = false;
        type = "CREDIT CARD";
        withdrawable = new CannotWithdraw();
    }

    @Override
    public String getSummary() {
        return "name: " + name + "\n" + "Owner: " + owner.getUsername() + "\n"
                + "Asset: " + asset + "\n" + "Balance: " + balance + "\n" +
                "Last Transaction: " + getLatestTransaction();
    }
}