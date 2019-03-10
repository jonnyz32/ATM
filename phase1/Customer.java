
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Customer extends ATM_User implements Serializable {
	private ArrayList<GenericAccount> accounts;

	// Initialize new customer
	Customer(String username, String password){
		super(username, password);
		accounts = new ArrayList<>();
	}

	void requestAccount(String type, String name){
		BankManager.requestAccount(this.getUsername(), type, name);
	}

	boolean addAccount(String type, String name) {
		if(type.equals("Chequing")) {
		    // Check if there are other chequing accounts.
		    for (GenericAccount a: accounts) {
		        if (a instanceof ChequingAcc) {
		            accounts.add(new ChequingAcc(name, this, false));
                }
			}
            accounts.add(new ChequingAcc(name, this, true));
		} else if(type.equals("credit")) {
			accounts.add(new CreditCardAcc(name, this));
		} else if(type.equals("creditline")) {
			accounts.add(new CreditLineAcc(name, this));
		} else if(type.equals("savings")) {
			accounts.add(new SavingAcc(name, this));
		} else {
			return false;
		}
		return true;
	}

	ArrayList<GenericAccount> getAccounts(){
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

	//Net total of all accounts
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
		GenericAccount account = new ChequingAcc("BAD", this, false);
		for (GenericAccount a: accounts) {
			if (a.name.equals(name)) {
				account =  a;
				break;
			}
		}
		return account;
	}

	/*
	//TODO: Migrate down to Account level?
	public boolean transferBetweenAccounts(GenericAccount from, GenericAccount to, double amount){
		// Returns true if transfer went through, false otherwise.
		int index = accounts.indexOf(from);
		if (index == -1){ return false; }

		if (from.getBalance()>=amount) {
			from.transferOut(amount);
			to.transferIn(amount);
			return true;
		}
		return false;
	}

	// Same code for transfering between individual accounts and between two different people
	public boolean transferToOther(GenericAccount myAcc, GenericAccount otherAcc, double amount){
		return transferBetweenAccounts(myAcc, otherAcc, amount);
	}

	public boolean withdrawFromAccount(GenericAccount acc, double amount){
		// Returns true if withdraw was successful, false otherwise.
		if (acc.getBalance()>=amount){
			//Pull cash
			acc.transferOut(amount);
			return true;
		}
		return false;
	}


	public boolean depositMoney(GenericAccount acc, double money){
		int index = accounts.indexOf(acc);
		if (index == -1){ return false; }
		else {
			acc.transferIn(money);
			return true;
		}
	}

	public boolean depositFromFile(GenericAccount acc, File depositFile){
		int index = accounts.indexOf(acc);
		if (index == -1){ return false; }

		ArrayList<Double> moneyIn = textFileManagers.get(index).readDeposits(depositFile);
		for (double cheque : moneyIn){
			acc.transfer_in(cheque);
		}
		return true;
	}
	*/
}
