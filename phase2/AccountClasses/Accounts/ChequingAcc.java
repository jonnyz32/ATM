package AccountClasses.Accounts;// A class for Chequing accounts

import AccountClasses.Withdrawals.WithdrawCappedNonNegative;
import UserClasses.Users.IAccountHolder;

import java.io.Serializable;

public class ChequingAcc extends GenericAccount implements Serializable {
    protected boolean primary;

    public ChequingAcc(String name, IAccountHolder o) {
        super(name, o);
        this.name = name;
        asset = true;
        type = "CHEQUING";
        withdrawable = new WithdrawCappedNonNegative(-100);
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
