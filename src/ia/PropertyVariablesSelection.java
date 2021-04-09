package ia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.SystemColor;

public class PropertyVariablesSelection {

	private JFrame frmUnibudget;
	private JTextField postCodeTxt;
	private JComboBox radiusCmb;
	private JComboBox maxPriceCmb;
	private JComboBox minPriceCmb;
	private JComboBox maxBedCmb;
	private JComboBox minBedCmb;
	private JComboBox maxPrice;
	private JComboBox propertyTypeCmb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PropertyVariablesSelection window = new PropertyVariablesSelection();
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
	public PropertyVariablesSelection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUnibudget = new JFrame();
		frmUnibudget.setResizable(false);
		frmUnibudget.setTitle("UniBudget");
		frmUnibudget.setBounds(100, 100, 640, 370);
		frmUnibudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUnibudget.getContentPane().setLayout(null);
		
//		String to essentially be recreated https://www.rightmove.co.uk/student-accommodation/find.html?locationIdentifier=POSTCODE%5E269204&maxBedrooms=3&minBedrooms=1&maxPrice=2250&minPrice=1250&radius=0.5&propertyTypes=&mustHave=&dontShow=&furnishTypes=&keywords=
		// Arrays to populate ComboBoxes below
		String [] radius = {"This area only","Within 1/4 miles","Within 1/2 miles","Within 1 mile","Within 3 miles","Within 5 miles","Within 10 miles","Within 15 miles","Within 20 miles","Within 30 miles","Within 40 miles"};
		String [] prices = {"Select PCM","£100","£150","£200","£250","£300","£350","£400","£500","£600","£700","£800","£900","£1000","£1100","£1200","£1300","£1400","£1500","£1750","£2000","£2250","£2500","£2750","£3000","£3500","£4000","£4500","£5000","£5500","£6000","£6500"
				,"£7000","£8000","£9000","£10000","£12500","£15000","£17500","£20000"};
		String [] beds = {"Any","Studio","1","2","3","4","5","6","7","8","9","10+"};
		String [] type = {"All Property Types", "Houses", "Flats"};
		
		
		JButton backBtn = new JButton("");
		backBtn.setToolTipText("Back Button");
		backBtn.setForeground(SystemColor.control);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUnibudget.dispose();
				PropertySelector.main(null);
			}
		});
		backBtn.setBackground(SystemColor.control);
		backBtn.setIcon(new ImageIcon(PropertyVariablesSelection.class.getResource("/resources/backButton.png")));
		backBtn.setBounds(10, 17, 65, 41);
		frmUnibudget.getContentPane().add(backBtn);
		
		// Title
		JLabel titleLbl = new JLabel("Please select your options");
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(10, 11, 604, 29);
		frmUnibudget.getContentPane().add(titleLbl);
		
		//Radius
		JLabel searchRadiusLbl = new JLabel("Search Radius");
		searchRadiusLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchRadiusLbl.setBounds(26, 71, 96, 14);
		frmUnibudget.getContentPane().add(searchRadiusLbl);
	
		radiusCmb = new JComboBox();
		radiusCmb.setModel(new DefaultComboBoxModel(radius));
		radiusCmb.setBounds(132, 69, 154, 22);
		frmUnibudget.getContentPane().add(radiusCmb);
		
		//Post Code
		JLabel postCodeLbl = new JLabel("Post Code");
		postCodeLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		postCodeLbl.setBounds(296, 73, 96, 14);
		frmUnibudget.getContentPane().add(postCodeLbl);
		
		postCodeTxt = new JTextField();
		postCodeTxt.setEditable(false);
		postCodeTxt.setBounds(392, 70, 96, 20);
		frmUnibudget.getContentPane().add(postCodeTxt);
		postCodeTxt.setColumns(10);
		postCodeTxt.setText(PropertySelector.uniPostalCode);
		
		//Min Price
		JLabel minPriceLbl = new JLabel("Minimum Price");
		minPriceLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		minPriceLbl.setBounds(26, 150, 96, 14);
		frmUnibudget.getContentPane().add(minPriceLbl);
		
		minPriceCmb = new JComboBox();
		minPriceCmb.setModel(new DefaultComboBoxModel(prices));
		minPriceCmb.setBounds(132, 148, 96, 22);
		frmUnibudget.getContentPane().add(minPriceCmb);
		
		//Max Price
		JLabel maxPriceLbl = new JLabel("Maximum Price");
		maxPriceLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maxPriceLbl.setBounds(296, 150, 96, 14);
		frmUnibudget.getContentPane().add(maxPriceLbl);
		
		maxPriceCmb = new JComboBox();
		maxPriceCmb.setModel(new DefaultComboBoxModel(prices));
		maxPriceCmb.setBounds(392, 148, 96, 22);
		frmUnibudget.getContentPane().add(maxPriceCmb);
		
		//Min Beds
		JLabel minimumBedsLbl = new JLabel("Minimum Beds");
		minimumBedsLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		minimumBedsLbl.setBounds(26, 225, 96, 14);
		frmUnibudget.getContentPane().add(minimumBedsLbl);
		
		minBedCmb = new JComboBox();
		minBedCmb.setModel(new DefaultComboBoxModel(beds));
		minBedCmb.setBounds(132, 223, 96, 22);
		frmUnibudget.getContentPane().add(minBedCmb);
		
		// Max Beds
		JLabel maximumBedsLbl = new JLabel("Maximum Beds");
		maximumBedsLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maximumBedsLbl.setBounds(296, 227, 96, 14);
		frmUnibudget.getContentPane().add(maximumBedsLbl);
		
		maxBedCmb = new JComboBox();
		maxBedCmb.setModel(new DefaultComboBoxModel(beds));
		maxBedCmb.setBounds(392, 223, 96, 22);
		frmUnibudget.getContentPane().add(maxBedCmb);
		
		// Property Type
		JLabel propertyTypesLbl = new JLabel("Property Types");
		propertyTypesLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		propertyTypesLbl.setBounds(26, 287, 96, 22);
		frmUnibudget.getContentPane().add(propertyTypesLbl);
		
		propertyTypeCmb = new JComboBox();
		propertyTypeCmb.setModel(new DefaultComboBoxModel(type));
		propertyTypeCmb.setBounds(132, 289, 154, 22);
		frmUnibudget.getContentPane().add(propertyTypeCmb);
		
		
		
		
		JButton zooplaBtn = new JButton("");
		zooplaBtn.setToolTipText("Search on Zoopla");
		zooplaBtn.setIcon(new ImageIcon(PropertyVariablesSelection.class.getResource("/resources/Zoopla Logo.png")));
		zooplaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String postCode = PropertySelector.uniPostalCode.replaceAll("\\s","-"); //Replaces whitespace to fill in the Zoopla API
				
				String maxBeds;
				if((maxBedCmb.getSelectedIndex() == 0)) {
					maxBeds = "";
				} else if((maxBedCmb.getSelectedIndex() == 11)) {
					maxBeds = "10";
				} else {
					maxBeds = (String) maxBedCmb.getSelectedItem(); }
				
				String minBeds;
				if((minBedCmb.getSelectedIndex() == 0)) {
					minBeds = "";
				} else if ((minBedCmb.getSelectedIndex() == 11)) {
					minBeds = "10";
				} else {
					minBeds = (String) minBedCmb.getSelectedItem(); }
				
				String maxPrice;
				if(maxPriceCmb.getSelectedIndex() == 0) {
					maxPrice = "";
				} else {
					maxPrice = (String) maxPriceCmb.getSelectedItem();
					maxPrice = maxPrice.substring(1); }

				String minPrice;
				if(minPriceCmb.getSelectedIndex() == 0) {
					minPrice = "0";
				} else {
					minPrice = (String) minPriceCmb.getSelectedItem();
					minPrice = minPrice.substring(1); }
				
				String [] searchRadius = {"0","0.25","0.5","1","3","5","10","15","20","30","40"};
				String radius =  searchRadius[radiusCmb.getSelectedIndex()];
				
				String propertyType;
				if(propertyTypeCmb.getSelectedIndex() == 0) {
					 propertyType = "property";
				} else {
				propertyType = (String) propertyTypeCmb.getSelectedItem(); }
				
				String url = "https://www.zoopla.co.uk/to-rent/"+propertyType+"/"+postCode+"/?beds_max="+maxBeds+"&beds_min="+minBeds+"&page_size=25&price_frequency=per_month&price_max="+maxPrice+"&price_min="+minPrice+
						"&q="+PropertySelector.formatedUniName+"&radius="+radius+"&results_sort=newest_listings&search_source=refine";
				Desktop desktop = java.awt.Desktop.getDesktop();
				try {
					desktop.browse(new URI(url));
				} catch (URISyntaxException | IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		zooplaBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		zooplaBtn.setBounds(296, 279, 101, 41);
		frmUnibudget.getContentPane().add(zooplaBtn);
		
		JButton rmBtn = new JButton("");
		rmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "https://www.rightmove.co.uk/student-accommodation/list-of-uk-universities.html";
				Desktop desktop = java.awt.Desktop.getDesktop();
				try {
					desktop.browse(new URI(url));
				} catch (URISyntaxException | IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		rmBtn.setToolTipText("Search on Rightmove");
		rmBtn.setBackground(Color.BLACK);
		rmBtn.setIcon(new ImageIcon(PropertyVariablesSelection.class.getResource("/resources/RightMove Logo.png")));
		rmBtn.setBounds(407, 279, 101, 41);
		frmUnibudget.getContentPane().add(rmBtn);
	}
}
