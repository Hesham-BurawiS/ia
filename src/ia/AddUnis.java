package ia;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class AddUnis {

	private JFrame frmUnibudget;

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
		frmUnibudget.setBounds(100, 100, 450, 300);
		frmUnibudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		frmUnibudget.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JLabel uniNameLbl = new JLabel("");
		uniNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		uniNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		uniNameLbl.setBounds(98, 11, 230, 26);
		panel.add(uniNameLbl);
		
		JLabel cityLbl = new JLabel("City:");
		cityLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cityLbl.setBounds(23, 60, 35, 26);
		panel.add(cityLbl);
		
		JTextField currentCity = new JTextField("");
		currentCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentCity.setBounds(55, 60, 217, 26);
		panel.add(currentCity);
		
		JLabel courseLbl = new JLabel("Course:");
		courseLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		courseLbl.setBounds(23, 97, 49, 26);
		panel.add(courseLbl);
		
		JTextField currentCourse = new JTextField("");
		currentCourse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentCourse.setBounds(74, 97, 207, 26);
		panel.add(currentCourse);
		
		JLabel lengthLbl = new JLabel("Length:");
		lengthLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lengthLbl.setBounds(23, 134, 49, 26);
		panel.add(lengthLbl);
		
		JTextField currentLength = new JTextField("");
		currentLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentLength.setBounds(74, 134, 207, 26);
		panel.add(currentLength);
		
		JLabel lblCost = new JLabel("Cost:");
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCost.setBounds(23, 171, 49, 26);
		panel.add(lblCost);
		
		JTextField currentCost = new JTextField("");
		currentCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentCost.setBounds(65, 171, 207, 26);
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
		
		JButton nextBtn = new JButton("Next");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Fill
			}
		});
		nextBtn.setBounds(335, 227, 89, 23);
		panel.add(nextBtn);
		
		JButton previousBtn = new JButton("Previous");
		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Fill
			}
		});
		previousBtn.setBounds(239, 227, 89, 23);
		panel.add(previousBtn);
	}

}
