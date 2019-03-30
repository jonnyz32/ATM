import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.regex.Pattern;

public class BankManagerMenuGUI {

	public JFrame managerFrm;
	final Scanner in = new Scanner(System.in);
	BankManager manager;

	/**
	 * Launch the application.
	 */
	public static void main(ATM_User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankManagerMenuGUI window = new BankManagerMenuGUI(user);
					window.managerFrm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BankManagerMenuGUI(ATM_User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ATM_User user) {
		BankManager manager = (BankManager) user;
		managerFrm = new JFrame();
		managerFrm.setBounds(100, 100, 450, 300);
		managerFrm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		managerFrm.getContentPane().setLayout(null);
		
		JLabel lblBankManagerMenu = new JLabel("Bank manager menu");
		lblBankManagerMenu.setBounds(17, 19, 146, 16);
		managerFrm.getContentPane().add(lblBankManagerMenu);
		
		JButton btnSetDate = new JButton("Set new date");
		btnSetDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame yearFrame = new JFrame();
			    String strYear = JOptionPane.showInputDialog(yearFrame, "Input year:");
			    JFrame monthFrame = new JFrame();
			    String strMonth = JOptionPane.showInputDialog(monthFrame, "Input month (numerical value):");
			    JFrame dayFrame = new JFrame();
			    String strDay = JOptionPane.showInputDialog(dayFrame, "Input day (numerical value):");

				if(strYear != null && strMonth != null && strDay != null) {
					if(isNumeric(strYear) && isNumeric(strMonth) && isNumeric(strDay)) {
						if(Integer.parseInt(strYear) > 0 && Integer.parseInt(strMonth) > 0 && Integer.parseInt(strMonth) < 13 &&
								Integer.parseInt(strDay) > 0 && Integer.parseInt(strDay) < 32) {
							int year = Integer.parseInt(strYear);
							int month = Integer.parseInt(strMonth);
							int day = Integer.parseInt(strDay);
							manager.setSystemDate(year, month, day);
							JFrame notice = new JFrame();
							String infoMessage = "Success! System time set to: " + ATM_machine.getTimeFormatted();
							JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							showInputError();
							return;
						}
					}
					else {
						showInputError();
						return;
					}
				}
				else {
					showInputError();
					return;
				}
			}
		});
		btnSetDate.setBounds(27, 47, 207, 29);
		managerFrm.getContentPane().add(btnSetDate);
		
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
			    if(strFive != null && strTen != null && strTwenty != null && strFifty != null) {
					if (isNumeric(strFive) && isNumeric(strTen) && isNumeric(strTwenty) && isNumeric(strFifty)){
						numFive = Integer.parseInt(strFive);
						numTen = Integer.parseInt(strTen);
						numTwenty = Integer.parseInt(strTwenty);
						numFifty = Integer.parseInt(strFifty);
					}
					else{
						showInputError();
						return;
					}
				}
			    else{
			    	showInputError();
			    	return;
				}
			    int result = -1;
			    if(strFive != null && strTen != null && strTwenty != null && strFifty != null) {
					manager.addBills(numFive, numTen, numTwenty, numFifty);
					showSuccess();
				}
		        
			}
		});
		btnRestockBills.setBounds(27, 88, 207, 29);
		managerFrm.getContentPane().add(btnRestockBills);
		
		JButton btnUndo = new JButton("Undo a customer transaction");
		btnUndo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFrame userFrame = new JFrame();
			    String strUser = JOptionPane.showInputDialog(userFrame, "Input target user:");
			    JFrame accountFrame = new JFrame();
			    String strAccount = JOptionPane.showInputDialog(accountFrame, "Input account name:");
				JFrame numFrame = new JFrame();
				String strNum = JOptionPane.showInputDialog(accountFrame, "How many of the transactions would you like to undo:");
				int numTransactions = -1;
				if(strNum != null){
					if(isNumeric(strNum)){
						numTransactions = Integer.parseInt(strNum);
					}
					else{
						showInputError();
						return;
					}
				}
				else{
					showInputError();
					return;
				}
			    if(strUser != null && strAccount != null && numTransactions != -1) {
					try {
						manager.undoTransaction(strUser, strAccount, numTransactions);
						showSuccess();
					} catch (BadInputException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			    else{
					showInputError();
					return;
				}
			}
		});
		btnUndo.setBounds(27, 129, 207, 29);
		managerFrm.getContentPane().add(btnUndo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(246, 19, 198, 185);
		scrollPane.setVisible(false);
		managerFrm.getContentPane().add(scrollPane);
		
		DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> accountList = new JList<>( model );
        accountList.setVisible(false);
        //https://stackoverflow.com/questions/16214480/adding-elements-to-a-jlist
        scrollPane.setViewportView(accountList);
        
        JButton btnConfirm = new JButton("Approve selected request");
		btnConfirm.setBounds(238, 211, 206, 29);
		btnConfirm.setVisible(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int listOption = accountList.getSelectedIndex();
				try {
					manager.approveAccount(listOption);
					showSuccess();
		        	List<AccountCreationRequest> requests = manager.getRequests();
		        	model.clear();
		        	for(AccountCreationRequest request: requests){
						String type = request.getType();
						model.addElement(request.getUser() + " requests a " + type + " account");
					}
		        	JList<String> accountList = new JList<>( model );
		        	scrollPane.setViewportView(accountList);
					
				} catch (BadInputException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		            
			}
		});
		managerFrm.getContentPane().add(btnConfirm);
		
		JButton btnApproveAccRequest = new JButton("Approve an account request");
		btnApproveAccRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<AccountCreationRequest> requests = manager.getRequests();
		        if (requests.size() < 1){
		        	JFrame notice = new JFrame();
		        	String infoMessage = "No requests available";
		        	JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
		            return;
		        }
				
				scrollPane.setVisible(true);
		        accountList.setVisible(true);
		        btnConfirm.setVisible(true);
		        
				for(AccountCreationRequest request: requests){
					String type = request.getType();
					model.addElement(request.getUser() + " requests a " + type + " account");
				}
			}
		});
		btnApproveAccRequest.setBounds(27, 170, 207, 29);
		managerFrm.getContentPane().add(btnApproveAccRequest);
		
		
		JButton btnCreateNewCustomer = new JButton("Create a new customer");
		btnCreateNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options1 = {"Employee", "User", "Cancel"};
				int result = JOptionPane.showOptionDialog(null, "What type of user are you making?",
						null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options1, null);
				JFrame usernameFrame = new JFrame();
				String strUser = JOptionPane.showInputDialog(usernameFrame, "Input username:");
				JFrame passwordFrame = new JFrame();
				String strPass = JOptionPane.showInputDialog(passwordFrame, "Input password:");
				if(strUser != null && isAlphaNumeric(strUser) && strPass != null && isAlphaNumeric(strPass)) {
					if(result == 0 || result == 1)
						manager.createNewUser(strUser, strPass, result);
					else {
						BankManagerMenuGUI.showInputError();
						return;
					}
					showSuccess();
				}
				else {
					showInputError();
					return;
				}
			}
		});
		btnCreateNewCustomer.setBounds(27, 211, 207, 29);
		managerFrm.getContentPane().add(btnCreateNewCustomer);
		
		JButton btnReturnToMain = new JButton("Return to main menu");
		btnReturnToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				managerFrm.setVisible(false);
				MainMenuGUI window = new MainMenuGUI();
                window.mainFrm.setVisible(true);
			}
		});
		btnReturnToMain.setForeground(Color.BLACK);
		btnReturnToMain.setBackground(Color.LIGHT_GRAY);
		btnReturnToMain.setBounds(258, 243, 168, 29);
		managerFrm.getContentPane().add(btnReturnToMain);
	}
	
	//following method taken from https://www.techiedelight.com/check-string-contains-alphanumeric-characters-java/
	public static boolean isAlphaNumeric(String s) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
		return p.matcher(s).find();
	}
	
	public static boolean isNumeric(String s) {
		Pattern p = Pattern.compile("^[0-9]*$");
		return p.matcher(s).find();
	}

	public static void showInputError() {
		JFrame notice = new JFrame();
		String infoMessage = "Invalid inputs. Try again.";
		JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showSuccess(){
		JFrame notice = new JFrame();
		String infoMessage = "Success";
		JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
	}
}