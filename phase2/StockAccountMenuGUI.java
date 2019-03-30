import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class StockAccountMenuGUI {

	private JFrame stockfrm;
	AccountMenu menu;

	/**
	 * Launch the application.
	 */
	public static void main(GenericAccount accountToDisplay, ATM_User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockAccountMenuGUI window = new StockAccountMenuGUI(accountToDisplay, user);
					window.stockfrm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StockAccountMenuGUI(GenericAccount accountToDisplay, ATM_User user) {
		initialize(accountToDisplay, user);
		menu = new AccountMenu(accountToDisplay);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(GenericAccount accountToDisplay, ATM_User user) {
		StockAccount account = (StockAccount) accountToDisplay;
		stockfrm = new JFrame();
		stockfrm.setBounds(100, 100, 450, 317);
		stockfrm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		stockfrm.getContentPane().setLayout(null);
		
		JButton btnReturnToMy = new JButton("Return to my accounts");
		btnReturnToMy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user instanceof Customer) {
					stockfrm.setVisible(false);
					CustomerMenuGUI window = new CustomerMenuGUI(user);
					window.customerfrm.setVisible(true);
				}
                else{
					stockfrm.setVisible(false);
					EmployeeMenuGUI window = new EmployeeMenuGUI(user);
					window.employeeFrm.setVisible(true);
				}
			}
		});
		btnReturnToMy.setBounds(228, 260, 206, 29);
		stockfrm.getContentPane().add(btnReturnToMy);
		
		JButton btnViewPortfolio = new JButton("View portfolio");
		btnViewPortfolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account.viewPortfolio();
			}
		});
		btnViewPortfolio.setBounds(6, 38, 210, 29);
		stockfrm.getContentPane().add(btnViewPortfolio);
		
		JButton btnViewProfit = new JButton("View profit");
		btnViewProfit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account.viewProfit();
			}
		});
		btnViewProfit.setBounds(6, 90, 210, 29);
		stockfrm.getContentPane().add(btnViewProfit);
		
		JButton btnCheckPrice = new JButton("Check symbol price");
		btnCheckPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFrame symbolFrame = new JFrame();
			        String symbol = JOptionPane.showInputDialog(symbolFrame, "Input the company's symbol");
					account.checkSymbolPrice(symbol);
				} catch (BadInputException e1) {
					// TODO Auto-generated catch block
					BankManagerMenuGUI.showInputError();
					e1.printStackTrace();
				}
			}
		});
		btnCheckPrice.setBounds(6, 144, 210, 29);
		stockfrm.getContentPane().add(btnCheckPrice);
		
		JButton btnCheckDetailedSymbol = new JButton("Check detailed symbol info");
		btnCheckDetailedSymbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					account.checkSymbolDetailedInfo();
				} catch (BadInputException e1) {
					// TODO Auto-generated catch block
					BankManagerMenuGUI.showInputError();
					e1.printStackTrace();
				}
			}
		});
		btnCheckDetailedSymbol.setBounds(6, 202, 210, 29);
		stockfrm.getContentPane().add(btnCheckDetailedSymbol);
		
		JButton btnSellShares = new JButton("Sell shares");
		btnSellShares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFrame symbolFrame = new JFrame();
			        String symbol = JOptionPane.showInputDialog(symbolFrame, "Input the company's symbol");
			        
					account.sellShares(symbol);
				} catch (BadInputException e1) {
					// TODO Auto-generated catch block
					BankManagerMenuGUI.showInputError();
					e1.printStackTrace();
				}
			}
		});
		btnSellShares.setBounds(228, 38, 206, 29);
		stockfrm.getContentPane().add(btnSellShares);
		
		JButton btnPurchaseShares = new JButton("Purchase shares");
		btnPurchaseShares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					account.purchaseShares();
				} catch (BadInputException e1) {
					// TODO Auto-generated catch block
					BankManagerMenuGUI.showInputError();
					e1.printStackTrace();
				}
			}
		});
		btnPurchaseShares.setBounds(228, 90, 206, 29);
		stockfrm.getContentPane().add(btnPurchaseShares);
		
		JButton btnWithdraw = new JButton("Withdraw money");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.withdraw();
			}
		});
		btnWithdraw.setBounds(228, 144, 206, 29);
		stockfrm.getContentPane().add(btnWithdraw);
		
		JButton btnDeposit = new JButton("Deposit cash");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.depositCash();
			}
		});
		btnDeposit.setBounds(228, 202, 210, 29);
		stockfrm.getContentPane().add(btnDeposit);
		
		
		JLabel lblStockMenu = new JLabel("Stock menu");
		lblStockMenu.setBounds(6, 10, 80, 16);
		stockfrm.getContentPane().add(lblStockMenu);
		
		JButton btnViewBalance = new JButton("Show balance");
		btnViewBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.showBalance();
			}
		});
		btnViewBalance.setBounds(6, 260, 210, 29);
		stockfrm.getContentPane().add(btnViewBalance);
	}
}
