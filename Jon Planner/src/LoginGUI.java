import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class LoginGUI {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(110, 62, 89, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(110, 124, 89, 14);
		frame.getContentPane().add(lblPassword);
		
		usernameField = new JTextField();
		usernameField.setBounds(254, 59, 151, 20);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(254, 121, 151, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				final UserList userList = new UserList();
				
				try {
					userList.readFile("UserList.txt");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				// simple test method
				if (userList.isValidPassword(username, password)) {
					JOptionPane.showMessageDialog(null,"Login succesful");
					frame.dispose();
					int type = userList.getType(username);
					if(type == 0) {
					new AdminMenu().setVisible(true);
					}
					else if(type==1) {
					new ClientMenu().setVisible(true);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(frame, "Invalid username or password");
				}
				passwordField.setText("");
			}
		});
		btnLogin.setBounds(169, 194, 89, 23);
		frame.getContentPane().add(btnLogin);
	}

	
	
}
