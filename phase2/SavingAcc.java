// A class for savings accounts

import java.io.Serializable;
import java.util.*;

public class SavingAcc extends GenericAccount implements Serializable {

    public SavingAcc(String name, IAccountHolder o) {
        super(name, o);
        asset = true;
        type = "SAVINGS";
        withdrawable = new WithdrawCapped(0);
    }

    void increase_interest() {
        balance += balance * 0.001;
    }
}