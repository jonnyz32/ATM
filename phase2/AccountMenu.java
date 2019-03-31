import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountMenu{

    private GenericAccount account;

    public AccountMenu(GenericAccount account){
        this.account = account;
    }

    void getLastTransaction(){
        String latest = account.getLatestTransaction();

        JFrame notice = new JFrame();
        String infoMessage = "Last Transaction: "+latest;
        JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
    }

    void showBalance() {
        String accountType = "";
        if(account.isAsset()) {
            accountType = "debit";
        } else {
            accountType = "credit";
        }
        JFrame notice = new JFrame();
        String infoMessage = "This account has a "+ accountType +" balance of: "+"$"+account.getBalance();
        JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
    }

    void depositFromFile(){
        account.depositFromFile();
    }

    void depositCash() {
    	Object[] options1 = {"Canadian dollars", "Foreign currency", "Cancel"};
		int result = JOptionPane.showOptionDialog(null, "What type of money are you depositing?",
				null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options1, null);
		if (result == 0) {
	        JFrame fiveFrame = new JFrame();
	        String strFive = JOptionPane.showInputDialog(fiveFrame, "How many fives?");
	        JFrame tenFrame = new JFrame();
	        String strTen = JOptionPane.showInputDialog(tenFrame, "How many tens?");
	        JFrame twentyFrame = new JFrame();
	        String strTwenty = JOptionPane.showInputDialog(twentyFrame, "How many twenties?");
	        JFrame fiftyFrame = new JFrame();
	        String strFifty = JOptionPane.showInputDialog(fiftyFrame, "How many fifties?");
	
	        int numFive = -1;
	        int numTen = -1;
	        int numTwenty = -1;
	        int numFifty = -1;
	        if(strFive != null && strTen != null && strTwenty != null && strFifty != null) {
	            if (BankManagerMenuGUI.isNumeric(strFive) && BankManagerMenuGUI.isNumeric(strTen) &&
	                    BankManagerMenuGUI.isNumeric(strTwenty) && BankManagerMenuGUI.isNumeric(strFifty)){
	                numFive = Integer.parseInt(strFive);
	                numTen = Integer.parseInt(strTen);
	                numTwenty = Integer.parseInt(strTwenty);
	                numFifty = Integer.parseInt(strFifty);
	            }
	            else{
	                BankManagerMenuGUI.showInputError();
	                return;
	            }
	        }
	        else{
	            BankManagerMenuGUI.showInputError();
	            return;
	        }
	        if(strFive != null && strTen != null && strTwenty != null && strFifty != null) {
	            account.depositCash(numFive, numTen, numTwenty, numFifty);
	            BankManagerMenuGUI.showSuccess();
	        }
		}
		else if (result == 1) {
			Object[] options2 = {"EURO", "YUAN","RUBLE","BRITISH POUND", "MEXICAN PESO", "USD", "Cancel"};
			int type = JOptionPane.showOptionDialog(null, "What type of money are you depositing?",
					null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, options2, null);
			if(type != options2.length - 1) {
				String strType = (String) options2[type];
				JFrame amountFrame = new JFrame();
		        String strAmount = JOptionPane.showInputDialog(amountFrame, "How much are you depositing?");
		        double doubleAmount = 0;
		        if(strAmount != null){
		            doubleAmount = Double.parseDouble(strAmount);
		            account.depositForeignCurrency(strType, doubleAmount);
		            BankManagerMenuGUI.showSuccess();
		        }
		        else{
		            BankManagerMenuGUI.showInputError();
		            return;
		        }
		        
			}
			else {
				return;
			}
		}
		else{
			return;
		}

    }

    void transferToSelf(){
        JFrame amountFrame = new JFrame();
        String strAmount = JOptionPane.showInputDialog(amountFrame, "Amount to transfer?");
        double doubleAmount = 0;
        if(strAmount != null){
            doubleAmount = Double.parseDouble(strAmount);
        }
        else{
            BankManagerMenuGUI.showInputError();
            return;
        }
        JFrame destinationFrame = new JFrame();
        String other_acc_name = JOptionPane.showInputDialog(destinationFrame, "Account to transfer to?");
        //TODO add options
        if(doubleAmount != 0 && other_acc_name != null) {
            GenericAccount other_acc = account.owner.getAccountByName(other_acc_name);
            TransferManager tm = new TransferManager(account, doubleAmount, other_acc);
            tm.make_transfer();
            BankManagerMenuGUI.showSuccess();
        }
        else{
            BankManagerMenuGUI.showInputError();
            return;
        }

    }

    private IAccountHolder transferToOther_helper() {
        // Get the user and the account
        JFrame destinationFrame = new JFrame();
        String other_username = JOptionPane.showInputDialog(destinationFrame, "Who would you like to transfer to");
        ATM_User other_user = ATM_machine.getUser(other_username);
        if (other_user == null) {
            BankManagerMenuGUI.showInputError();
        }
        if (other_user instanceof IAccountHolder) {
            return (IAccountHolder) other_user;
        }
        return null;
    }

    void transferToOther(){
        IAccountHolder other_user = transferToOther_helper();
        JFrame destinationFrame = new JFrame();
        String other_acc_name = JOptionPane.showInputDialog(destinationFrame, "Which account of " + other_user.getUsername() + " would you like to transfer to?");

        GenericAccount other_acc = other_user.getAccountByName(other_acc_name);
        if (other_acc == null) {
            BankManagerMenuGUI.showInputError();
            return;
        }
        JFrame amountFrame = new JFrame();
        String strAmount = JOptionPane.showInputDialog(amountFrame, "Amount to transfer?");
        double doubleAmount = 0;
        if(strAmount != null){
            doubleAmount = Double.parseDouble(strAmount);
            TransferManager tm = new TransferManager(account, doubleAmount, other_acc);
            tm.make_transfer();
        }
        else{
            BankManagerMenuGUI.showInputError();
            return;
        }
    }

    void transferToExternal(){
        JFrame destinationFrame = new JFrame();
        String name = JOptionPane.showInputDialog(destinationFrame, "What bill would you like to pay?");

        JFrame amountFrame = new JFrame();
        String strAmount = JOptionPane.showInputDialog(amountFrame, "Amount to transfer?");
        double doubleAmount = 0;
        if(strAmount != null && name != null) {
            doubleAmount = Double.parseDouble(strAmount);
            account.transferToExternal(name, doubleAmount);
        }
        else{
            BankManagerMenuGUI.showInputError();
            return;
        }
    }

    void withdraw(){
    	Object[] options1 = {"Canadian dollars", "Foreign currency", "Cancel"};
		int result = JOptionPane.showOptionDialog(null, "What type of money are you depositing?",
				null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options1, null);
		if (result == 0) {
	        JFrame amountFrame = new JFrame();
	        String strAmount = JOptionPane.showInputDialog(amountFrame, "How much would you like to withdraw? (Should be a multiple of 5)");
	        int amount = 0;
	        if (strAmount != null){
	            amount = Integer.parseInt(strAmount);
	            if(amount %5 == 0 && amount <= account.getBalance()){
	                account.withdraw(amount);
	            }
	            else{
	                JFrame notice = new JFrame();
	                String infoMessage = "You either don't have enough money to withdraw that much, or you didn't input a multiple of 5. Try again. Balance: "+ account.getBalance();
	                JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
	                return;
	            }
	        }
	        else{
	            BankManagerMenuGUI.showInputError();
	        }
		}
		else if (result == 1) {
			Object[] options2 = {"EURO", "YUAN","RUBLE","BRITISH POUND", "MEXICAN PESO", "USD", "Cancel"};
			int type = JOptionPane.showOptionDialog(null, "What type of money are you withdrawing?",
					null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, options2, null);
			if(type != options2.length - 1) {
				String strType = (String) options2[type];
				JFrame amountFrame = new JFrame();
		        String strAmount = JOptionPane.showInputDialog(amountFrame, "How much are you withdrawing?");
		        double doubleAmount = 0;
		        if(strAmount != null){
		            doubleAmount = Double.parseDouble(strAmount);
		            account.withdrawForeignCurrency(strType, doubleAmount);
		            BankManagerMenuGUI.showSuccess();
		        }
		        else{
		            BankManagerMenuGUI.showInputError();
		            return;
		        }
		        
			}
			else {
				return;
			}
		}
		else {
			return;
		}
    }
}
