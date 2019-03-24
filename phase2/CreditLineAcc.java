// A class for the credit line

import java.io.Serializable;
import java.util.*;
public class CreditLineAcc extends GenericAccount implements Serializable {

    public CreditLineAcc(String name, IAccountHolder o) {
        super(name, o);
        asset = false;
        type = "CREDIT LINE";
        withdrawable = new WithdrawUncapped();
    }
}