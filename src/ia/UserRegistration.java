package ia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class UserRegistration {

	private JFrame frmUnibudget;
	private JTextField firstNameTxt;
	private JTextField lastNameTxt;
	private JTextField emailTxt;
	private JPasswordField passwordField;
	private JPasswordField confrimPasswdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegistration window = new UserRegistration();
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
	public UserRegistration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUnibudget = new JFrame();
		frmUnibudget.setResizable(false);
		frmUnibudget.setTitle("UniBudget");
		frmUnibudget.setBounds(100, 100, 470, 415);
		frmUnibudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUnibudget.getContentPane().setLayout(null);
		
		JLabel welcomeLbl = new JLabel("Register for UniBudget");
		welcomeLbl.setBounds(134, 6, 205, 22);
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		frmUnibudget.getContentPane().add(welcomeLbl);
		
		JLabel firstNameLbl = new JLabel("First Name");
		firstNameLbl.setBounds(106, 59, 261, 17);
		firstNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		firstNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		frmUnibudget.getContentPane().add(firstNameLbl);
		
		firstNameTxt = new JTextField();
		firstNameTxt.setBounds(106, 82, 261, 20);
		firstNameLbl.setLabelFor(firstNameTxt);
		frmUnibudget.getContentPane().add(firstNameTxt);
		firstNameTxt.setColumns(10);
		
		JLabel lastNameLbl = new JLabel("Last Name");
		lastNameLbl.setBounds(204, 108, 64, 17);
		lastNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmUnibudget.getContentPane().add(lastNameLbl);
		
		lastNameTxt = new JTextField();
		lastNameTxt.setBounds(106, 131, 261, 20);
		frmUnibudget.getContentPane().add(lastNameTxt);
		lastNameTxt.setColumns(10);
		
		JLabel emailLbl = new JLabel("Email");
		emailLbl.setBounds(221, 157, 31, 17);
		emailLbl.setHorizontalAlignment(SwingConstants.CENTER);
		emailLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmUnibudget.getContentPane().add(emailLbl);
		
		emailTxt = new JTextField();
		emailTxt.setBounds(106, 180, 261, 20);
		frmUnibudget.getContentPane().add(emailTxt);
		emailTxt.setColumns(10);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setBounds(207, 206, 58, 17);
		passwordLbl.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmUnibudget.getContentPane().add(passwordLbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(106, 229, 261, 20);
		frmUnibudget.getContentPane().add(passwordField);
		
		JLabel confrimPasswdLbl = new JLabel("Confirm Password");
		confrimPasswdLbl.setBounds(181, 255, 110, 17);
		confrimPasswdLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		confrimPasswdLbl.setHorizontalAlignment(SwingConstants.CENTER);
		frmUnibudget.getContentPane().add(confrimPasswdLbl);
		
		confrimPasswdField = new JPasswordField();
		confrimPasswdField.setBounds(106, 278, 261, 20);
		frmUnibudget.getContentPane().add(confrimPasswdField);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.setBounds(106, 329, 261, 25);
		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmUnibudget.getContentPane().add(registerBtn);
	}

}
