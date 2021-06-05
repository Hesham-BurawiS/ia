package ia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;

import javax.swing.JButton;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
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
	public BudgetViewer()  {
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
			/* This method ensures that the given string has a total of 35 characters if less, whitespace is addedd this is make sure everything is
			 even in the report the new string is returned*/
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
				currentDiff = rs.getString("difference");  //Duplication error if rs.getString is used directly without specifically asigning these variables
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
		expenseType.setBounds(10, 12, 664, 26);
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
		lblDifferenceSubtotal.setBackground(SystemColor.control);
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
		
		JButton emailBtn = new JButton("Email");
		emailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  final String host = "smtp.burawi.tech";  // Host of the email server sending the email
				  final String user = "unibudget@burawi.tech"; // Email for report to be sent from
				  final String password = "SZpIvFP3";  // password for the email
//				  User.email = "ioana.kada@bisc.edu.eg";
//				  User.email = "t268@bisc.edu.eg";
//				  User.firstName = "Ioana";
				  String to = User.email; 
				  
				   // Assigns the properties to initiate a connection to the SMTP server 
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
				     report += "\nBUDGET PREPARED FOR " + User.arrayOfUnis[BudgetUniSelector.getUniIndex()-1]; 
				     for (int i = 0; i < budgetTables.length; i++) {
						emailDataFetcher(budgetTables[i]);
					}
				     
				     String filename = User.arrayOfUnis[BudgetUniSelector.getUniIndex()-1]+" Budget Report.txt";  // Subtraction is to ensure compatibility with arrays
				     BodyPart messageBodyPart = new MimeBodyPart();

			         // Fills the text component 
			         messageBodyPart.setText("Hi "+User.firstName+", \nPlease find your budget attached.");
			         
			         // Creates a multipart message
			         Multipart multipart = new MimeMultipart();

			         // adds previous text component part to the message
			         multipart.addBodyPart(messageBodyPart);
				     
				     try {
				         File budgetFile = new File(filename); 
					 	if (budgetFile.delete()) { 
					 		// Checks to see if file exists and if so delete's it in order to overwrite it with the new data
					   	  }
				         budgetFile.createNewFile(); // Creates the file for the report does not need if due to deletion above
				         
				         // Writes generated report to the file
				         FileWriter myWriter = new FileWriter(filename);
				         myWriter.write(report);
				         myWriter.close();
				       } catch (IOException e1) {
				         System.out.println("An error occurred.");
				         e1.printStackTrace();
				       }
				     
				     // Adding the attachment above to the email
			         messageBodyPart = new MimeBodyPart();
			         DataSource source = new FileDataSource(filename);
			         messageBodyPart.setDataHandler(new DataHandler(source));
			         messageBodyPart.setFileName(filename);
			         multipart.addBodyPart(messageBodyPart);

			         // Combines both parts text and attachment
			         message.setContent(multipart );  
			         
				    //send the message to the user
				     Transport.send(message);  
				   
				     } catch (MessagingException e1) {
				    	 e1.printStackTrace();
				     	}  
			}
		});
		emailBtn.setBounds(551, 488, 89, 32);
		frmUniBudget.getContentPane().add(emailBtn);
		
		JButton printBtn = new JButton("");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 report += "Hi " + User.firstName + ",";
			     report += "\nPlease find your UniBudget report below.";
			     report += "\nBUDGET PREPARED FOR " + User.arrayOfUnis[BudgetUniSelector.getUniIndex()-1]; 
			     for (int i = 0; i < budgetTables.length; i++) {
					emailDataFetcher(budgetTables[i]);
				}
			     
			     String filename = User.arrayOfUnis[BudgetUniSelector.getUniIndex()-1]+" Budget Report.txt"; 
			     
			     try {
			         File budgetFile = new File(filename); 
				 	if (budgetFile.delete()) { 
				 		// Checks to see if file exists and if so delete's it in order to overwrite it with the new data
				   	  }
			         budgetFile.createNewFile(); // Creates the file for the report does not need if due to deletion above
			         
			         // Writes generated report to the file
			         FileWriter myWriter = new FileWriter(filename);
			         myWriter.write(report);
			         myWriter.close();
			         
			         File file = new File ("University of Cambridge Budget Report.txt");
			     	Desktop desktop = Desktop.getDesktop();
			     	desktop.open(file);
			     	Thread.sleep(1000); // This is to give the document time to open so it is in focus to print 
			     	
			     	try {
			     	Robot robot = new Robot();
			         robot.setAutoDelay(250);
			         String os = System.getProperty("os.name"); //Checks for user's OS type to ensure the correct key sequence is sent
			         if(os.contains("Windows")) {
			         	// Key sequence to print on Windows 
			 	        robot.keyPress(KeyEvent.VK_CONTROL);
			 	        robot.keyPress(KeyEvent.VK_P);
			 	        //Keys are released otherwise they will be pressed until the program closes causing issues for the user
			 	        robot.keyRelease(KeyEvent.VK_CONTROL);
			 	        robot.keyRelease(KeyEvent.VK_P);
			 	        robot.keyPress(KeyEvent.VK_ENTER);
			         } else if(os.contains("Mac")) {
			         	// Key sequence to print on MacOS
			 	        robot.keyPress(KeyEvent.VK_META);  //This is meant to simulate the command key
			 	        robot.keyPress(KeyEvent.VK_P);
			 	        robot.keyRelease(KeyEvent.VK_META);
			 	        robot.keyRelease(KeyEvent.VK_P);
			 	        robot.keyPress(KeyEvent.VK_ENTER);
			         }
			     } catch (AWTException ex) {
			         ex.printStackTrace();
			     } 
			} catch (Exception e2) {
				e2.printStackTrace();
			} 
			     }
			     
		});
		printBtn.setIcon(new ImageIcon("C:\\Users\\H\\Downloads\\printing-text (2).png"));
		printBtn.setBounds(642, 488, 32, 32);
		frmUniBudget.getContentPane().add(printBtn);
		
	}
}
