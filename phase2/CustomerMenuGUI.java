
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

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
		customerfrm.setBounds(100, 100, 450, 292);
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
				else {
					customerfrm.setVisible(false);
					ChequingMenuGUI window = new ChequingMenuGUI(accountToDisplay, user);
	                window.chequingfrm.setVisible(true);
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
			        System.out.println(summary);
                    
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
				System.out.println("Options: chequing, credit, creditline, savings\n" +
		                "Which type of account?");
		        String accountType = "InvalidAccount";
		        boolean loop = true;
		        String[] validAccounts = {"chequing","credit","creditline","savings"};
		        while(loop) {
		            accountType = in.nextLine().toLowerCase();
		            for(String va : validAccounts) {
		                if(va.equals(accountType)) {
		                    loop=false;
		                }
		            }
		        }
		        if (accountType.equals("chequing")) {
		            System.out.println("Would you like to make this your primary account (yes or no)");
		            String ans = in.nextLine();
		            if (ans.equals("yes")) {
		                accountType += "(primary)";
		            }
		        }
		        System.out.println("What would you like to name your " + accountType + " account? (alphanumeric no spaces)");
		        String accountName = in.nextLine();
		        customer.requestAccount(accountType, accountName);
		        System.out.println("Request sent!");
			}
		});
		btnRequestAccountCreation.setBounds(42, 85, 224, 29);
		customerfrm.getContentPane().add(btnRequestAccountCreation);
		
		JButton btnNetTotal = new JButton("Get net total");
		btnNetTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double total = customer.getNetTotal();
		        System.out.println("Your net total is :");
		        System.out.println("$"+total);
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
				accountList.setVisible(true);
				scrollPane.setVisible(true);
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
		
		
	}
}
