
// needs from account
/*
* has method
* double for amount
* last transaction variable
* revert transaction method
* getSummary()
* mostRecentTransaction()
* getDateOfCreation()
* ALSO - need getDeposits() and storeOutgoingMoney() in the text class
* */


// TODO replace Object with Account after it is created
public class Customer extends ATM_User {
	private ArrayList<AccountInterface> accounts;
	private TextFileManager textFileManager;

	public Customer (String username, String password, TextFileManager textFileManager){
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

	//TODO
	public String getDateOfCreation(AccountInterface acc){
		return acc.getDateOfCreation();
	}

	public double getNetTotal(){
		double totalDebt = 0;
		double totalAsset = 0;

		for (AccountInterface acc : accounts) {
			if (acc.getOwes()) {
				// TODO
			}
		}
	}


	public String getLogin(){
		return login;
	}

	public double viewAccountBalance(AccountInterface acc){
		return acc.getBalance();
	}
	public boolean transferBetweenAccounts(AccountInterface from, Object to, double amount){
		// Returns true if transfer went through, false otherwise.
		if (from.has(amount)) {
			to.transfer_in(amount);
			return true;
		}
		return false;
	}

	// Same code for transfering between individual accounts and between two different people
	public void transferToOther(AccountInterface myAcc, AccountInterface otherAcc, double amount){
		return transferBetweenAccounts(myAcc, otherAcc, amount);
	}

	public boolean withdrawFromAccount(AccountInterface acc, double amount){
		// Returns true if withdraw was succesful, false otherwise/
		if (acc.has(amount)){
			acc.transfer_out(amount);
			return true
		}
		return false;
	}

	public void payBill(AccountInterface acc, double amount){
		if (acc.has(amount)){
			acc.transfer_out(amount);
			// TODO add in the save to text file functionality
			textFilemanager.storeOutgoingMoney(amount);
			return true;
		}
		return false;
	}

	public void depositMoney(AccountInterface acc, double money){
		// TODO add in the text file parser to bool
		ArrayList<double> moneyIn = textFileManager.getDeposits('deposits.txt');
		for (double cheque : moneyIn){
			acc.transfer_in(cheque);
		}
	}

	public void requestAccountCreation(){
		// TODO write to a file
		textFileManager.
	}

	public void undoMostRecentTransaction(AccountInterface acc){
		acc.revertTransaction();
	}
	//

}