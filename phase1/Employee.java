import java.io.Serializable;
import java.util.ArrayList;

public class Employee extends ATM_User implements Serializable, IEmployee, IAccountHolder{

    Employee(String username, String password){
        super(username, password);
    }

    /*TODO: Delete these from here
    public void requestAccount(String type, String name){
        BankManager.requestAccount(this.getUsername(), type, name);
    }

    public boolean addAccount(String type, String name) {
        return accountHandler.addAccount(type, name);
    }

    public ArrayList<GenericAccount> getAccounts(){
        return accountHandler.getAccounts();
    }

    //Summary of account balances
    public String getFullSummary(){
        return accountHandler.getFullSummary();
    }

    //Net total of all accounts
    public double getNetTotal() {
        return accountHandler.getNetTotal();
    }

    public GenericAccount getAccountByName(String name) {
        return accountHandler.getAccountByName(name);
    }*/
}
