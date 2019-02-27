
// TODO replace Object with Account after it is created
public class Customer {
	private ArrayList<Object> accounts;
	private String login;
	private String password;

	public String getFullSummary(){}
	public String getMostRecentTransaction(Object acc){}
	public String getDateOfCreation(Object acc){}
	public double getNetTotal(){}


	public String getLogin(){
		return login;
	}

	public double viewAccountBalance(Object acc){
		return acc.getBalance();
	}
	public boolean transferBetweenAccounts(Object from, Object to, double amount){
		// Returns true if transfer went through, false otherwise.
		if (from.has(amount)) {
			to.transfer_in(amount);
			return true;
		}
		return false;
	}

	// Same code for transfering between individual accounts and between two different people
	public void transferToOther(Object myAcc, Object otherAcc, double amount){
		return transferBetweenAccounts(myAcc, otherAcc, amount);
	}

	public boolean withdrawFromAccount(Object acc, double amount){
		// Returns true if withdraw was succesful, false otherwise/
		if (acc.has(amount)){
			acc.transfer_out(amount);
			return true
		}
		return false;
	}

	public void payBill(Object acc, double amount){
		if (acc.has(amount)){
			acc.transfer_out(amount);
			// TODO add in the save to text file functionality
			storeOutgoingMoney(amount);
			return true;
		}
		return false;
	}

	public void depositMoney(Object acc, double money){
		// TODO add in the text file parser to bool
		ArrayList<double> moneyIn = getDeposits('deposits.txt');
		for (double cheque : moneyIn){
			acc.transfer_in(cheque);
		}
	}
	public void requestAccountCreation(){}

	// the following methods are avaliable only for BankManager
	public void setLogin(String login)(){
		this.login = login;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void undoMostRecentTransaction(Object acc){}
	//

}