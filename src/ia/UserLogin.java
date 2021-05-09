package ia;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserLogin extends User{

	private static JFrame frmUnibudget;
	private static JTextField emailTxt;
	private static JPasswordField passwordField;
	private static JLabel notificationLbl;
	

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin window = new UserLogin();
					window.frmUnibudget.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserLogin() {
		initialize();
	}
	
	public static void login() {
		String unHashedPassword = passwordField.getText();
		String email = emailTxt.getText();
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		String hashedPassword = null;
		boolean validEmail = false;

		// Filled in Data check
		if (email.isBlank() || unHashedPassword.isBlank() ) {
			notificationLbl.setText("Please fill in all fields!");
			//TODO Stop Function
		}
		// Email Validation
		else if (!email.matches(regex)) {
			notificationLbl.setText("Please enter a valid email!");
		}
		else { // Used to reset label once conditions are met
			notificationLbl.setText("");
		}
		// Database Connection to verify user information
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection("jdbc:mysql://db.burawi.tech:3306/unibudget?verifyServerCertificate=false&useSSL=true", "hesho" , "cQnfD23b8tiYk!7h");
		    Statement stmt = null;
		    ResultSet rs = null;
		    
		    
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM users WHERE email = " + "'" + email + "'");
		    while (rs.next()) {
		    	 validEmail = true;
		         hashedPassword = rs.getString("password");
		        if(!BCrypt.checkpw(unHashedPassword, hashedPassword)) {
		        	notificationLbl.setText("Invalid email or password!");
		        }
		        else {
		        	notificationLbl.setText("");	
		        	// Set user data but do I want to put it in user file and use as global var and build an object?
		        	//User.main(null);
		        	User.firstName = rs.getString("firstName");
		        	User.lastName = rs.getString("lastName");
		        	User.email = email; // The variable is already used here that's why User.
		        	id = rs.getInt("id");
		        	int choiceCount = 1;
		    		User.totalChoices = 5; 
		    		String UniNames [] = new String [5];
		    		while (choiceCount <=5) {
		    			String [] arr = choice("choice"+choiceCount);
		    			if(arr[0] == null) {
		    				totalChoices--; }
		    			UniNames[choiceCount-1] = arr[2]; // Must have intermediate array to avoid null pointer ex
		    			choiceCount++;			
		    		}
		    		

		    		User.arrayOfUnis = new String[totalChoices];
		    		for (int i = 0; i < totalChoices; i++) {
		    			User.arrayOfUnis[i] = UniNames[i];
		    		}
		    		
		    		if(UniNames[0] != null) {
		    			User.choice1 = UniNames[0];
		    		}
		    		if(UniNames[1] != null) {
		    			User.choice2 = UniNames[1];
		    		}
		    		if(UniNames[2] != null) {
		    			User.choice3 = UniNames[2];
		    		}
		    		if(UniNames[3] != null) {
		    			User.choice4 = UniNames[3];
		    		}
		    		if(UniNames[4] != null) {
		    			User.choice5 = UniNames[4];
		    		}
		        	MainMenu.main(null);
		        	frmUnibudget.dispose();
		        }
		      }
		    // the while loop won't run if the email is invalid so this goes here
		    if (!validEmail) {
		    	notificationLbl.setText("Invalid email or password!");
		    }
		
		
	} catch (SQLException ex) {
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUnibudget = new JFrame();
		frmUnibudget.setResizable(false);
		frmUnibudget.setTitle("Unibudget");
		frmUnibudget.setBounds(100, 100, 465, 300);
		frmUnibudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUnibudget.getContentPane().setLayout(null);
		
		JLabel welcomeLbl = new JLabel("Login to Unibudget");
		welcomeLbl.setBounds(131, 6, 218, 22);
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		frmUnibudget.getContentPane().add(welcomeLbl);
		
		notificationLbl = new JLabel("");
		notificationLbl.setHorizontalAlignment(SwingConstants.CENTER);
		notificationLbl.setForeground(Color.RED);
		notificationLbl.setBounds(131, 34, 218, 14);
		frmUnibudget.getContentPane().add(notificationLbl);
		
		JLabel emailLbl = new JLabel("Email");
		emailLbl.setBounds(131, 59, 218, 17);
		emailLbl.setHorizontalAlignment(SwingConstants.CENTER);
		emailLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmUnibudget.getContentPane().add(emailLbl);
		
		emailTxt = new JTextField();
		emailTxt.setBounds(131, 82, 218, 20);
		frmUnibudget.getContentPane().add(emailTxt);
		emailTxt.setColumns(10);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setBounds(131, 133, 218, 17);
		passwordLbl.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmUnibudget.getContentPane().add(passwordLbl);
		
		
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					login();
					
				}
			}
			}
		);
		passwordField.setBounds(131, 156, 218, 20);
		frmUnibudget.getContentPane().add(passwordField);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
		}});
		loginBtn.setBounds(131, 207, 218, 25);
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmUnibudget.getContentPane().add(loginBtn);
		

		
	}
}
