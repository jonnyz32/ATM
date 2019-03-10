// A class for the credit line

import java.io.Serializable;
import java.util.*;
public class CreditLineAcc extends GenericAccount implements Serializable {

    public CreditLineAcc(String name_p, Customer o) {
        name = name_p;
        owner = o;
        past_trans = new ArrayList<String>();
        balance = 0;
        asset = false;
        creation_date = new GregorianCalendar();
        lastTransText = "No transactions have been made";
        past_trans.add(lastTransText);
        type = "(CreditLine)";
    }
}