package UserClasses.Users;

import AccountClasses.Accounts.GenericAccount;
import UserClasses.Misc.AccountHandler;

import java.io.Serializable;
import java.util.ArrayList;

public class Employee extends ATM_User implements Serializable, ILevelOneAccess, IAccountHolder {

    AccountHandler accountHandler;
    private LevelOneAccessHandler levelOneAccessHandler;

    public Employee(String username, String password){
        super(username, password);
        accountHandler = new AccountHandler(this);
        levelOneAccessHandler = new LevelOneAccessHandler();
    }

    public void addBills(int fives, int tens, int twenties, int fifties){
        levelOneAccessHandler.addBills(fives, tens, twenties, fifties);
    }

    public boolean createNewUser(String username, String password, int type) {
        return levelOneAccessHandler.createNewUser(username, password, 1);
    }

    public void requestAccount(String type, String name) {
        accountHandler.requestAccount(type, name);
    }

    public void addAccount(String type, String name) {
        accountHandler.addAccount(type, name);
    }

    public void addAccount(GenericAccount acc) {accountHandler.addAccount(acc);}

    public ArrayList<GenericAccount> getAccounts() {
        return accountHandler.getAccounts();
    }

    //Summary of account balances
    public String getFullSummary() {
        return accountHandler.getFullSummary();
    }

    //Net total of all accounts
    public double getNetTotal() {
        return accountHandler.getNetTotal();
    }

    public GenericAccount getAccountByName(String name) {
        return accountHandler.getAccountByName(name);
    }
}
