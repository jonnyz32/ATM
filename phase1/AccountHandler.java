import java.util.ArrayList;
import java.util.List;

public class AccountHandler {
    private ArrayList<GenericAccount> accounts;
    private IAccountHolder user;

    AccountHandler(IAccountHolder user){
        this.user = user;
        accounts = new ArrayList<GenericAccount>();
    }

    boolean addAccount(String type, String name) {
        if (type.equals("chequing")) {
            // Check if there are other chequing accounts.
            for (GenericAccount a : accounts) {
                if (a instanceof ChequingAcc) {
                    accounts.add(new ChequingAcc(name, user, false));
                }
            }
            accounts.add(new ChequingAcc(name, user, true));
        } else if (type.equals("credit")) {
            accounts.add(new CreditCardAcc(name, user));
        } else if (type.equals("creditline")) {
            accounts.add(new CreditLineAcc(name, user));
        } else if (type.equals("savings")) {
            accounts.add(new SavingAcc(name, user));
        } else {
            return false;
        }
        return true;
    }

    ArrayList<GenericAccount> getAccounts() {
        return accounts;
    }

    //Summary of account balances
    String getFullSummary(){
        String summary = "";
        for (GenericAccount acc : accounts){
            summary += acc.getSummary();
            summary += "\n";
        }
        return summary;
    }

    double getNetTotal(){
        double total = 0;
        for (GenericAccount acc : accounts) {
            if (acc.isAsset()) {
                total += acc.getBalance();
            } else {
                total -= acc.getBalance();
            }
        }
        return total;
    }

    /**
     * Gets an account given the name.
     * Assume account exists.
     */
    GenericAccount getAccountByName(String name) {
        GenericAccount account = new ChequingAcc("BAD", user, false);
        for (GenericAccount a: accounts) {
            if (a.name.equals(name)) {
                account =  a;
                break;
            }
        }
        return account;
    }
}
