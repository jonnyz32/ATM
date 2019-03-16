// A super class for debt accounts.

import java.io.Serializable;
import java.util.*;

public class CreditCardAcc extends GenericAccount {
    private final int maxDebt = 3000;



    public CreditCardAcc(String name_p, Customer o) {
        name = name_p;
        owner = o;
        past_trans = new ArrayList<String>();
        balance = 0;
        asset = false;
        creation_date = new GregorianCalendar();
        lastTransText = "No transactions have been made";
        past_trans.add(lastTransText);
        type = " (CreditCard)";
    }

    @Override
    boolean withdraw(int amount){
        if (balance + amount <= maxDebt){
            balance += amount;
            return super.withdraw(amount);
        }
        return false;
    }
}