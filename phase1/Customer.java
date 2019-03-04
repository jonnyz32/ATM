
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Customer extends ATM_User {
	private ArrayList<GenericAccount> accounts;

	// Initialize new customer
	public Customer(String username, String password){
		super(username, password);
		accounts = new ArrayList<GenericAccount>();
		addAction(1, ()->getFullSummary(), "Get account summary");
		addAction(2, ()->requestAccount(), "Request account creation");
		//TODO: Access each account they have
	}

	public void requestAccount(){
		Scanner s = new Scanner(System.in);
		System.out.println("What kind of account?"); //TODO: list their options
		String accountType = s.nextLine();
		BankManager.requestAccount(this.getUsername(), accountType);
	}

	public void addAccount(GenericAccount account) {
		accounts.add(account);
	}

	public void viewAccount(GenericAccount account) {
		account.showMenu();
	}

	public void getFullSummary(){
		String summary = "";
		for (AccountInterface acc : accounts){
			summary += acc.getSummary();
			summary += "\n";
		}
		System.out.println(summary);
	}

	//
	public double getNetTotal(){
		double totalDebt = 0;
		double totalAsset = 0;

		for (AccountInterface acc : accounts) {
			if (acc.getOwes()) {
				// TODO
				totalDebt += acc.getBalance();
			} else {
				totalAsset += acc.getBalance();
			}
		}
		return totalAsset - totalDebt;
	}


	//TODO: Migrate down to Account level?
	public boolean transferBetweenAccounts(AccountInterface from, AccountInterface to, double amount){
		// Returns true if transfer went through, false otherwise.
		int index = accounts.indexOf(from);
		if (index == -1){ return false; }

		if (has(from, amount)) {
			to.transfer_in(amount);
			return true;
		}
		return false;
	}

	// Same code for transfering between individual accounts and between two different people
	public boolean transferToOther(AccountInterface myAcc, AccountInterface otherAcc, double amount){
		return transferBetweenAccounts(myAcc, otherAcc, amount);
	}

	public boolean withdrawFromAccount(AccountInterface acc, double amount){
		// Returns true if withdraw was successful, false otherwise.
		if (has(acc, amount)){
			acc.transfer_out(amount);
			return true;
		}
		return false;
	}

	//Does this have to be local? It decreases balance...
	public boolean payBill(AccountInterface acc, double amount, Date date){
		int index = accounts.indexOf(acc);
		if (index == -1){ return false; }

		if (has(acc, amount)){
			acc.transfer_out(amount);
			textFileManagers.get(index).withdrawMoney(amount, date);
			return true;
		}
		return false;
	}

	public boolean depositMoney(AccountInterface acc, double money){
		int index = accounts.indexOf(acc);
		if (index == -1){ return false; }
		else {
			acc.transfer_in(money);
			return true;
		}
	}

	public boolean depositFromFile(AccountInterface acc, File depositFile){
		int index = accounts.indexOf(acc);
		if (index == -1){ return false; }

		ArrayList<Double> moneyIn = textFileManagers.get(index).readDeposits(depositFile);
		for (double cheque : moneyIn){
			acc.transfer_in(cheque);
		}
		return true;
	}
}
