// A super class for debt accounts.

import java.io.Serializable;

public class CreditCardAcc extends GenericAccount implements Serializable{

    public CreditCardAcc(String name, IAccountHolder o) {
        super(name, o);
        asset = false;
        type = "CREDIT CARD";
        withdrawable = new CannotWithdraw();
    }
}