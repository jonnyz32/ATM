
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

public class MainMenuGUI {

	public JFrame mainFrm;
	private JTextField userField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuGUI window = new MainMenuGUI();
					window.mainFrm.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenuGUI() {
		initialize();
		mainFrm.setDefaultLookAndFeelDecorated(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrm = new JFrame();
		mainFrm.setBackground(SystemColor.menu);
		mainFrm.setBounds(100, 100, 450, 300);
		mainFrm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainFrm.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("207 ATM");
		lblTitle.setBounds(122, 6, 207, 81);
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 42));
		mainFrm.getContentPane().add(lblTitle);
		
		JTextPane txtpnPleaseInputYour = new JTextPane();
		txtpnPleaseInputYour.setBounds(57, 72, 330, 42);
		txtpnPleaseInputYour.setEditable(false);
		txtpnPleaseInputYour.setBackground(SystemColor.window);
		txtpnPleaseInputYour.setText("Please input your password and username. Remember to protect your password at all times.");
		mainFrm.getContentPane().add(txtpnPleaseInputYour);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(57, 148, 66, 16);
		mainFrm.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(57, 176, 66, 16);
		mainFrm.getContentPane().add(lblPassword);
		
		JTextField userField = new JTextField();
		userField.setBounds(134, 143, 130, 26);
		mainFrm.getContentPane().add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 171, 130, 26);
		mainFrm.getContentPane().add(passwordField);
		
		JTextPane txtpnLoginError = new JTextPane();
		txtpnLoginError.setBackground(SystemColor.window);
		txtpnLoginError.setText("INCORRECT USERNAME OR PASSWORD. Try again.");
		txtpnLoginError.setEditable(false);
		txtpnLoginError.setBounds(276, 143, 168, 49);
		txtpnLoginError.setVisible(false);
		mainFrm.getContentPane().add(txtpnLoginError);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(162, 211, 117, 29);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ATM_User user = UserManager.getUser(userField.getText());
			        if ((user != null) && (user.getPassword().equals(new String(passwordField.getPassword())))){
			            if (user instanceof Customer) {
			            	mainFrm.setVisible(false);
		                    CustomerMenuGUI window = new CustomerMenuGUI(user);
		                    window.customerfrm.setVisible(true);
			            }
			            else if (user instanceof Employee){
							mainFrm.setVisible(false);
							EmployeeMenuGUI window = new EmployeeMenuGUI(user);
							window.employeeFrm.setVisible(true);
						}
			            else if (user instanceof BankManager) {
			            	mainFrm.setVisible(false);
		                    BankManagerMenuGUI window = new BankManagerMenuGUI(user);
		                    window.managerFrm.setVisible(true);
			            }
			        }
			        else{
			        	userField.setText("");
			        	passwordField.setText("");
			        	txtpnLoginError.setVisible(true);

			        }
            	}
				catch (Exception j) {
            			j.printStackTrace();
				}
			}
		});
		mainFrm.getContentPane().add(btnLogin);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(327, 243, 117, 29);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ATM_machine.onExit();
				System.exit(0);
			}
		});
		mainFrm.getContentPane().add(btnQuit);
		
	}
}
