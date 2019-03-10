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
        ATM_machine.update_user(owner);
    }

    @Override
    void withdraw(int amount){
        if (balance >= amount){
            super.withdraw(amount);
        }
        else{
            System.out.println("You don't have enough money to withdraw this much! Balance: " + balance);
        }
    }


}