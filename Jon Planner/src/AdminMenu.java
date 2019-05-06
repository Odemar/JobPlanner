import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AdminMenu extends JFrame {

	private JPanel homeScreen;
	private JPanel viewCalendar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminMenu() {
		setTitle("Job Planner - Administrator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		// Homescreen panel
		homeScreen = new JPanel();
		homeScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(homeScreen);
		homeScreen.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("homescreen");
		lblNewLabel.setBounds(176, 88, 114, 14);
		homeScreen.add(lblNewLabel);
		
		// Calendar panel
		viewCalendar = new JPanel();
		viewCalendar.setBorder(new EmptyBorder(5, 5, 5, 5));
		viewCalendar.setLayout(null);
		
		JLabel calendartag = new JLabel("Calendar");
		calendartag.setBounds(176, 88, 114, 14);
		viewCalendar.add(calendartag);
		

		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//
		// View pop-up menu
		//
		JMenu mnNewMenu = new JMenu("View");
		menuBar.add(mnNewMenu);
		
		// Calendar option
		JMenuItem mntmViewCalendar = new JMenuItem("Calendar");
		mntmViewCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove(homeScreen);
				setContentPane(viewCalendar);
				validate();
			}
		});
		mnNewMenu.add(mntmViewCalendar);
		// View users
		JMenuItem mntmViewAllUsers = new JMenuItem("Users");
		mntmViewAllUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnNewMenu.add(mntmViewAllUsers);
		// View job requests
		JMenuItem mntmViewJobRequest = new JMenuItem("Job Request");
		mntmViewJobRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnNewMenu.add(mntmViewJobRequest);
		//
		// Edit pop-up
		//
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnAdd = new JMenu("Add");
		mnNewMenu_1.add(mnAdd);
		//Add user
		JMenuItem mntmUser = new JMenuItem("User");
		mntmUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnAdd.add(mntmUser);
		// Add job
		JMenuItem mntmJob = new JMenuItem("Job");
		mntmJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnAdd.add(mntmJob);
		
		JMenu mnRemove = new JMenu("Remove");
		mnNewMenu_1.add(mnRemove);
		//remove user
		JMenuItem mntmUser_1 = new JMenuItem("User");
		mntmUser_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnRemove.add(mntmUser_1);
		//remove job
		JMenuItem mntmJob_1 = new JMenuItem("Job");
		mntmJob_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnRemove.add(mntmJob_1);
		
		JMenu mnEdit = new JMenu("Edit");
		mnNewMenu_1.add(mnEdit);
		//edit user
		JMenuItem mntmUser_2 = new JMenuItem("User");
		mntmUser_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnEdit.add(mntmUser_2);
		//edit job
		JMenuItem mntmJob_2 = new JMenuItem("Job");
		mntmJob_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnEdit.add(mntmJob_2);
		
		
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
