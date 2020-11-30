package ia;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

public class AddUnis {
	

	
	public static String[] legacyComplier() {
		String [] legacy = new String[371];
		String line = "";  
		int count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\hesho\\eclipse-workspace\\HeshoLabs\\src\\csv\\legacy.csv"));  
			while ((line = br.readLine()) != null) {  //returns a Boolean value  
				legacy[count] = line;
				count++;
			}  
		}   
		catch (IOException e)   {  
		e.printStackTrace();  
		}  
		return legacy;
	}
	private JFrame frmUnibudget;
	private JComboBox UCASCodeComboBox;
	private JLabel uniNameLbl;
	private JLabel loadingWheel; 
	private JComboBox campusComboBox;
	private JPanel panel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUnis window = new AddUnis();
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
	public AddUnis() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUnibudget = new JFrame();
		frmUnibudget.setTitle("UniBudget");
		frmUnibudget.setBounds(100, 100, 500, 300);
		frmUnibudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBorder(null);
		frmUnibudget.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		loadingWheel = new JLabel("");
		loadingWheel.setBounds(171, 98, 64, 64);
		panel.add(loadingWheel);
		loadingWheel.setIcon(new ImageIcon(AddUnis.class.getResource("/resources/loadingWheel.gif")));
		loadingWheel.setVisible(false);
		
		
		uniNameLbl = new JLabel("");
		uniNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		uniNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		uniNameLbl.setBounds(11, 11, 449, 39);
		panel.add(uniNameLbl);
		 
		JLabel cityLbl = new JLabel("City:");
		cityLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cityLbl.setBounds(49, 133, 35, 26);
		panel.add(cityLbl);
		
		JTextField currentCity = new JTextField("");
		currentCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentCity.setBounds(99, 61, 207, 26);
		panel.add(currentCity);
		
		JLabel courseLbl = new JLabel("Course Code:");
		courseLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		courseLbl.setBounds(11, 60, 88, 26);
		panel.add(courseLbl);
		
		JTextField currentCourse = new JTextField("");
		currentCourse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentCourse.setBounds(98, 98, 207, 26);
		panel.add(currentCourse);
		
		JLabel lengthLbl = new JLabel("Length:");
		lengthLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lengthLbl.setBounds(39, 96, 49, 26);
		panel.add(lengthLbl);
		
		JTextField currentLength = new JTextField("");
		currentLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentLength.setBounds(98, 134, 207, 26);
		panel.add(currentLength);
		
		JLabel lblCost = new JLabel("Cost:");
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCost.setBounds(44, 171, 35, 26);
		panel.add(lblCost);
		
		JTextField currentCost = new JTextField("");
		currentCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentCost.setBounds(98, 172, 207, 26);
		panel.add(currentCost);
		
		JButton menuBtn = new JButton("Menu");
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.main(null);
				frmUnibudget.dispose();
			}
		});
		menuBtn.setBounds(10, 227, 89, 23);
		panel.add(menuBtn);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Check if the user has 5 uni choices
				//TODO Check if this option is already added
				
				
			}
		});
		addBtn.setBounds(385, 227, 89, 23);
		panel.add(addBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setEnabled(false);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Fill
			}
		});
		cancelBtn.setBounds(286, 227, 89, 23);
		panel.add(cancelBtn);
		
		JLabel campusLbl = new JLabel("Campus");
		campusLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		campusLbl.setBounds(328, 133, 156, 33);
		panel.add(campusLbl);
		
		campusComboBox = new JComboBox();
		campusComboBox.setModel(new DefaultComboBoxModel(new String[] {"-"}));
		campusComboBox.setBounds(328, 175, 132, 22);
		panel.add(campusComboBox);
		
		JLabel uniCodeLbl = new JLabel("University UCAS Code");
		uniCodeLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		uniCodeLbl.setBounds(328, 57, 156, 33);
		panel.add(uniCodeLbl);
		
		
		UCASCodeComboBox = new JComboBox();
		UCASCodeComboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				 // This code isn't runinning because the thing doesn't display until it pops up
				panel.revalidate();
				panel.repaint();
				
				String codeChoice = (String) UCASCodeComboBox.getSelectedItem();
				String [] temp = new String[40]; //Set to 40 to account for all possible venues
				int count = 0;
				// Search db for uni corrosponding to LC and update the uniLbl and add option for campus
				// Database Connection to verify user information
				Connection conn = null;
				try {
				    conn = DriverManager.getConnection("jdbc:mysql://db.burawi.tech:3306/unibudget?verifyServerCertificate=false&useSSL=true", "hesho" , "cQnfD23b8tiYk!7h");
				    Statement stmt = null;
				    ResultSet rs = null;
				    
				    stmt = conn.createStatement();
				    rs = stmt.executeQuery("SELECT * FROM legacyCodes WHERE LEGACY_CODE = " + "'" + codeChoice + "'");
				    while (rs.next()) {
				    	temp[count] = rs.getString("VENUE");
				    	uniNameLbl.setText(rs.getString("NAME"));
				       count++;
				        }
				    String [] venues = new String[count]; //Recreates array with accurate size to make sure combobox has no null values
				    for (int i = 0; i < venues.length; i++) {
						venues[i] = temp[i];
					}
				    
				    
				    campusComboBox.setModel(new DefaultComboBoxModel(venues));
				    loadingWheel.setVisible(false);
				
			} catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
			}


		});
		UCASCodeComboBox.setModel(new DefaultComboBoxModel(legacyComplier()));
		UCASCodeComboBox.setBounds(328, 101, 132, 22);
		panel.add(UCASCodeComboBox);
		

		

	}
}
