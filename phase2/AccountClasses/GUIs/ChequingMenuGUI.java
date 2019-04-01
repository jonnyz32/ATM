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

public class ChequingMenuGUI {

	public JFrame chequingfrm;
	AccountMenu menu;

	/**
	 * Launch the application.
	 */
	public static void main(GenericAccount accountToDisplay, ATM_User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChequingMenuGUI window = new ChequingMenuGUI(accountToDisplay, user);
					window.chequingfrm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChequingMenuGUI(GenericAccount accountToDisplay, ATM_User user) {
		initialize(accountToDisplay, user);
		menu = new AccountMenu(accountToDisplay);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(GenericAccount accountToDisplay, ATM_User user) {
		chequingfrm = new JFrame();
		chequingfrm.setBounds(100, 100, 450, 300);
		chequingfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnViewBalance = new JButton("View details");
		btnViewBalance.setBounds(6, 50, 210, 29);
		btnViewBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.showBalance();
			}
		});
		chequingfrm.getContentPane().setLayout(null);
		chequingfrm.getContentPane().add(btnViewBalance);
		
		JButton btnViewLastTransaction = new JButton("View last transaction");
		btnViewLastTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.getLastTransaction();
			}
		});
		btnViewLastTransaction.setBounds(6, 102, 210, 29);
		chequingfrm.getContentPane().add(btnViewLastTransaction);
		
		JButton btnDepositFromFile = new JButton("Deposit from file");
		btnDepositFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.depositFromFile();
			}
		});
		btnDepositFromFile.setBounds(6, 156, 210, 29);
		chequingfrm.getContentPane().add(btnDepositFromFile);
		
		JButton btnTransferAcc = new JButton("Transfer to another account");
		btnTransferAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.transferToSelf();
			}
		});
		btnTransferAcc.setBounds(6, 214, 210, 29);
		chequingfrm.getContentPane().add(btnTransferAcc);
		
		JButton btnTransferUser = new JButton("Transfer to another user");
		btnTransferUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.transferToOther();
			}
		});
		btnTransferUser.setBounds(228, 50, 206, 29);
		chequingfrm.getContentPane().add(btnTransferUser);
		
		JButton btnPayExternalBill = new JButton("Pay external bill");
		btnPayExternalBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.transferToExternal();
			}
		});
		btnPayExternalBill.setBounds(228, 102, 206, 29);
		chequingfrm.getContentPane().add(btnPayExternalBill);
		
		JButton btnWithdrawMoney = new JButton("Withdraw money");
		btnWithdrawMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.withdraw();
			}
		});
		btnWithdrawMoney.setBounds(228, 156, 206, 29);
		chequingfrm.getContentPane().add(btnWithdrawMoney);
		
		JButton btnDepositCash = new JButton("Deposit cash");
		btnDepositCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.depositCash();
			}
		});
		btnDepositCash.setBounds(228, 214, 210, 29);
		chequingfrm.getContentPane().add(btnDepositCash);
		
		JLabel lblChequingMenu = new JLabel("Chequing menu");
		lblChequingMenu.setBounds(6, 17, 111, 16);
		chequingfrm.getContentPane().add(lblChequingMenu);
		
		JButton btnReturnToAccounts = new JButton("Return to my accounts");
		btnReturnToAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user instanceof Customer) {
					chequingfrm.setVisible(false);
					CustomerMenuGUI window = new CustomerMenuGUI(user);
					window.customerfrm.setVisible(true);
				}
				else{
					chequingfrm.setVisible(false);
					EmployeeMenuGUI window = new EmployeeMenuGUI(user);
					window.employeeFrm.setVisible(true);
				}
			}
		});
		btnReturnToAccounts.setBounds(134, 243, 172, 29);
		chequingfrm.getContentPane().add(btnReturnToAccounts);
	}

}
