package AccountClasses.Accounts;// A class for Chequing accounts

import AccountClasses.Withdrawals.WithdrawDoubleCapped;
import UserClasses.Users.IAccountHolder;

import java.io.Serializable;

public class ChequingAcc extends GenericAccount implements Serializable {
    public boolean primary;

    public ChequingAcc(String name, IAccountHolder o) {
        super(name, o);
        this.name = name;
        asset = true;
        type = "CHEQUING";
        withdrawable = new WithdrawDoubleCapped(-100, 0);
        this.primary = false;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean p) {
        this.primary = p;
    }

    @Override
    public String getSummary() {
        return "name: " + name + "\n" + "Owner: " + owner.getUsername() + "\n"
                + "Asset: " + asset + "\n" + "Balance: " + balance + "\n" + "Primary: " + primary + "\n" +
                "Last Transaction: " + getLatestTransaction();
    }
}
