import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class StockAccount extends GenericAccount implements Serializable {

	public StockAccount(String name_p, Customer o) {
		name = name_p;
		owner = o;
		past_trans = new ArrayList<String>();
		balance = 0;
		asset = true;
		creation_date = new GregorianCalendar();
		lastTransText = "No transactions have been made";
		past_trans.add(lastTransText);
		type = "(Stock)";
	}
}
