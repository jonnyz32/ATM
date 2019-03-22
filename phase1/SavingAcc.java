// A class for savings accounts

import java.io.Serializable;
import java.util.*;

public class SavingAcc extends GenericAccount implements Serializable {
    private final int maxDebt = 0;


    public SavingAcc(String name_p, IAccountHolder o) {
        name = name_p;
        owner = o;
        past_trans = new ArrayList<String>();
        balance = 0;
        asset = true;
        creation_date = new GregorianCalendar();
        lastTransText = "No transactions have been made";
        past_trans.add(lastTransText);
        type = "SAVINGS";
    }

    void increase_interest() {
        balance += balance * 0.001;
    }

    @Override
    boolean withdraw(int amount){
        if (balance + maxDebt >= amount){
            super.withdraw(amount);
            return true;
        }

        System.out.println("You don't have enough money remaining to withdraw that much! Balance: " + balance);
        return false;
    }


}