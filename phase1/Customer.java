<<<<<<< HEAD
//
//// needs from account
///*
//* has method
//* double for amount
//* last transaction variable
//* revert transaction method
//* getSummary()
//* mostRecentTransaction()
//* getDateOfCreation()
//* ALSO - need getDeposits() and storeOutgoingMoney() in the text class
//* */
//
//
//// TODO replace Object with Account after it is created
//public class Customer {
//	private ArrayList<Object> accounts;
//	private String login;
//	private String password;
//
//	public String getFullSummary(){
//		String summary = "";
//		for (Account acc : accounts){
//			summary += acc.getSummary();
//		}
//		return summary;
//	}
//
//	public String getMostRecentTransaction(Object acc){
//		return acc.mostRecentTransaction();
//	}
//
//	public String getDateOfCreation(Object acc){
//		return acc.getDateOfCreation();
//	}
//	public double getNetTotal(){
//		// TODO
//	}
//
//
//	public String getLogin(){
//		return login;
//	}
//
//	public double viewAccountBalance(Object acc){
//		return acc.getBalance();
//	}
//	public boolean transferBetweenAccounts(Object from, Object to, double amount){
//		// Returns true if transfer went through, false otherwise.
//		if (from.has(amount)) {
//			to.transfer_in(amount);
//			return true;
//		}
//		return false;
//	}
//
//	// Same code for transfering between individual accounts and between two different people
//	public void transferToOther(Object myAcc, Object otherAcc, double amount){
//		return transferBetweenAccounts(myAcc, otherAcc, amount);
//	}
//
//	public boolean withdrawFromAccount(Object acc, double amount){
//		// Returns true if withdraw was succesful, false otherwise/
//		if (acc.has(amount)){
//			acc.transfer_out(amount);
//			return true
//		}
//		return false;
//	}
//
//	public void payBill(Object acc, double amount){
//		if (acc.has(amount)){
//			acc.transfer_out(amount);
//			// TODO add in the save to text file functionality
//			storeOutgoingMoney(amount);
//			return true;
//		}
//		return false;
//	}
//
//	public void depositMoney(Object acc, double money){
//		// TODO add in the text file parser to bool
//		ArrayList<double> moneyIn = getDeposits('deposits.txt');
//		for (double cheque : moneyIn){
//			acc.transfer_in(cheque);
//		}
//	}
//
//	public void requestAccountCreation(){
//		// TODO use Observer strategy with bank manager
//	}
//
//	// the following methods are avaliable only for BankManager
//	public void setLogin(String login)(){
//		this.login = login;
//	}
//	public void setPassword(String password){
//		this.password = password;
//	}
//	public void undoMostRecentTransaction(Object acc){}
//	//
//
//}
=======

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

import java.util.ArrayList;

// TODO replace Object with Account after it is created
public class Customer extends ATM_User {
	private ArrayList<AccountInterface> accounts;
	private TextFileManager textFileManager;

	public Customer(String username, String password, TextFileManager textFileManager){
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
>>>>>>> b5480c7c00f65161a324aabe70721bd168be94ca
