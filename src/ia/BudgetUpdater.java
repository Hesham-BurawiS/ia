package ia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Color;

public class BudgetUpdater {

	private JFrame frmUnibudget;
	private JTextField projectedCostTxt;
	private JTextField actualCostTxt;
	private JTextField differenceTxt;
	private JLabel lblExpenseCata;
	private JComboBox expenseCmb;
	private ArrayList <Expenses> currentExpenses;
	private int tableChoice = 0;
	private double diff;
	private JLabel lblError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BudgetUpdater window = new BudgetUpdater();
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
	public BudgetUpdater() {
		initialize();
	}
	
	public double doubleCaster(String d) {
		try {
			return Double.valueOf(d);
		} catch (NumberFormatException e) {
			lblError.setText("Please input a number");
		}
		return diff;
	}
	
	public void expenseUpdater(String table) {
		Connection conn = null;
		try {
			
		    lblExpenseCata.setText(table);
		    conn = DriverManager.getConnection("jdbc:mysql://db.burawi.tech:3306/unibudget?verifyServerCertificate=false&useSSL=true", "hesho" , "cQnfD23b8tiYk!7h");				    					    
		    String sql = "SELECT * FROM " + table + " WHERE id = '" + User.id + "'AND uniChoice = '" + BudgetUniSelector.getUniIndex()+"'";
		    Statement stmt = null;
		    ResultSet rs = null;
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery(sql);
		    
			currentExpenses = new ArrayList<Expenses>();			
			
		    int i = 0;
			while (rs.next()) {
				Expenses currentExpense = new Expenses(rs.getString("expenseType"), Double.parseDouble(rs.getString("projectedCost")),Double.parseDouble(rs.getString("actualCost")),Double.parseDouble(rs.getString("difference")));				
				currentExpenses.add(currentExpense);
				i++; 
			} 
			String [] expenseTypes = new String[currentExpenses.size()];
			for (int j = 0; j < expenseTypes.length; j++) {
				expenseTypes[j] = currentExpenses.get(j).type;
			}
			//Initialize fields with first item
			Expenses current = currentExpenses.get(0);
			projectedCostTxt.setText(String.valueOf(current.projectedCost));
			actualCostTxt.setText(String.valueOf(current.actualCost));
			differenceTxt.setText(String.valueOf(current.difference));
			expenseCmb.setModel(new DefaultComboBoxModel(expenseTypes));
		} catch (SQLException ex) {
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			} 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUnibudget = new JFrame();
		frmUnibudget.setTitle("UniBudget");
		frmUnibudget.setBounds(100, 100, 700, 300);
		frmUnibudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUnibudget.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 82, 684, 68);
		frmUnibudget.getContentPane().add(panel);
		panel.setLayout(null);
		
		projectedCostTxt = new JTextField();
		projectedCostTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				diff = doubleCaster(projectedCostTxt.getText()) - doubleCaster(actualCostTxt.getText());
				differenceTxt.setText(String.valueOf(diff));
			}
		});
		projectedCostTxt.setHorizontalAlignment(SwingConstants.CENTER);
		projectedCostTxt.setBounds(177, 11, 157, 20);
		panel.add(projectedCostTxt);
		projectedCostTxt.setColumns(10);
		
		actualCostTxt = new JTextField();
		actualCostTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				diff = doubleCaster(projectedCostTxt.getText()) - doubleCaster(actualCostTxt.getText());
				differenceTxt.setText(String.valueOf(diff));
			}
		});
		actualCostTxt.setHorizontalAlignment(SwingConstants.CENTER);
		actualCostTxt.setColumns(10);
		actualCostTxt.setBounds(344, 11, 157, 20);
		panel.add(actualCostTxt);
		
		differenceTxt = new JTextField();
		differenceTxt.setEditable(false);
		differenceTxt.setHorizontalAlignment(SwingConstants.CENTER);
		differenceTxt.setColumns(10);
		differenceTxt.setBounds(511, 11, 157, 20);
		panel.add(differenceTxt);
		
		expenseCmb = new JComboBox();
		expenseCmb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Expenses current = currentExpenses.get(expenseCmb.getSelectedIndex());
				projectedCostTxt.setText(String.valueOf(current.projectedCost));
				actualCostTxt.setText(String.valueOf(current.actualCost));
				differenceTxt.setText(String.valueOf(current.difference));
			}
		});
		expenseCmb.setBounds(10, 10, 157, 22);
		panel.add(expenseCmb);
		
		lblExpenseCata = new JLabel("Expense Catagory");
		lblExpenseCata.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblExpenseCata.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpenseCata.setBounds(0, 0, 684, 25);
		frmUnibudget.getContentPane().add(lblExpenseCata);
		
		JLabel lblExpenseType = new JLabel("Expense Type");
		lblExpenseType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExpenseType.setBounds(10, 46, 157, 25);
		frmUnibudget.getContentPane().add(lblExpenseType);
		
		JLabel lblProjectCost = new JLabel("Projected Cost");
		lblProjectCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProjectCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectCost.setBounds(177, 46, 157, 25);
		frmUnibudget.getContentPane().add(lblProjectCost);
		
		JLabel lblActualCost = new JLabel("Actual Cost");
		lblActualCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActualCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualCost.setBounds(344, 46, 157, 25);
		frmUnibudget.getContentPane().add(lblActualCost);
		
		JLabel lblDifference = new JLabel("Difference");
		lblDifference.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDifference.setHorizontalAlignment(SwingConstants.CENTER);
		lblDifference.setBounds(511, 46, 157, 25);
		frmUnibudget.getContentPane().add(lblDifference);
		
		JButton menuBtn = new JButton("Menu");
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.main(null);
				frmUnibudget.dispose();
			}
		});
		menuBtn.setBounds(10, 227, 89, 23);
		frmUnibudget.getContentPane().add(menuBtn);
		
		final String budgetTables [] = {"Income","Housing","Entertainment","Transportation","Loans","Insurance","Savings","Food","Subscriptions","Personal"};
		
		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				try {
				   	conn = DriverManager.getConnection("jdbc:mysql://db.burawi.tech:3306/unibudget?verifyServerCertificate=false&useSSL=true", "hesho" , "cQnfD23b8tiYk!7h");				    					    
				    String sql = "UPDATE " + budgetTables[tableChoice] + " SET projectedCost = ?, actualCost = ?, difference = ? WHERE id = '" + User.id + "'AND uniChoice = '" + BudgetUniSelector.getUniIndex() + "' AND expenseType = ?";
			        PreparedStatement preparedStatement = conn.prepareStatement(sql);
			        
			        double pC = Double.valueOf(projectedCostTxt.getText());
			        double aC = Double.valueOf(actualCostTxt.getText());
			        preparedStatement.setDouble(1,pC);
			        preparedStatement.setDouble(2,aC);
			        preparedStatement.setDouble(3,diff);
			        preparedStatement.setString(4, (String) expenseCmb.getSelectedItem());

			        preparedStatement.executeUpdate(); 
				} catch (SQLException ex) {
				 	System.out.println("SQLException: " + ex.getMessage());
			   	 	System.out.println("SQLState: " + ex.getSQLState());
			    	System.out.println("VendorError: " + ex.getErrorCode());
				}
			}
		});
		saveBtn.setBounds(344, 222, 157, 32);
		frmUnibudget.getContentPane().add(saveBtn);		
		
		JButton nextBtn = new JButton("");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableChoice == 9) {
					tableChoice = -1; //To count for the addition below
				}
				tableChoice++;
				expenseUpdater(budgetTables[tableChoice]);
			}
		});
		nextBtn.setBackground(SystemColor.control);
		nextBtn.setIcon(new ImageIcon(BudgetUpdater.class.getResource("/resources/Forward Chevron.png")));
		nextBtn.setBounds(499, 222, 32, 32);
		frmUnibudget.getContentPane().add(nextBtn);
		
		JButton backBtn = new JButton("");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableChoice == 0) {
					tableChoice = 10; // To count for the subtraction below
				}
				tableChoice--;
				expenseUpdater(budgetTables[tableChoice]);
				
			}
		});
		backBtn.setBackground(SystemColor.control);
		backBtn.setIcon(new ImageIcon(BudgetUpdater.class.getResource("/resources/Back Chevron.png")));
		backBtn.setBounds(315, 222, 32, 32);
		frmUnibudget.getContentPane().add(backBtn);
		
		expenseUpdater("Income");
				
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setBounds(177, 161, 324, 25);
		frmUnibudget.getContentPane().add(lblError);
		
	}
}
