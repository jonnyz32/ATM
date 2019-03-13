// A class for savings accounts

import java.io.Serializable;
import java.util.*;

public class SavingAcc extends GenericAccount implements Serializable {

    public SavingAcc(String name_p, Customer o) {
        name = name_p;
        owner = o;
        past_trans = new ArrayList<String>();
        balance = 0;
        asset = true;
        creation_date = new GregorianCalendar();
        lastTransText = "No transactions have been made";
        past_trans.add(lastTransText);
        type = "(Savings)";
    }

    void increase_interest() {
        balance += balance * 0.001;
    }

    @Override
    boolean withdraw(int amount){
        if (balance >= amount){
            super.withdraw(amount);
            return true;
        }
        else{
            return false;
        }
    }


}