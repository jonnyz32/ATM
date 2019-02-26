
// TODO replace Object with Account after it is created
public class Customer {
	private ArrayList<Object> accounts;
	private String login;
	private String password;

	public String getFullSummary(){}
	public String getMostRecentTransaction(Object acc){}
	public String getDateOfCreation(Object acc){}
	public double getNetTotal(){}

	public String getLogin(){}

	public double viewAccountBalance(Object acc){}
	public void transferBetweenAccounts(Object from, Object to){}
	public void withdrawFromAccount(Object acc, double amount){}
	public void transferToOther(Object myAcc, Object otherAcc){}
	public void payBill(Object acc, double amount){}
	public void depositMoney(Object acc, double money){}
	public void requestAccountCreation(){}

	// the following methods are avaliable only for BankManager
	public void setLogin(String login)(){}
	public void setPassword(String password){}
	public void undoMostRecentTransaction(Object acc){}
	//

}