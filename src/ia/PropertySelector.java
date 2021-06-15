package ia;

import java.awt.EventQueue;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PropertySelector extends User{

	private JFrame frmUniBudget;
	private JComboBox comboBox;
	private JPanel contentPane;
	public static String uniPostalCode;
	public static String formatedUniName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PropertySelector window = new PropertySelector();
					window.frmUniBudget.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public PropertySelector() throws SQLException {
		initialize();
	}
	
	/**
	 * Program Functions
	 */
	


	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frmUniBudget = new JFrame();
		frmUniBudget.setBounds(100, 100, 450, 180);
		frmUniBudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frmUniBudget.setTitle("UniBudget");
		frmUniBudget.getContentPane().setLayout(null);

		
		JLabel titleLbl = new JLabel("Find Property Listings");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		titleLbl.setBounds(10, 11, 424, 22);
		frmUniBudget.getContentPane().add(titleLbl);
		
		JLabel uniSelLbl = new JLabel("View Lisings for");
		uniSelLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		uniSelLbl.setBounds(20, 41, 103, 19);
		frmUniBudget.getContentPane().add(uniSelLbl);
		
		/* An array with the length of the total number of universities the user has is created below 
    	 * This is then used below to create an array with all the users choices stored by their name*/
    	int choiceCount = 1;
		User.totalChoices = 5; 
		String UniNames [] = new String [5];
		while (choiceCount <=5) {
			String [] arr = choice("choice"+choiceCount);
			if(arr[0] == null) {
				totalChoices--; }
			UniNames[choiceCount-1] = arr[2]; // Must have intermediate array to avoid null pointer exception
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
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(User.arrayOfUnis));
		comboBox.setBounds(119, 44, 305, 22);
		frmUniBudget.getContentPane().add(comboBox);
		
		JButton searchBtn = new JButton("Find!");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String uniName = (String) comboBox.getSelectedItem();
					formatedUniName = uniName.replaceAll("\\s","%20"); // All whitespace is replaces by %20 to allow a URL to be built for use with the API
					
					String APIkey = "AIzaSyA0SQ2rQ94n57EEJkZ_eKZ6cNdgCCu1r1g";
					URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+formatedUniName+"&key="+APIkey);
					BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
					String jsonString = "";
					String strTemp = "";
					
					// The output from the API is copied from the BufferedReader into a String to allow it to be read
					while (null != (strTemp = br.readLine())) {
						jsonString +=  strTemp;
					}
					
					// A Gson JSONObject is built with all the dataJSON is then sorted through the various arrays until the post code is reached
					JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
					JsonArray resultsArray = jsonObject.getAsJsonArray("results");
					JsonObject resultsObject = (JsonObject) resultsArray.get(0);
					JsonArray addressArray = resultsObject.getAsJsonArray("address_components");
					int addArrLen = addressArray.size() - 1; // Different locations have a different array size so this is to always get the post code 
					uniPostalCode = addressArray.get(addArrLen).getAsJsonObject().get("long_name").getAsString();  // Post code is stored for use later
					 PropertyVariablesSelection.main(null);
					 frmUniBudget.dispose();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
		});
		searchBtn.setBounds(335, 105, 89, 23);
		frmUniBudget.getContentPane().add(searchBtn);
		
		JButton menuBtn = new JButton("Menu");
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUniBudget.dispose();
				MainMenu.main(null);
			}
		});
		menuBtn.setBounds(20, 105, 89, 23);
		frmUniBudget.getContentPane().add(menuBtn);
		
		
		
		
		
		
	}
}
