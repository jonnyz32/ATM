
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreditCardMenuGUI {

	JFrame creditCardfrm;
	AccountMenu menu;

	/**
	 * Launch the application.
	 */
	public static void main(GenericAccount accountToDisplay, ATM_User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditCardMenuGUI window = new CreditCardMenuGUI(accountToDisplay, user);
					window.creditCardfrm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreditCardMenuGUI(GenericAccount accountToDisplay, ATM_User user) {
		initialize(accountToDisplay, user);
		menu = new AccountMenu(accountToDisplay);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(GenericAccount accountToDisplay, ATM_User user) {
		creditCardfrm = new JFrame();
		creditCardfrm.setBounds(100, 100, 450, 300);
		creditCardfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		creditCardfrm.getContentPane().setLayout(null);
		
		JButton btnViewBalance = new JButton("View balance");
		btnViewBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.showBalance();
			}
		});
		btnViewBalance.setBounds(113, 63, 210, 29);
		creditCardfrm.getContentPane().add(btnViewBalance);
		
		JButton btnViewLastTransaction = new JButton("View last transaction");
		btnViewLastTransaction.setBounds(113, 115, 210, 29);
		btnViewLastTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.getLastTransaction();
			}
		});
		creditCardfrm.getContentPane().add(btnViewLastTransaction);
		
		JButton btnDeposit = new JButton("Deposit from file");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.depositFromFile();
			}
		});
		btnDeposit.setBounds(113, 169, 210, 29);
		creditCardfrm.getContentPane().add(btnDeposit);
		
		JLabel lblCreditCardMenu = new JLabel("Credit card menu");
		lblCreditCardMenu.setBounds(27, 25, 111, 16);
		creditCardfrm.getContentPane().add(lblCreditCardMenu);
		
		JButton btnReturnToAccounts = new JButton("Return to my accounts");
		btnReturnToAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user instanceof Customer) {
					creditCardfrm.setVisible(false);
					CustomerMenuGUI window = new CustomerMenuGUI(user);
					window.customerfrm.setVisible(true);
				}
                else{
					creditCardfrm.setVisible(false);
					EmployeeMenuGUI window = new EmployeeMenuGUI(user);
					window.employeeFrm.setVisible(true);
				}
			}
		});
		btnReturnToAccounts.setBounds(127, 226, 186, 29);
		creditCardfrm.getContentPane().add(btnReturnToAccounts);
	}

}
