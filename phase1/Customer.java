


import java.util.ArrayList;
import java.util.Date;

public class Customer extends ATM_User {
	private ArrayList<AccountInterface> accounts;
	private FileManager textFileManager;

	public Customer(String username, String password, FileManager textFileManager){
		super(username, password);
		this.textFileManager = textFileManager;
	}

	public String getFullSummary(){
		String summary = "";
		for (AccountInterface acc : accounts){
			summary += acc.getSummary();
		}
		return summary;
	}

	public double[] getMostRecentTransaction(AccountInterface acc){
		return acc.getLatestTrans();
	}

	public Date getDateOfCreation(AccountInterface acc){
		return acc.getCreation_date();
	}

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


	public double viewAccountBalance(AccountInterface acc){
		return acc.getBalance();
	}
	public boolean transferBetweenAccounts(AccountInterface from, AccountInterface to, double amount){
		// Returns true if transfer went through, false otherwise.
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
		// Returns true if withdraw was succesful, false otherwise/
		if (has(acc, amount)){
			acc.transfer_out(amount);
			return true;
		}
		return false;
	}

	public boolean payBill(AccountInterface acc, double amount){
		if (has(acc, amount)){
			acc.transfer_out(amount);
			// TODO add in the save to text file functionality
			textFilemanager.storeOutgoingMoney(amount);
			return true;
		}
		return false;
	}

	public void depositMoney(AccountInterface acc, double money){
		// TODO add in the text file parser to bool
		ArrayList<Double> moneyIn = textFileManager.getDeposits('deposits.txt');
		for (double cheque : moneyIn){
			acc.transfer_in(cheque);
		}
	}

	public void requestAccountCreation(){
		// TODO write to a file


	}

	public void undoMostRecentTransaction(AccountInterface acc){
		acc.revertTransaction();
	}
	//


	// Helper Method
	public boolean has(AccountInterface acc, double amount) {
		return acc.getBalance() - amount >= 0;
	}
}
