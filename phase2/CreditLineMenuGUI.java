
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreditLineMenuGUI {

	JFrame creditlinefrm;
	AccountMenu menu;

	/**
	 * Launch the application.
	 */
	public static void main(GenericAccount accountToDisplay, ATM_User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditLineMenuGUI window = new CreditLineMenuGUI(accountToDisplay, user);
					window.creditlinefrm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreditLineMenuGUI(GenericAccount accountToDisplay, ATM_User user) {
		initialize(accountToDisplay, user);
		menu = new AccountMenu(accountToDisplay);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(GenericAccount accountToDisplay, ATM_User user) {
		creditlinefrm = new JFrame();
		creditlinefrm.setBounds(100, 100, 450, 300);
		creditlinefrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		creditlinefrm.getContentPane().setLayout(null);
		
		JButton btnViewBal = new JButton("View balance");
		btnViewBal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.showBalance();
			}
		});
		btnViewBal.setBounds(6, 43, 210, 29);
		creditlinefrm.getContentPane().add(btnViewBal);
		
		JButton btnTransferUser = new JButton("Transfer to another user");
		btnTransferUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.transferToOther();
			}
		});
		btnTransferUser.setBounds(228, 43, 206, 29);
		creditlinefrm.getContentPane().add(btnTransferUser);
		
		JButton btnViewLastTransaction = new JButton("View last transaction");
		btnViewLastTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.getLastTransaction();
			}
		});
		btnViewLastTransaction.setBounds(6, 95, 210, 29);
		creditlinefrm.getContentPane().add(btnViewLastTransaction);
		
		JButton btnPayExternalBill = new JButton("Pay external bill");
		btnPayExternalBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.transferToExternal();
			}
		});
		btnPayExternalBill.setBounds(228, 95, 206, 29);
		creditlinefrm.getContentPane().add(btnPayExternalBill);
		
		JButton btnDeposit = new JButton("Deposit from file");
		btnDeposit.setBounds(6, 149, 210, 29);
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.depositFromFile();
			}
		});
		creditlinefrm.getContentPane().add(btnDeposit);
		
		JButton btnWithdrawMoney = new JButton("Withdraw money");
		btnWithdrawMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.withdraw();
			}
		});
		btnWithdrawMoney.setBounds(228, 149, 206, 29);
		creditlinefrm.getContentPane().add(btnWithdrawMoney);
		
		JButton btnTransferAcc = new JButton("Transfer to another account");
		btnTransferAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.transferToSelf();
			}
		});
		btnTransferAcc.setBounds(6, 207, 210, 29);
		creditlinefrm.getContentPane().add(btnTransferAcc);
		
		JLabel lblCreditLineMenu = new JLabel("Credit line menu");
		lblCreditLineMenu.setBounds(6, 15, 111, 16);
		creditlinefrm.getContentPane().add(lblCreditLineMenu);
		
		JButton btnReturnToAccounts = new JButton("Return to my accounts");
		btnReturnToAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creditlinefrm.setVisible(false);
                CustomerMenuGUI window = new CustomerMenuGUI(user);
                window.customerfrm.setVisible(true);
			}
		});
		btnReturnToAccounts.setBounds(131, 243, 175, 29);
		creditlinefrm.getContentPane().add(btnReturnToAccounts);
	}

}
