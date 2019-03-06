
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Customer extends ATM_User {
	private ArrayList<GenericAccount> accounts;

	// Initialize new customer
	public Customer(String username, String password){
		super(username, password);
		accounts = new ArrayList<GenericAccount>();
		addAction(1, ()->getFullSummary(), "Get account summary");
		addAction(2, ()->requestAccount(), "Request account creation");
		for(int i=0;i<accounts.size();i++) {
			final int f = i; //Because the input needs to be final.
			addAction(i+2, ()->viewAccount(f), "Account: "+accounts.get(i).name);
		}
	}

	public void requestAccount(){
		Scanner s = new Scanner(System.in);
		System.out.println("What kind of account?"); //TODO: list their options
		String accountType = s.nextLine();
		BankManager.requestAccount(this.getUsername(), accountType);
	}

	public void addAccount(String account) {
		if(account.equals("Chequing")) {
			accounts.add(new ChequingAcc());
		} else if(account.equals("CreditCard")) {
			accounts.add(new CreditCardAcc());
		} else if(account.equals("CreditLine")) {
			accounts.add(new CreditLineAcc());
		} else if(account.equals("Saving")) {
			accounts.add(new SavingAcc());
		} else {
			System.out.println("ERROR: INVALID ACCOUNT TYPE");
		}
	}

	public void viewAccount(int i) {
		accounts.get(i).showMenu();
	}

	public void getFullSummary(){
		String summary = "";
		for (GenericAccount acc : accounts){
			summary += acc.getSummary();
			summary += "\n";
		}
		System.out.println(summary);
	}

	//
	public double getNetTotal(){
		double totalDebt = 0;
		double totalAsset = 0;

		for (GenericAccount acc : accounts) {
			if (!acc.isAsset()) {
				// TODO
				totalDebt += acc.getBalance();
			} else {
				totalAsset += acc.getBalance();
			}
		}
		return totalAsset - totalDebt;
	}


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
}
