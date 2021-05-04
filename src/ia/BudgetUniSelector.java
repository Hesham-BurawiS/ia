package ia;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BudgetUniSelector {

	private JFrame frmUniBudget;
	private JComboBox comboBox;
	private static int uniIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BudgetUniSelector window = new BudgetUniSelector();
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
	public BudgetUniSelector() {
		initialize();
	}
	
	public static int getUniIndex() {
		return uniIndex;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUniBudget = new JFrame();
		frmUniBudget.setTitle("UniBudget");
		frmUniBudget.setBounds(100, 100, 450, 180);
		frmUniBudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUniBudget.getContentPane().setLayout(null);
		
		JLabel titleLbl = new JLabel("Budget Planner");
		titleLbl.setBounds(10, 11, 424, 22);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		frmUniBudget.getContentPane().add(titleLbl);
		
		JLabel uniSelLbl = new JLabel("View budget for");
		uniSelLbl.setBounds(20, 44, 103, 19);
		uniSelLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmUniBudget.getContentPane().add(uniSelLbl);

		comboBox = new JComboBox();
		comboBox.setBounds(119, 44, 305, 22);
		comboBox.setModel(new DefaultComboBoxModel(User.arrayOfUnis));
		frmUniBudget.getContentPane().add(comboBox);
		
		JButton menuBtn = new JButton("Menu");
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUniBudget.dispose();
				MainMenu.main(null);
			}
		});
		menuBtn.setBounds(10, 107, 89, 23);
		frmUniBudget.getContentPane().add(menuBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BudgetUpdater.main(null);
				frmUniBudget.dispose();
			}
		});
		updateBtn.setBounds(335, 107, 89, 23);
		frmUniBudget.getContentPane().add(updateBtn);
		
		JButton viewBtn = new JButton("View");
		viewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uniIndex = comboBox.getSelectedIndex()+1;
				BudgetViewer.main(null);
				frmUniBudget.dispose();
			}
		});
		viewBtn.setBounds(236, 107, 89, 23);
		frmUniBudget.getContentPane().add(viewBtn);
	}


}
