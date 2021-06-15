package ia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class MainMenu extends User{

	private JFrame frmUnibudget;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUnibudget = new JFrame();
		frmUnibudget.setTitle("UniBudget");
		frmUnibudget.setBounds(100, 100, 450, 290);
		frmUnibudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUnibudget.getContentPane().setLayout(null);
		
		JLabel welcomeLbl = new JLabel("Welcome, " + User.firstName);
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		welcomeLbl.setBounds(132, 11, 164, 21);
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		frmUnibudget.getContentPane().add(welcomeLbl);
		
		JButton addUniBtn = new JButton("Add a Uni");
		addUniBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUnis.main(null);
				frmUnibudget.dispose();
			}
		});
		addUniBtn.setBounds(10, 51, 130, 23);
		frmUnibudget.getContentPane().add(addUniBtn);
		
		JButton viewUniBtn = new JButton("View your Unis");
		viewUniBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (User.totalChoices != 0 ) {
				ViewUnis.main(null);
				frmUnibudget.dispose(); 
				} else {
					JOptionPane.showMessageDialog(frmUnibudget, "You currently have no universities.\n Please add one first.", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		viewUniBtn.setBounds(294, 51, 130, 23);
		frmUnibudget.getContentPane().add(viewUniBtn);
		
		JButton budgetBtn = new JButton("Budget");
		budgetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (User.totalChoices != 0 ) {
					BudgetUniSelector.main(null);
					frmUnibudget.dispose(); 
				} else {
						JOptionPane.showMessageDialog(frmUnibudget, "You currently have no universities.\n Please add one first.", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				
			}
		});
		budgetBtn.setBounds(10, 145, 130, 23);
		frmUnibudget.getContentPane().add(budgetBtn);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginMenu.main(null);
				frmUnibudget.dispose();
			}
		});
		logoutBtn.setBounds(150, 212, 130, 23);
		frmUnibudget.getContentPane().add(logoutBtn);
		
		JButton viewPropertiesBtn = new JButton("View Properties");
		viewPropertiesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (User.totalChoices != 0 ) {
					PropertySelector.main(null);
					frmUnibudget.setVisible(false); 
				} else {
						JOptionPane.showMessageDialog(frmUnibudget, "You currently have no universities.\n Please add one first.", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}				
			}
		});
		viewPropertiesBtn.setBounds(294, 145, 130, 23);
		frmUnibudget.getContentPane().add(viewPropertiesBtn);
	}
}
