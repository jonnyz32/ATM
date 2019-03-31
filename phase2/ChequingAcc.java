// A class for Chequing accounts

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

    boolean isPrimary() {
        return primary;
    }

    void setPrimary(boolean p) {
        this.primary = p;
    }

    @Override
    String getSummary() {
        return "name: " + name + "\n" + "Owner: " + owner.getUsername() + "\n"
                + "Asset: " + asset + "\n" + "Balance: " + balance + "\n" + "Primary: " + primary + "\n" +
                "Last Transaction: " + getLatestTransaction();
    }
}
