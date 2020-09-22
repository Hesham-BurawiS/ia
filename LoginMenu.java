package ia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Font;

public class LoginMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMenu window = new LoginMenu();
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
	public LoginMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel welcomeLbl = new JLabel("Welcome to UniBudget");
		welcomeLbl.setBounds(90, 0, 254, 27);
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 22));
		frame.getContentPane().add(welcomeLbl);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setBounds(90, 92, 254, 23);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin.main(null);
				frame.dispose();	
			}
		});
		frame.getContentPane().add(loginBtn);
		
		JButton registrationBtn = new JButton("Register");
		registrationBtn.setBounds(90, 180, 254, 23);
		registrationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegistration.main(null);
				frame.dispose();	
			}
		});
		frame.getContentPane().add(registrationBtn);
	}

}
