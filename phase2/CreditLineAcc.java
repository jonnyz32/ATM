// A class for the credit line

import java.io.Serializable;
import java.util.*;
public class CreditLineAcc extends GenericAccount implements Serializable {

    double points;
    public CreditLineAcc(String name, IAccountHolder o) {
        super(name, o);
        asset = false;
        type = "CREDIT LINE";
        withdrawable = new WithdrawUncapped();
        points = 0;
    }
    @Override
    String getSummary() {
        return "name: " + name + "\n" + "Owner: " + owner.getUsername() + "\n"
                + "Asset: " + asset + "\n" + "Balance: " + balance + "\n" + "Points: " + points + "\n" +
                "Last Transaction: " + getLatestTransaction();
    }
}