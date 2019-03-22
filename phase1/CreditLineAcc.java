// A class for the credit line

import java.io.Serializable;
import java.util.*;
public class CreditLineAcc extends GenericAccount implements Serializable {
    private final int maxDebt = 10000;


    public CreditLineAcc(String name_p, IAccountHolder o) {
        name = name_p;
        owner = o;
        past_trans = new ArrayList<String>();
        balance = 0;
        asset = false;
        creation_date = new GregorianCalendar();
        lastTransText = "No transactions have been made";
        past_trans.add(lastTransText);
        type = "CREDIT LINE";
    }

    @Override
    boolean withdraw(int amount){
        if (balance + amount <= maxDebt){
            balance += amount;
            return super.withdraw(amount);
        }
        System.out.println("You don't have enough remaining credit to withdraw that much! Remaining credit: " +
                (maxDebt - balance));
        return false;
    }
}