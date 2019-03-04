
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Customer extends ATM_User {
	private ArrayList<AccountInterface> accounts;
	private ArrayList<FileManager> textFileManagers;

	// Initialize new customer
	public Customer(String username, String password){
		super(username, password);
		accounts = new ArrayList<>();
		textFileManagers = new ArrayList<>();
	}

	// Add an account and corresponding FileManager
	public void addAccount(AccountInterface account, String accountType) {
		accounts.add(account);
		textFileManagers.add(new FileManager(getUsername(), accountType));
	}

	public boolean checkIfAccountExists(AccountInterface account) {
		return (accounts.indexOf(account) != -1);
	}

	public String getFullSummary(){
		String summary = "";
		for (AccountInterface acc : accounts){
			summary += acc.getSummary();
			summary += "\n";
		}
		return summary;
	}

	public double[] getMostRecentTransaction(AccountInterface acc){
		return acc.getLatestTrans();
	}

	public Calendar getDateOfCreation(AccountInterface acc){
		return acc.getCreation_date();
	}

	//
	public double getNetTotal(){
		double netTotal = 0;
		for (AccountInterface acc : accounts) {
			if (acc instanceof CreditCardAcc || acc instanceof CreditLineAcc){
				netTotal -= acc.getBalance();
			}
			else if (acc instanceof ChequingAcc || acc instanceof  SavingAcc){
				netTotal += acc.getBalance();
			}
		}
		return netTotal;
	}


	public double viewAccountBalance(AccountInterface acc){
		return acc.getBalance();
	}

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

	public void requestAccountCreation(String accountType){
		BankManager.requestAccount(this.getUsername(), accountType);
	}

	public void undoMostRecentTransaction(AccountInterface acc){
		acc.revertTransaction();
	}


	// Helper Method
	public boolean has(AccountInterface acc, double amount) {
		return acc.getBalance() - amount >= 0;
	}
}
