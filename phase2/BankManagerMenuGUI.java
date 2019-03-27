import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class BankManagerMenuGUI {

	public JFrame managerFrm;
	final Scanner in = new Scanner(System.in);

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
				System.out.println("Input year:");
		        int year = in.nextInt();
		        System.out.println("Input month:");
		        int month = in.nextInt();
		        System.out.println("Input day:");
		        int day = in.nextInt();
		        if (manager.setSystemDate(year, month, day)) {
		            System.out.println("System time set to: " + ATM_machine.getTimeFormatted());
		        }
		        else{
		            System.out.println("Invalid date entry");
		        }
			}
		});
		btnSetDate.setBounds(27, 47, 207, 29);
		managerFrm.getContentPane().add(btnSetDate);
		
		JButton btnRestockBills = new JButton("Restock bills");
		btnRestockBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("What kind?");
		        int type = in.nextInt();
		        System.out.println("How many?");
		        int num = in.nextInt();
		        int result = manager.addBills(type, num);
		        if(result == -1){
		            System.out.println("ERROR: Invalid input");
		        }
		        else{
		            System.out.println("New number: " + result + " bills");
		        }
			}
		});
		btnRestockBills.setBounds(27, 88, 207, 29);
		managerFrm.getContentPane().add(btnRestockBills);
		
		JButton btnUndo = new JButton("Undo a customer transaction");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Input target user:");
		        String username = in.nextLine();
		        System.out.println("Input account name:");
		        String account = in.nextLine();
		        manager.undoTransaction(username, account);
			}
		});
		btnUndo.setBounds(27, 129, 207, 29);
		managerFrm.getContentPane().add(btnUndo);
		
		JButton btnApproveAccRequest = new JButton("Approve an account request");
		btnApproveAccRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<AccountCreationRequest> requests = BankManager.getRequests();
		        int i = 0;
		        if (requests.size() < 1){
		            System.out.println("No requests available");
		            return;
		        }

		        for(AccountCreationRequest request: requests){
		            String type = request.getType();
		            System.out.println(i + ": " + request.getUser() + " requests a " + type + " account");
		            i++;
		        }

		        System.out.println("Input id to approve:");
		        int target = in.nextInt();
		        while (target>=requests.size()){
		            System.out.println("Invalid id, try again.");
		            target = in.nextInt();
		        }

		        if(manager.approveAccount(target)){
		            System.out.println("Request approved");
		        }
		        else{
		            System.out.println("Error: Request not valid");
		        }
			}
		});
		btnApproveAccRequest.setBounds(27, 170, 207, 29);
		managerFrm.getContentPane().add(btnApproveAccRequest);
		
		JButton btnCreateNewCustomer = new JButton("Create a new customer");
		btnCreateNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Input username:");
		        String username = in.nextLine();
		        System.out.println("Input password:");
		        String password = in.nextLine();
		        if (manager.createNewCustomer(username, password)){
		            System.out.println("Account created");
		        }
		        else{
		            System.out.println("Error: Account not available");
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
}
