package ia;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

import javax.swing.SwingConstants;

public class Property extends JFrame {

	private JPanel contentPane;
	private JComboBox uniComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Property frame = new Property();
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
	public Property() {
		setTitle("UniBudget");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("Find Property Listings");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		titleLbl.setBounds(10, 11, 534, 22);
		contentPane.add(titleLbl);
		
		JLabel uniSelLbl = new JLabel("View Lisings for");
		uniSelLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		uniSelLbl.setBounds(20, 41, 103, 19);
		contentPane.add(uniSelLbl);
		
		uniComboBox = new JComboBox();
		uniComboBox.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					String [] possibleUniNames = new String [5];
					int count = 0;
						for (int i = 1; i <= 5; i++) {
						String[] currentUni = User.choice("choice"+i);
							possibleUniNames[i-1] = currentUni[1];
							
							}

				    String [] uniNames = new String[count]; //Recreates array with accurate size to make sure combobox has no null values
				    for (int i = 0; i < uniNames.length; i++) {
						uniNames[i] = possibleUniNames[i];
					}
				    
				    
				    uniComboBox.setModel(new DefaultComboBoxModel(uniNames));
				
			} catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
			}
		});
		uniComboBox.setMaximumRowCount(5);
		uniComboBox.setBounds(120, 41, 70, 22);
		contentPane.add(uniComboBox);
	}

}
