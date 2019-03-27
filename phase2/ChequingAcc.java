// A class for Chequing accounts

import java.io.Serializable;
import java.util.*;

public class ChequingAcc extends GenericAccount implements Serializable {
    private boolean primary;
    private final int maxDebt = 100;

    public ChequingAcc(String name_p, IAccountHolder o, boolean primary_p) {
        name = name_p;
        owner = o;
        past_trans = new ArrayList<String>();
        balance = 0;
        asset = true;
        creation_date = new GregorianCalendar();
        lastTransText = "No transactions have been made";
        past_trans.add(lastTransText);
        primary = primary_p;
        type = "CHEQUING";
    }

    boolean isPrimary() {
        return primary;
    }

    void setPrimary(boolean p) {
        this.primary = p;
    }

    @Override
    boolean withdraw(int amount){
        if (balance + maxDebt >= amount){
            balance -= amount;
            return super.withdraw(amount);
        }
        System.out.println("You don't have enough money remaining to withdraw that much! Balance: " + balance);
        return false;
    }


}
