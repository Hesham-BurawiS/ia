package ia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ViewUnis {

	private JFrame frmUnibudget;
	private static int currentChoice;
	private static JLabel uniNameLbl;
	private static JLabel currentCity;
	private static JLabel currentCourse;
	private static JLabel currentLength;
	private static JLabel currentCost;
	private static JLabel currentUC;
	private static JLabel currentVenue;

	/**	
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUnis window = new ViewUnis();
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
	public ViewUnis() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUnibudget = new JFrame();
		frmUnibudget.setTitle("UniBudget");
		frmUnibudget.setBounds(100, 100, 450, 300);
		frmUnibudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		frmUnibudget.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		uniNameLbl = new JLabel("");
		uniNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		uniNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		uniNameLbl.setBounds(98, 11, 230, 26);
		panel.add(uniNameLbl);
		
		JLabel cityLbl = new JLabel("City:");
		cityLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cityLbl.setBounds(23, 60, 35, 26);
		panel.add(cityLbl);
		
		currentCity = new JLabel("");
		currentCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentCity.setBounds(55, 60, 217, 26);
		panel.add(currentCity);
		
		JLabel courseLbl = new JLabel("Course:");
		courseLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		courseLbl.setBounds(23, 97, 49, 26);
		panel.add(courseLbl);
		
		currentCourse = new JLabel("");
		currentCourse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentCourse.setBounds(74, 97, 207, 26);
		panel.add(currentCourse);
		
		JLabel lengthLbl = new JLabel("Length:");
		lengthLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lengthLbl.setBounds(23, 134, 49, 26);
		panel.add(lengthLbl);
		
		currentLength = new JLabel("");
		currentLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentLength.setBounds(74, 134, 207, 26);
		panel.add(currentLength);
		
		JLabel lblCost = new JLabel("Cost:");
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCost.setBounds(23, 171, 49, 26);
		panel.add(lblCost);
		
		currentCost = new JLabel("");
		currentCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentCost.setBounds(65, 171, 207, 26);
		panel.add(currentCost);
		
		JLabel venueLbl = new JLabel("Venue:");
		venueLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		venueLbl.setBounds(335, 105, 89, 14);
		panel.add(venueLbl);
		
		currentVenue = new JLabel("");
		currentVenue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentVenue.setBounds(335, 130, 89, 14);
		panel.add(currentVenue);
		
		JLabel UniCodeLbl = new JLabel("Uni Code:");
		UniCodeLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		UniCodeLbl.setBounds(335, 157, 89, 14);
		panel.add(UniCodeLbl);
		
		currentUC = new JLabel("");
		currentUC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentUC.setBounds(335, 182, 89, 14);
		panel.add(currentUC);
		
		// Updates the above lables witht the first choice
		currentChoice = 1;
		try {
			String[] currentUni = User.choice("choice"+currentChoice);
			for (int i = 0; i < currentUni.length; i++) {
				// TODO Turn this into a fucking function KEEP YOUR CODE DRY THIS IS WET AS SHIT
				currentUC.setText(currentUni[1]);
				uniNameLbl.setText(currentUni[2]);
				currentCity.setText(currentUni[3]);
				currentCourse.setText(currentUni[4]);
				currentLength.setText(currentUni[5] + " years");
				currentCost.setText("£"+currentUni[6]);
				currentVenue.setText(currentUni[7]);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} // End of update
		
		
		JButton menuBtn = new JButton("Menu");
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.main(null);
				frmUnibudget.dispose();
			}
		});
		menuBtn.setBounds(10, 227, 89, 23);
		panel.add(menuBtn);
		
		JButton nextBtn = new JButton("Next");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentChoice==5 || currentChoice==User.totalChoices) {
					currentChoice=0;
				}
				try {
					currentChoice++;
					String[] currentUni = User.choice("choice"+currentChoice);
					for (int i = 0; i < currentUni.length; i++) {
						currentUC.setText(currentUni[1]);
						uniNameLbl.setText(currentUni[2]);
						currentCity.setText(currentUni[3]);
						currentCourse.setText(currentUni[4]);
						currentLength.setText(currentUni[5] + " years");
						currentCost.setText("£"+currentUni[6]);
						currentVenue.setText(currentUni[7]);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		nextBtn.setBounds(335, 227, 89, 23);
		panel.add(nextBtn);
		
		JButton previousBtn = new JButton("Previous");
		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentChoice==1) {
					currentChoice=User.totalChoices+1; //Sets it to the max number adjusting for the subtraction below
				}
				try {
					currentChoice--;
					String[] currentUni = User.choice("choice"+currentChoice);
					for (int i = 0; i < currentUni.length; i++) {
						currentUC.setText(currentUni[1]);
						uniNameLbl.setText(currentUni[2]);
						currentCity.setText(currentUni[3]);
						currentCourse.setText(currentUni[4]);
						currentLength.setText(currentUni[5] + " years");
						currentCost.setText("£"+currentUni[6]);
						currentVenue.setText(currentUni[7]);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		previousBtn.setBounds(239, 227, 89, 23);
		panel.add(previousBtn);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int removalConfirmation = JOptionPane.showConfirmDialog(frmUnibudget, "Are you sure you would like to remove" + 
															uniNameLbl.getText(), "Confirm Removal?", JOptionPane.YES_NO_OPTION);
				if (removalConfirmation == 0) { // 0 means user has selected yes
					Connection conn = null;
					try {
					    conn = DriverManager.getConnection("jdbc:mysql://db.burawi.tech:3306/unibudget?verifyServerCertificate=false&useSSL=true", "hesho" , "cQnfD23b8tiYk!7h");
					    Statement stmt = conn.createStatement();					    					    
					    stmt.executeUpdate("DELETE FROM choice" + currentChoice + " WHERE id = " + User.id);
					    User.totalChoices--;

				} catch (SQLException ex) {
				    // handle any errors
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				}
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(ViewUnis.class.getResource("/resources/red-x.png")));
		btnNewButton.setBounds(360, 11, 64, 64);
		panel.add(btnNewButton);
		

	}
}
