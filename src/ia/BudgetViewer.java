package ia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class BudgetViewer {

	private JFrame frmUniBudget;
	private JPanel panel;
	private JLabel expenseType;
	private JLabel lblName1;
	private JLabel lblName2;
	private JLabel lblName3;
	private JLabel lblName4;
	private int tableChoice = 0;

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
	
	public void expenseChanger(String table) {
		Connection conn = null;
		try {
			
			expenseType.setText(table);
		    conn = DriverManager.getConnection("jdbc:mysql://db.burawi.tech:3306/unibudget?verifyServerCertificate=false&useSSL=true", "hesho" , "cQnfD23b8tiYk!7h");				    					    
		    String sql = "SELECT * FROM " + table + " WHERE id = '" + User.id + "'AND uniChoice = '" + BudgetUniSelector.uniIndex+"'";
		    Statement stmt = null;
		    ResultSet rs = null;
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery(sql);
		    
		    panel = new JPanel();
			panel.setBounds(0, 79, 684, 358);
			frmUniBudget.getContentPane().add(panel);
			panel.setLayout(null);
		    
		    int i = 10;
			while (rs.next()) {
				lblName1 = new JLabel(rs.getString("expenseType"));
				panel.add(lblName1);
				lblName1.setBounds(20, i, 157, 26);
				panel.add(lblName1);
				
				lblName2 = new JLabel("£"+rs.getString("projectedCost"));
				lblName2.setHorizontalAlignment(SwingConstants.CENTER);
				lblName2.setBounds(187, i, 157, 26);
				panel.add(lblName2);
				
				lblName3 = new JLabel("£"+rs.getString("actualCost"));
				lblName3.setHorizontalAlignment(SwingConstants.CENTER);
				lblName3.setBounds(354, i, 157, 26);
				panel.add(lblName3);
				
				lblName4 = new JLabel("£"+rs.getString("difference"));
				lblName4.setHorizontalAlignment(SwingConstants.CENTER);
				lblName4.setBounds(521, i, 157, 26);
				panel.add(lblName4);
				
				panel.validate();
				panel.repaint();
				i+=40;
			}
		    		    

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
		
		
		JLabel lblProjectedCostSubtotal = new JLabel("£0.00");
		lblProjectedCostSubtotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProjectedCostSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectedCostSubtotal.setBounds(177, 439, 157, 26);
		frmUniBudget.getContentPane().add(lblProjectedCostSubtotal);
		
		
		JLabel lblActualCostSubtotal = new JLabel("£0.00");
		lblActualCostSubtotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblActualCostSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualCostSubtotal.setBounds(354, 439, 157, 26);
		frmUniBudget.getContentPane().add(lblActualCostSubtotal);
		
		
		JLabel lblDifferenceSubtotal = new JLabel("£0.00");
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
		
		
	}
}
