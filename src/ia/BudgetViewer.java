package ia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


import java.awt.Font;
import javax.swing.JButton;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class BudgetViewer {

	private JFrame frmUniBudget;
	private JPanel panel;
	private JLabel expenseType;
	private JLabel lblCurrET;
	private JLabel lblCurrPC;
	private JLabel lblCurrAC;
	private JLabel lblCurrDiff;
	private JLabel lblError;
	private JLabel lblProjectedCostSubtotal;
	private JLabel lblActualCostSubtotal;
	private JLabel lblDifferenceSubtotal;
	private int tableChoice = 0;
	private String report = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BudgetViewer window = new BudgetViewer();
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
	public BudgetViewer() {
		initialize();
	}
	
	public double doubleCaster(String input) {
		try {
			return Double.valueOf(input);
		} catch (NumberFormatException e) {
			lblError.setText("Please input a number"); //Will never be returned because data is validated before being written into db but here as a failsafe
		}
		return 0;
	}
	
	protected String stringFiller(String text) {

		    char[] array = new char[35];
		    char[] arrayText = text.toCharArray();
		    for (int i = 0; i < arrayText.length; i++) {
				array[i] = arrayText[i]; }
		    for (int i = arrayText.length; i < array.length; i++) {
				array[i] = "  ".charAt(0);
			}
		    return new String(array);
		}
	
	public void emailDataFetcher(String table) {
		try {
			User.id = 12;
		 Connection conn = DriverManager.getConnection("jdbc:mysql://db.burawi.tech:3306/unibudget?verifyServerCertificate=false&useSSL=true", "hesho" , "cQnfD23b8tiYk!7h");				    					    
		    String sql = "SELECT * FROM " + table + " WHERE id = '" + User.id + "'AND uniChoice = '" + BudgetUniSelector.getUniIndex()+"'";
		    Statement stmt = null;
		    ResultSet rs = null;
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery(sql);
		    String currentET ="";
		    String currentPC = "";
		    String currentAC = "";
		    String currentDiff ="";		
			
		    report += "\n\n" + table.toUpperCase() +"\n";
		    report += "\n"+stringFiller("Expense Type")+stringFiller("Projected Cost")+stringFiller("Actual Cost")+stringFiller("Difference");
			while (rs.next()) {
				//System.out.println(rs.getString(3));
				System.out.println(rs.getString("expenseType"));
				currentET = rs.getString("expenseType");
				currentPC = rs.getString("projectedCost");
				currentAC = rs.getString("actualCost");
				currentDiff = rs.getString("difference");  //Duplication error if rs.getString is used directly 
				report += "\n" + stringFiller(currentET) + stringFiller(currentPC) + stringFiller(currentAC) + stringFiller(currentDiff);
			
			} 
		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	public void expenseChanger(String table) {
		Connection conn = null;
		try {
			
			expenseType.setText(table);
		    conn = DriverManager.getConnection("jdbc:mysql://db.burawi.tech:3306/unibudget?verifyServerCertificate=false&useSSL=true", "hesho" , "cQnfD23b8tiYk!7h");				    					    
		    String sql = "SELECT * FROM " + table + " WHERE id = '" + User.id + "'AND uniChoice = '" + BudgetUniSelector.getUniIndex()+"'";
		    Statement stmt = null;
		    ResultSet rs = null;
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery(sql);
		    
		    panel = new JPanel();
			panel.setBounds(0, 79, 684, 358);
			frmUniBudget.getContentPane().add(panel);
			panel.setLayout(null);
		    double projectedTotal = 0;
		    double actualTotal = 0;
		    double diffTotal = 0;
		    int i = 10;
			while (rs.next()) {
				lblCurrET = new JLabel(rs.getString("expenseType"));
				panel.add(lblCurrET);
				lblCurrET.setBounds(20, i, 157, 26);
				panel.add(lblCurrET);
				
				lblCurrPC = new JLabel("£"+rs.getString("projectedCost"));
				lblCurrPC.setHorizontalAlignment(SwingConstants.CENTER);
				lblCurrPC.setBounds(187, i, 157, 26);
				panel.add(lblCurrPC);
				projectedTotal += doubleCaster(rs.getString("projectedCost"));
				
				lblCurrAC = new JLabel("£"+rs.getString("actualCost"));
				lblCurrAC.setHorizontalAlignment(SwingConstants.CENTER);
				lblCurrAC.setBounds(354, i, 157, 26);
				panel.add(lblCurrAC);
				actualTotal += doubleCaster(rs.getString("actualCost"));
				
				lblCurrDiff = new JLabel("£"+rs.getString("difference"));
				lblCurrDiff.setHorizontalAlignment(SwingConstants.CENTER);
				lblCurrDiff.setBounds(521, i, 157, 26);
				panel.add(lblCurrDiff);
				diffTotal += doubleCaster(rs.getString("difference"));
				
				panel.validate();
				panel.repaint();
				i+=40;
			}
			lblProjectedCostSubtotal.setText("£"+projectedTotal);
			lblActualCostSubtotal.setText("£"+actualTotal);
			lblDifferenceSubtotal.setText("£"+diffTotal);
		    		    

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
		frmUniBudget = new JFrame();
		frmUniBudget.setTitle("UniBudget");
		frmUniBudget.setBounds(100, 100, 700, 570);
		frmUniBudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUniBudget.getContentPane().setLayout(null);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setBounds(20, 12, 267, 25);
		frmUniBudget.getContentPane().add(lblError);
		
		expenseType = new JLabel("Personal");
		expenseType.setFont(new Font("Tahoma", Font.BOLD, 22));
		expenseType.setHorizontalAlignment(SwingConstants.CENTER);
		expenseType.setBounds(10, 11, 664, 26);
		frmUniBudget.getContentPane().add(expenseType);
		
		JLabel lblExpenseType = new JLabel("Expense Type");
		lblExpenseType.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblExpenseType.setBounds(20, 48, 157, 26);
		frmUniBudget.getContentPane().add(lblExpenseType);
		
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSubtotal.setBounds(10, 436, 157, 26);
		frmUniBudget.getContentPane().add(lblSubtotal);
		
		JLabel lblProjectedCost = new JLabel("Projected Cost");
		lblProjectedCost.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProjectedCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectedCost.setBounds(187, 48, 157, 26);
		frmUniBudget.getContentPane().add(lblProjectedCost);
		
		JLabel lblActualCost = new JLabel("Actual Cost");
		lblActualCost.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblActualCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualCost.setBounds(354, 48, 157, 26);
		frmUniBudget.getContentPane().add(lblActualCost);
		
		JLabel lblDifference = new JLabel("Difference");
		lblDifference.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDifference.setHorizontalAlignment(SwingConstants.CENTER);
		lblDifference.setBounds(521, 48, 157, 26);
		frmUniBudget.getContentPane().add(lblDifference);
		
		
		lblProjectedCostSubtotal = new JLabel("£0.00");
		lblProjectedCostSubtotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProjectedCostSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectedCostSubtotal.setBounds(177, 439, 157, 26);
		frmUniBudget.getContentPane().add(lblProjectedCostSubtotal);
		
		
		lblActualCostSubtotal = new JLabel("£0.00");
		lblActualCostSubtotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblActualCostSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualCostSubtotal.setBounds(354, 439, 157, 26);
		frmUniBudget.getContentPane().add(lblActualCostSubtotal);
		
		
		lblDifferenceSubtotal = new JLabel("£0.00");
		lblDifferenceSubtotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDifferenceSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblDifferenceSubtotal.setBounds(521, 439, 157, 26);
		frmUniBudget.getContentPane().add(lblDifferenceSubtotal);
		
		final String budgetTables [] = {"Income","Housing","Entertainment","Transportation","Loans","Insurance","Savings","Food","Subscriptions","Personal"};
		
		expenseChanger("Income");
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BudgetUpdater.main(null);
				frmUniBudget.dispose();
			}
		});
		updateBtn.setBounds(354, 488, 157, 32);
		frmUniBudget.getContentPane().add(updateBtn);
		
		JButton menuBtn = new JButton("Menu");
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.main(null);
				frmUniBudget.dispose();
			}
		});
		menuBtn.setBounds(10, 488, 157, 32);
		frmUniBudget.getContentPane().add(menuBtn);
		
		JButton backBtn = new JButton("");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableChoice == 0) {
					tableChoice = 10; // To count for the subtraction below
				}
				tableChoice--;
				expenseChanger(budgetTables[tableChoice]);
				
			}
		});
		backBtn.setForeground(SystemColor.control);
		backBtn.setBackground(SystemColor.control);
		backBtn.setIcon(new ImageIcon(BudgetViewer.class.getResource("/resources/Back Chevron.png")));
		backBtn.setBounds(323, 488, 32, 32);
		frmUniBudget.getContentPane().add(backBtn);
		
		JButton nextBtn = new JButton("");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableChoice == 9) {
					tableChoice = -1; //To count for the addition below
				}
				tableChoice++;
				expenseChanger(budgetTables[tableChoice]);
				
			}
		});
		nextBtn.setBackground(SystemColor.control);
		nextBtn.setIcon(new ImageIcon(BudgetViewer.class.getResource("/resources/Forward Chevron.png")));
		nextBtn.setBounds(509, 488, 32, 32);
		frmUniBudget.getContentPane().add(nextBtn);
		
		JButton btnNewButton = new JButton("Email");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  String host="smtp.burawi.tech";  
				  final String user="unibudget@burawi.tech";//change accordingly  
				  final String password="SZpIvFP3";//change accordingly  
//				  User.email = "ioana.kada@bisc.edu.eg";
				  User.email = "t268@bisc.edu.eg";
				  User.firstName = "Ioana";
				  String to=User.email;//change accordingly  
				  
				   //Get the session object  
				   Properties props = new Properties();  
				   props.put("mail.smtp.host",host);  
				   props.put("mail.smtp.auth", "true");
				   props.put("mail.smtp.port", "587");
				     
				   Session session = Session.getDefaultInstance(props,new Authenticator() {  
				    protected PasswordAuthentication getPasswordAuthentication() {  
				    return new PasswordAuthentication(user,password);  
				      }  
				    });  
				  
				   //Compose the message  
				    try {  
				     MimeMessage message = new MimeMessage(session);  
				     message.setFrom(new InternetAddress(user));  
				     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
				     message.setSubject("UniBudget Report");  
				     report += "Hi " + User.firstName + ",";
				     report += "\nPlease find your UniBudget report below.";
				     report += "\nBUDGET PREPARED FOR " + User.arrayOfUnis[BudgetUniSelector.getUniIndex()]; 
				     for (int i = 0; i < budgetTables.length; i++) {
				    	 System.out.println(budgetTables[i]);
						emailDataFetcher(budgetTables[i]);
					}
				     System.out.println(report);
				     //message.setContent(report, "text/html");
<<<<<<< Updated upstream
				     message.setText(report);  
=======
				     message.setText(report);
>>>>>>> Stashed changes
				     
				     try {
				         File budgetFile = new File("filename.txt");
					 	if (budgetFile.delete()) { 
					      System.out.println("Deleted the file: " + budgetFile.getName());
					   	  }
				         if (budgetFile.createNewFile()) {
				           System.out.println("File created: " + budgetFile.getName());
				         } 
				         FileWriter myWriter = new FileWriter("filename.txt");
				         myWriter.write(report);
				         myWriter.close();
				         System.out.println("Successfully wrote to the file.");
				       } catch (IOException e1) {
				         System.out.println("An error occurred.");
				         e1.printStackTrace();
				       }
				    //send the message  
				     Transport.send(message);  
				  
				     System.out.println("message sent successfully...");  
				   
				     } catch (MessagingException e1) {
				    	 e1.printStackTrace();
				     	}  
			}
		});
		btnNewButton.setBounds(551, 488, 89, 32);
		frmUniBudget.getContentPane().add(btnNewButton);
		
	}
}
