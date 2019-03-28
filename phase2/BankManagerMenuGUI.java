import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SingleSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
			    			JFrame notice = new JFrame();
				        	String infoMessage = "Invalid inputs. Only input numbers that represent years, months, and years.";
				        	JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
			    		}
			    	}
			    	else {
			    		JFrame notice = new JFrame();
			        	String infoMessage = "Invalid inputs. Only input numbers that represent years, months, and years.";
			        	JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
			    	}
			    }
			    else {
			    	JFrame notice = new JFrame();
		        	String infoMessage = "Invalid inputs. Only input numbers that represent years, months, and years.";
		        	JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
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
			    if(strFive != null) 
			    	numFive = Integer.parseInt(strFive);
			    if(strTen != null) 
			    	numTen = Integer.parseInt(strTen);
			    if(strTwenty != null) 
			    	numTwenty = Integer.parseInt(strTwenty);
			    if(strFifty != null) 
			    	numFifty = Integer.parseInt(strFifty);
			    
			    int result = -1;
			    if(strFive != null && strTen != null && strTwenty != null && strFifty != null)
			    	manager.addBills(numFive, numTen, numTwenty, numFifty);
		        
	        	JFrame notice = new JFrame();
	        	String infoMessage = "Success";
	        	JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
		        
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
			    if(strUser != null && strAccount != null)
					try {
						manager.undoTransaction(strUser, strAccount);
					} catch (BadInputException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
		btnConfirm.setBounds(258, 211, 168, 29);
		btnConfirm.setVisible(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int listOption = accountList.getSelectedIndex();
				try {
					manager.approveAccount(listOption);
					JFrame notice = new JFrame();
		        	String infoMessage = "Success";
		        	JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
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
				JFrame usernameFrame = new JFrame();
			    String strUser = JOptionPane.showInputDialog(usernameFrame, "Input username:");
			    JFrame passwordFrame = new JFrame();
			    String strPass = JOptionPane.showInputDialog(passwordFrame, "Input password:");
			    if(strUser != null && isAlphaNumeric(strUser) && strPass != null && isAlphaNumeric(strPass)) {
			    	manager.createNewCustomer(strUser, strPass);
		        	JFrame notice = new JFrame();
		        	String infoMessage = "Success!";
		        	JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
			    }
			    else {
			    	JFrame notice = new JFrame();
		        	String infoMessage = "Incorrect input. Please try again, with only alphanumeric characters.";
		        	JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
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
}