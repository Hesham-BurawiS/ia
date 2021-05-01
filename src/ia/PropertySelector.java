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

public class PropertySelector {

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
	 */
	public PropertySelector() {
		initialize();
	}
	
	/**
	 * Program Functions
	 */
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
//		User.arrayOfUnis = new String[1];
//		User.arrayOfUnis[0] = "QMUL";
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(User.arrayOfUnis));
		comboBox.setBounds(119, 44, 305, 22);
		frmUniBudget.getContentPane().add(comboBox);
		
		JButton searchBtn = new JButton("Find!");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String uniName = (String) comboBox.getSelectedItem();
					formatedUniName = uniName.replaceAll("\\s","%20");
					
					String APIkey = "AIzaSyA0SQ2rQ94n57EEJkZ_eKZ6cNdgCCu1r1g";
					URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+formatedUniName+"&key="+APIkey);
					System.out.println(url);
					BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
					String jsonString = "";
					String strTemp = "";
					
					while (null != (strTemp = br.readLine())) {
						jsonString +=  strTemp;
					}
					
					JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
					JsonArray resultsArray = jsonObject.getAsJsonArray("results");
					JsonObject resultsObject = (JsonObject) resultsArray.get(0);
					JsonArray addressArray = resultsObject.getAsJsonArray("address_components");
					int addArrLen = addressArray.size() - 1; // Different places have a different array size so this is to always get the post code
					uniPostalCode = addressArray.get(addArrLen).getAsJsonObject().get("long_name").getAsString();
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
