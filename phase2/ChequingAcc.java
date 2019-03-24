// A class for Chequing accounts

import java.io.Serializable;

public class ChequingAcc extends GenericAccount implements Serializable {
    private boolean primary;

    public ChequingAcc(String name, IAccountHolder o, boolean primary) {
        super(name, o);
        this.name = name;
        asset = true;
        type = "CHEQUING";
        withdrawable = new WithdrawCapped(-100);
        this.primary = primary;
    }

    boolean isPrimary() {
        return primary;
    }

    void setPrimary(boolean p) {
        this.primary = p;
    }
}
