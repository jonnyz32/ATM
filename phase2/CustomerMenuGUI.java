
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class CustomerMenuGUI {

	JFrame customerfrm;
	final Scanner in = new Scanner(System.in);

	/**
	 * Launch the application.
	 */
	public static void main(ATM_User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerMenuGUI window = new CustomerMenuGUI(user);
					window.customerfrm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerMenuGUI(ATM_User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ATM_User user) {
		Customer customer = (Customer) user;
		customerfrm = new JFrame();
		customerfrm.setBounds(100, 100, 450, 302);
		customerfrm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		customerfrm.getContentPane().setLayout(null);
		
		JLabel lblAccounts = new JLabel("Accounts:");
		lblAccounts.setBounds(33, 16, 95, 16);
		customerfrm.getContentPane().add(lblAccounts);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(278, 49, 166, 152);
		scrollPane.setVisible(false);
		customerfrm.getContentPane().add(scrollPane);
		
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
					GenericAccount accountToDisplay = customer.getAccountByName(accountName);
					if (accountToDisplay.type.equalsIgnoreCase("CREDIT CARD")) {
						customerfrm.setVisible(false);
						CreditCardMenuGUI window = new CreditCardMenuGUI(accountToDisplay, user);
		                window.creditCardfrm.setVisible(true);
					}
					else if (accountToDisplay.type.equalsIgnoreCase("SAVINGS")) {
						customerfrm.setVisible(false);
						SavingsMenuGUI window = new SavingsMenuGUI(accountToDisplay, user);
		                window.savingsfrm.setVisible(true);
					}
					else if (accountToDisplay.type.equalsIgnoreCase("CREDITLINE")) {
						customerfrm.setVisible(false);
						CreditLineMenuGUI window = new CreditLineMenuGUI(accountToDisplay, user);
		                window.creditlinefrm.setVisible(true);
					}
					else if (accountToDisplay.type.equalsIgnoreCase("STOCK")) {
						customerfrm.setVisible(false);
						StockAccountMenuGUI window = new StockAccountMenuGUI(accountToDisplay, user);
						window.stockfrm.setVisible(true);
					}
					else {
						customerfrm.setVisible(false);
						ChequingMenuGUI window = new ChequingMenuGUI(accountToDisplay, user);
		                window.chequingfrm.setVisible(true);
					}
				}
				else {
					String listOption = accountList.getSelectedValue();
					JFrame accountNameFrame = new JFrame();
				    String name = JOptionPane.showInputDialog(accountNameFrame, "What would you like to name your " + listOption + " account? (alphanumeric no spaces)");
				    if(!name.equals("") && BankManagerMenuGUI.isAlphaNumeric(name)) {
						customer.requestAccount(listOption, name);
						BankManagerMenuGUI.showSuccess();
				    }
				    else {
				    	BankManagerMenuGUI.showInputError();
				    }
					
				}
			}
		});
		btnConfirm.setBounds(278, 202, 166, 29);
		customerfrm.getContentPane().add(btnConfirm);
		
		JButton btnSummary = new JButton("Show summary of all accounts");
		btnSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String summary = customer.getFullSummary();
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
		customerfrm.getContentPane().add(btnSummary);
		
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
			}
		});
		btnRequestAccountCreation.setBounds(42, 85, 224, 29);
		customerfrm.getContentPane().add(btnRequestAccountCreation);
		
		JButton btnNetTotal = new JButton("Get net total");
		btnNetTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double total = customer.getNetTotal();
				JFrame notice = new JFrame();
				String infoMessage = "Your net total is : $" +total;
				JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNetTotal.setBounds(43, 126, 223, 29);
		customerfrm.getContentPane().add(btnNetTotal);
		
		JButton btnReturnToMain = new JButton("Return to main menu");
		btnReturnToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerfrm.setVisible(false);
				MainMenuGUI window = new MainMenuGUI();
                window.mainFrm.setVisible(true);
			}
		});
		btnReturnToMain.setBounds(283, 235, 167, 29);
		customerfrm.getContentPane().add(btnReturnToMain);
		
		JButton btnViewSpecfic = new JButton("View specific account");
		btnViewSpecfic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConfirm.setText("View selected account");
				accountList.setVisible(true);
				scrollPane.setVisible(true);
				model.clear();
				btnConfirm.setVisible(true);
				ArrayList<GenericAccount> accounts = customer.getAccounts();
				for(int i = 0; i < accounts.size(); i++) {
					System.out.println(accounts.get(i).name);
					model.addElement(accounts.get(i).name+", " + accounts.get(i).type);
				}
			}
		});
		btnViewSpecfic.setBounds(43, 167, 223, 29);
		customerfrm.getContentPane().add(btnViewSpecfic);
		
		JButton btnJointAccount = new JButton("Request joint account");
		btnJointAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame userFrame = new JFrame();
				String strUser = JOptionPane.showInputDialog(userFrame, "Input target user:");
				JFrame accountFrame = new JFrame();
				String strAccount = JOptionPane.showInputDialog(accountFrame, "Input account name:");
				if(!strUser.equals("") && !strAccount.equals("")) {
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
		btnJointAccount.setBounds(43, 208, 223, 29);
		customerfrm.getContentPane().add(btnJointAccount);
		
		JButton btnChangePassword = new JButton("Change password ");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame passFrame = new JFrame();
				String newPassword = JOptionPane.showInputDialog(passFrame, "Input new password");
				if(!newPassword.equals("")) {
					user.setPassword(newPassword);
				}
				else {
					JFrame notice = new JFrame();
					String infoMessage = "Invalid password. Try again";
					JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnChangePassword.setBounds(43, 249, 223, 29);
		customerfrm.getContentPane().add(btnChangePassword);
		
		
	}
}
