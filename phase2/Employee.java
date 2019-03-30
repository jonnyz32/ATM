import java.io.Serializable;
import java.util.ArrayList;

public class Employee extends ATM_User implements Serializable, ILevelOneAccess, IAccountHolder{

    private AccountHandler accountHandler;
    private LevelOneAccessHandler levelOneAccessHandler;

    Employee(String username, String password){
        super(username, password);
        accountHandler = new AccountHandler(this);
        levelOneAccessHandler = new LevelOneAccessHandler();
    }

    public void addBills(int fives, int tens, int twenties, int fifties){
        levelOneAccessHandler.addBills(fives, tens, twenties, fifties);
    }

    public void createNewUser(String username, String password, int type) {
        levelOneAccessHandler.createNewUser(username, password, type);
    }

    public void requestAccount(String type, String name) {
        accountHandler.requestAccount(type, name);
    }

    public void addAccount(String type, String name) {
        accountHandler.addAccount(type, name);
    }

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
