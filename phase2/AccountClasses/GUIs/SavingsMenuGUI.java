package AccountClasses.GUIs;

import AccountClasses.Accounts.GenericAccount;
import UserClasses.Users.ATM_User;
import UserClasses.Users.Customer;
import UserClasses.GUIs.CustomerMenuGUI;
import UserClasses.GUIs.EmployeeMenuGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SavingsMenuGUI {

	public JFrame savingsfrm;
	AccountMenu menu;

	/**
	 * Launch the application.
	 */
	public static void main(GenericAccount accountToDisplay, ATM_User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SavingsMenuGUI window = new SavingsMenuGUI(accountToDisplay, user);
					window.savingsfrm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SavingsMenuGUI(GenericAccount accountToDisplay, ATM_User user) {
		initialize(accountToDisplay, user);
		menu = new AccountMenu(accountToDisplay);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(GenericAccount accountToDisplay, ATM_User user) {
		savingsfrm = new JFrame();
		savingsfrm.setBounds(100, 100, 450, 300);
		savingsfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		savingsfrm.getContentPane().setLayout(null);
		
		JButton btnViewBal = new JButton("View details");
		btnViewBal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.showBalance();
			}
		});
		btnViewBal.setBounds(16, 45, 210, 29);
		savingsfrm.getContentPane().add(btnViewBal);
		
		JLabel lblSavingsMenu = new JLabel("Savings Menu");
		lblSavingsMenu.setBounds(16, 10, 86, 16);
		savingsfrm.getContentPane().add(lblSavingsMenu);
		
		JButton btnViewLastTransaction = new JButton("View last transaction");
		btnViewLastTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.getLastTransaction();
			}
		});
		btnViewLastTransaction.setBounds(16, 97, 210, 29);
		savingsfrm.getContentPane().add(btnViewLastTransaction);
		
		JButton btnDeposit = new JButton("Deposit from file");
		btnDeposit.setBounds(16, 151, 210, 29);
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.depositFromFile();
			}
		});
		savingsfrm.getContentPane().add(btnDeposit);
		
		JButton btnTransferAcc = new JButton("Transfer to another account");
		btnTransferAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.transferToSelf();
			}
		});
		btnTransferAcc.setBounds(16, 209, 210, 29);
		savingsfrm.getContentPane().add(btnTransferAcc);
		
		JButton btnNewButton_3 = new JButton("Transfer to another user");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.transferToOther();
			}
		});
		btnNewButton_3.setBounds(238, 45, 206, 29);
		savingsfrm.getContentPane().add(btnNewButton_3);
		
		JButton btnPayExternalBill = new JButton("Pay external bill");
		btnPayExternalBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.transferToExternal();
			}
		});
		btnPayExternalBill.setBounds(238, 97, 206, 29);
		savingsfrm.getContentPane().add(btnPayExternalBill);
		
		JButton btnWithdrawMoney = new JButton("Withdraw money");
		btnWithdrawMoney.setBounds(238, 151, 206, 29);
		btnWithdrawMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.withdraw();
			}
		});
		savingsfrm.getContentPane().add(btnWithdrawMoney);
		
		JButton btnReturnToAccounts = new JButton("Return to my accounts");
		btnReturnToAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user instanceof Customer) {
					savingsfrm.setVisible(false);
					CustomerMenuGUI window = new CustomerMenuGUI(user);
					window.customerfrm.setVisible(true);
				}
                else{
					savingsfrm.setVisible(false);
					EmployeeMenuGUI window = new EmployeeMenuGUI(user);
					window.employeeFrm.setVisible(true);
				}
			}
		});
		btnReturnToAccounts.setBounds(138, 243, 169, 29);
		savingsfrm.getContentPane().add(btnReturnToAccounts);
		
		JButton btnDepositCash = new JButton("Deposit cash");
		btnDepositCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.depositCash();
			}
		});
		btnDepositCash.setBounds(238, 209, 206, 29);
		savingsfrm.getContentPane().add(btnDepositCash);
	}
}
