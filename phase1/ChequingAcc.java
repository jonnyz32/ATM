// A class for Chequing accounts

import java.io.Serializable;
import java.util.*;

public class ChequingAcc extends GenericAccount implements Serializable {
    final boolean primary;

    public ChequingAcc(String name_p, Customer o, boolean primary_p) {
        name = name_p;
        owner = o;
        past_trans = new ArrayList<String>();
        balance = 0;
        asset = true;
        creation_date = new GregorianCalendar();
        lastTransText = "No transactions have been made";
        past_trans.add(lastTransText);
        primary = primary_p;
        type = "(Chequing)";
    }

    boolean isPrimary() {
        return primary;
    }


}
