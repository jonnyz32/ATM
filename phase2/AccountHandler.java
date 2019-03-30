import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountHandler implements Serializable {
    private ArrayList<GenericAccount> accounts;
    private IAccountHolder user;

    static Object[][] accountList = new Object[][]{
            {"PrimaryChequing",PrimaryChequingAcc.class},
            {"Chequing", ChequingAcc.class},
            {"CreditCard", CreditCardAcc.class},
            {"CreditLine", CreditLineAcc.class},
            {"Savings", SavingAcc.class},
            {"Stocks", StockAccount.class}
    };

    AccountHandler(IAccountHolder user){
        this.user = user;
        accounts = new ArrayList<>();
    }

    void requestAccount(String type, String name){
        new BankManager().requestAccount(user.getUsername(), type, name);
    }

    void addAccount(String type, String name) {
        for(Object[] accountType : accountList) {
            if(type.equalsIgnoreCase((String)accountType[0])) {
                try {
                    GenericAccount newAcc = (GenericAccount)((Class)accountType[1]).getDeclaredConstructor(String.class,IAccountHolder.class).newInstance(name,user);
                    accounts.add(newAcc);
                    ATM_machine.getUserManager().saveUsers();
                } catch(Exception e) {
                    System.out.println("An internal error has occurred.");
                }
            }
        }
    }

    void addAccount(GenericAccount acc) {
        accounts.add(acc);
    }

    //Make sure there is only one primary account
    //There should only be one other primary account because it gets checked everytime an account is created
    void checkChequingPrimary(ArrayList<GenericAccount> accs, String name) {
        int idx = 0;
        for (GenericAccount a: accs) {
            if (a.type.equals(" (Chequing)") && !a.name.equals(name) && ((ChequingAcc) a).isPrimary()) {
                idx = accs.indexOf(a);
                break;
            }
        }
        if (idx != 0) {
            ChequingAcc c = (ChequingAcc) accs.get(idx);
            c.setPrimary(false);
            accs.set(idx, c);
        }
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
        GenericAccount account = null;
        for (GenericAccount a: accounts) {
            if (a.name.equals(name)) {
                account =  a;
                break;
            }
        }
        return account;
    }
}
