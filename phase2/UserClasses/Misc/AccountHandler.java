package UserClasses.Misc;

import AccountClasses.Accounts.*;
import TopClasses.UserManager;
import UserClasses.Users.BankManager;
import UserClasses.Users.IAccountHolder;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountHandler implements Serializable {
    private ArrayList<GenericAccount> accounts;
    private IAccountHolder user;

    public static Object[][] accountList = new Object[][]{
            {"PrimaryChequing", PrimaryChequingAcc.class},
            {"Chequing", ChequingAcc.class},
            {"CreditCard", CreditCardAcc.class},
            {"CreditLine", CreditLineAcc.class},
            {"Savings", SavingAcc.class},
            {"Stocks", StockAccount.class}
    };

    public AccountHandler(IAccountHolder user){
        this.user = user;
        accounts = new ArrayList<>();
    }

    public void requestAccount(String type, String name){
        new BankManager().requestAccount(user.getUsername(), type, name);
    }

    public void addAccount(String type, String name) {
        for(Object[] accountType : accountList) {
            if(type.equalsIgnoreCase((String)accountType[0])) {
                try {
                    GenericAccount newAcc = (GenericAccount)((Class)accountType[1]).getDeclaredConstructor(
                                                    String.class,IAccountHolder.class).newInstance(name,user);
                    accounts.add(newAcc);
                } catch(Exception e) {
                    System.out.println("An internal error has occurred.");
                }
            }
        }
        new UserManager().saveUsers();
    }

    public void addAccount(GenericAccount acc) {
        accounts.add(acc);
        new UserManager().saveUsers();
    }

    //Make sure there is only one primary account
    //There should only be one other primary account because it gets checked everytime an account is created
    public void checkChequingPrimary(String name) {
        ArrayList<GenericAccount> accs = getAccounts();
        int[] idx = new int[accs.size()];
        idx[0] = -1;
        int i = 0;
        for (GenericAccount a: accs) {
            if (a instanceof ChequingAcc && !a.name.equals(name) && ((ChequingAcc) a).isPrimary()) {
                idx[i] = (accs.indexOf(a));
                i += 1;
            }
        }
        for (int i2: idx) {
            if (i2 != 0 && i2 != -1) {
                ChequingAcc c = (ChequingAcc) accs.get(i2);
                c.setPrimary(false);
                accs.set(i2, c);
            }
        }
        new UserManager().saveUsers();
    }

    public ArrayList<GenericAccount> getAccounts() {
        return accounts;
    }

    //Summary of account balances
    public String getFullSummary(){
        String summary = "";
        for (GenericAccount acc : accounts){
            summary += acc.getSummary();
            summary += "\n";
        }
        return summary;
    }

    public double getNetTotal(){
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
    public GenericAccount getAccountByName(String name) {
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
