import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class EmployeeMenuGUI {

	JFrame employeeFrm;

	/**
	 * Launch the application.
	 */
	public static void main(ATM_User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMenuGUI window = new EmployeeMenuGUI(user);
					window.employeeFrm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeMenuGUI(ATM_User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ATM_User user) {
		Employee employee = (Employee) user;
		employeeFrm = new JFrame();
		employeeFrm.setBounds(100, 100, 450, 384);
		employeeFrm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		employeeFrm.getContentPane().setLayout(null);
		
		JLabel lblAccounts = new JLabel("Accounts:");
		lblAccounts.setBounds(33, 16, 95, 16);
		employeeFrm.getContentPane().add(lblAccounts);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(278, 49, 166, 152);
		scrollPane.setVisible(false);
		employeeFrm.getContentPane().add(scrollPane);
		
		DefaultListModel<String> model = new DefaultListModel<>();
		JList<String> accountList = new JList<>( model );
		//https://stackoverflow.com/questions/16214480/adding-elements-to-a-jlist
		scrollPane.setViewportView(accountList);
		accountList.setVisible(false);
		accountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnConfirm = new JButton("View selected account");
		btnConfirm.setVisible(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnConfirm.getText() == "View selected account") {
					String listOption = accountList.getSelectedValue();
					String accountName = "";
					for(int i = 0; i < listOption.length(); i++) {
						if (listOption.charAt(i) != ',')
							accountName += listOption.charAt(i);
						else
							break;
					}
					GenericAccount accountToDisplay = employee.getAccountByName(accountName);
					if (accountToDisplay.type.equalsIgnoreCase("CREDIT CARD")) {
						employeeFrm.setVisible(false);
						CreditCardMenuGUI window = new CreditCardMenuGUI(accountToDisplay, user);
		                window.creditCardfrm.setVisible(true);
					}
					else if (accountToDisplay.type.equalsIgnoreCase("SAVINGS")) {
						employeeFrm.setVisible(false);
						SavingsMenuGUI window = new SavingsMenuGUI(accountToDisplay, user);
		                window.savingsfrm.setVisible(true);
					}
					else if (accountToDisplay.type.equalsIgnoreCase("CREDITLINE")) {
						employeeFrm.setVisible(false);
						CreditLineMenuGUI window = new CreditLineMenuGUI(accountToDisplay, user);
		                window.creditlinefrm.setVisible(true);
					}
					else if (accountToDisplay.type.equalsIgnoreCase("STOCK")) {
						employeeFrm.setVisible(false);
						StockAccountMenuGUI window = new StockAccountMenuGUI(accountToDisplay, user);
						window.stockfrm.setVisible(true);
					}
					else {
						employeeFrm.setVisible(false);
						ChequingMenuGUI window = new ChequingMenuGUI(accountToDisplay, user);
		                window.chequingfrm.setVisible(true);
					}
				}
				else {
					String listOption = accountList.getSelectedValue();
					JFrame accountNameFrame = new JFrame();
				    String name = JOptionPane.showInputDialog(accountNameFrame, "What would you like to name your " + listOption + " account? (alphanumeric no spaces)");
				    if(name != null && BankManagerMenuGUI.isAlphaNumeric(name)) {
						employee.requestAccount(listOption, name);
						BankManagerMenuGUI.showSuccess();
				    }
				    else {
				    	BankManagerMenuGUI.showInputError();
				    }
					
				}
			}
		});
		btnConfirm.setBounds(278, 202, 166, 29);
		employeeFrm.getContentPane().add(btnConfirm);
		
		JButton btnSummary = new JButton("Show summary of all accounts");
		btnSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String summary = employee.getFullSummary();
					JFrame notice = new JFrame();
					String infoMessage = summary;
					JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
            	}
				catch (Exception j) {
            			j.printStackTrace();
				}
			}
		});
		btnSummary.setBounds(43, 44, 223, 29);
		employeeFrm.getContentPane().add(btnSummary);
		
		JButton btnRequestAccountCreation = new JButton("Request account creation");
		btnRequestAccountCreation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConfirm.setText("Request this type");
				btnConfirm.setVisible(true);
				accountList.setVisible(true);
				scrollPane.setVisible(true);
				String[] validAccounts = new String[AccountHandler.accountList.length];
				for(int i=0;i<AccountHandler.accountList.length;i++) {
					validAccounts[i] = (String)AccountHandler.accountList[i][0];
				}
				model.clear();
				for(String va : validAccounts) {
					model.addElement(va);
				}


				//if (accountType.equals("chequing")) {
				//    System.out.println("Would you like to make this your primary account (yes or no)");
				//    String ans = nextLine();
				//    if (ans.equals("yes")) {
				//        accountType += "(primary)";
				//    }
				//}
			}
		});
		btnRequestAccountCreation.setBounds(42, 85, 224, 29);
		employeeFrm.getContentPane().add(btnRequestAccountCreation);
		
		JButton btnNetTotal = new JButton("Get net total");
		btnNetTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double total = employee.getNetTotal();
				JFrame notice = new JFrame();
				String infoMessage = "Your net total is : $" +total;
				JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNetTotal.setBounds(43, 126, 223, 29);
		employeeFrm.getContentPane().add(btnNetTotal);
		
		JButton btnReturnToMain = new JButton("Return to main menu");
		btnReturnToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeFrm.setVisible(false);
				MainMenuGUI window = new MainMenuGUI();
                window.mainFrm.setVisible(true);
			}
		});
		btnReturnToMain.setBounds(283, 235, 167, 29);
		employeeFrm.getContentPane().add(btnReturnToMain);
		
		JButton btnViewSpecfic = new JButton("View specific account");
		btnViewSpecfic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConfirm.setText("View selected account");
				accountList.setVisible(true);
				scrollPane.setVisible(true);
				model.clear();
				btnConfirm.setVisible(true);
				ArrayList<GenericAccount> accounts = employee.getAccounts();
				for(int i = 0; i < accounts.size(); i++) {
					System.out.println(accounts.get(i).name);
					model.addElement(accounts.get(i).name+", " + accounts.get(i).type);
				}
			}
		});
		btnViewSpecfic.setBounds(43, 161, 223, 29);
		employeeFrm.getContentPane().add(btnViewSpecfic);
		
		JButton btnRestockBills = new JButton("Restock bills");
		btnRestockBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			    if(strFive != "" && strTen != "" && strTwenty != "" && strFifty != "") {
					if (BankManagerMenuGUI.isNumeric(strFive) && BankManagerMenuGUI.isNumeric(strTen) && BankManagerMenuGUI.isNumeric(strTwenty) && BankManagerMenuGUI.isNumeric(strFifty)){
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
			    int result = -1;

				employee.addBills(numFive, numTen, numTwenty, numFifty);
				BankManagerMenuGUI.showSuccess();

			}
		});
		btnRestockBills.setBounds(43, 202, 223, 29);
		employeeFrm.getContentPane().add(btnRestockBills);
		
		JButton btnCreateCustomer = new JButton("Create new customer");
		btnCreateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame usernameFrame = new JFrame();
			    String strUser = JOptionPane.showInputDialog(usernameFrame, "Input username:");
			    JFrame passwordFrame = new JFrame();
			    String strPass = JOptionPane.showInputDialog(passwordFrame, "Input password:");
			    if(strUser != "" && BankManagerMenuGUI.isAlphaNumeric(strUser) && strPass != "" && BankManagerMenuGUI.isAlphaNumeric(strPass)) {
			        //type refers to the creation of a customer instead of another employee
			    	employee.createNewUser(strUser, strPass, 1);
		        	BankManagerMenuGUI.showSuccess();
			    }
			    else {
			    	BankManagerMenuGUI.showInputError();
			    	return;
			    }
			}
		});
		btnCreateCustomer.setBounds(43, 243, 223, 29);
		employeeFrm.getContentPane().add(btnCreateCustomer);
		
		JButton btnJointAccount = new JButton("Request joint account");
		btnJointAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame userFrame = new JFrame();
				String strUser = JOptionPane.showInputDialog(userFrame, "Input target user:");
				JFrame accountFrame = new JFrame();
				String strAccount = JOptionPane.showInputDialog(accountFrame, "Input account name:");
				if(strUser != "" && strAccount != "") {
					ATM_User otherUser = new UserManager().getUser(strUser);
					if(otherUser instanceof IAccountHolder) {
						GenericAccount acc = ((IAccountHolder)otherUser).getAccountByName(strAccount);
						if(acc!=null) {
							((IAccountHolder)user).addAccount(acc);
						} else {
							System.out.println("Bad Account Name");
						}
					} else {
						System.out.println("Bad User Name");
					}
				} else{
					System.out.println("null inputs");
				}
			}
		});
		btnJointAccount.setBounds(43, 283, 223, 29);
		employeeFrm.getContentPane().add(btnJointAccount);
		
		JButton btnChangePassword = new JButton("Change password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame passFrame = new JFrame();
				String newPassword = JOptionPane.showInputDialog(passFrame, "Input new password");
				if(newPassword != "") {
					user.setPassword(newPassword);
				}
				else {
					JFrame notice = new JFrame();
					String infoMessage = "Invalid password. Try again";
					JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnChangePassword.setBounds(43, 324, 223, 29);
		employeeFrm.getContentPane().add(btnChangePassword);
		
		
		
		
	}
}
