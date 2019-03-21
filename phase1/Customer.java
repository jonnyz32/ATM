import java.io.Serializable;
import java.util.*;

public class Customer extends ATM_User implements Serializable, IAccountHolder {
	private AccountHandler accountHandler;
	// Initialize new customer
	Customer(String username, String password){
		super(username, password);
		accountHandler = new AccountHandler(this);
	}

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

    /**
     * Gets an account given the name.
	 * Assume account exists.
     */
	public GenericAccount getAccountByName(String name) {
		return accountHandler.getAccountByName(name, this);
	}
}
