package ia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewUnis {

	private JFrame frmUnibudget;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUnis window = new ViewUnis();
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
	public ViewUnis() {
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
		
		
		JLabel uniNameLbl = new JLabel("University of Cambridge");
		uniNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		uniNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		uniNameLbl.setBounds(98, 11, 230, 26);
		panel.add(uniNameLbl);
		
		JLabel cityLbl = new JLabel("City:");
		cityLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cityLbl.setBounds(23, 60, 35, 26);
		panel.add(cityLbl);
		
		JLabel currentCity = new JLabel("Cambridge");
		currentCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentCity.setBounds(55, 60, 217, 26);
		panel.add(currentCity);
		
		JLabel courseLbl = new JLabel("Course:");
		courseLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		courseLbl.setBounds(23, 97, 49, 26);
		panel.add(courseLbl);
		
		JLabel currentCourse = new JLabel("Computer Science");
		currentCourse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentCourse.setBounds(74, 97, 207, 26);
		panel.add(currentCourse);
		
		JLabel lengthLbl = new JLabel("Length:");
		lengthLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lengthLbl.setBounds(23, 134, 49, 26);
		panel.add(lengthLbl);
		
		JLabel currentLength = new JLabel("3 years");
		currentLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentLength.setBounds(74, 134, 207, 26);
		panel.add(currentLength);
		
		JLabel lblCost = new JLabel("Cost:");
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCost.setBounds(23, 171, 49, 26);
		panel.add(lblCost);
		
		JLabel currentCost = new JLabel("$9,250");
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
		nextBtn.setBounds(335, 227, 89, 23);
		panel.add(nextBtn);
		
		JButton previousBtn = new JButton("Previous");
		previousBtn.setBounds(239, 227, 89, 23);
		panel.add(previousBtn);
	}
}
