package ia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import ia.DatabaseConnection.*;
public class User {

	protected static int id;
	protected static String firstName;
	protected static String lastName;
	protected static String email;
	protected static String choice1;
	protected static String choice2;
	protected static String choice3;
	protected static String choice4;
	protected static String choice5;
	protected static int totalChoices;

	public User() {
		// TODO Auto-generated constructor stub
		int id;
		String firstName;
		String lastName;
		String email;
		String choice1;
		String choice2;
		String choice3;
		String choice4;
		String choice5;
		int totalChoices;

		
	}
	public static String[] choice(String table) throws SQLException {
		String[] choiceArr = new String [8];
		String sql = "SELECT * FROM " + table + " WHERE id = " + id; //TODO remove this when in prod
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection("jdbc:mysql://db.burawi.tech:3306/unibudget?verifyServerCertificate=false&useSSL=true", "hesho" , "cQnfD23b8tiYk!7h");
		    Statement stmt = null;
		    ResultSet rs = null;
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery(sql);
			while (rs.next()) {
				for (int i = 0; i < choiceArr.length; i++) {
					choiceArr[i] = rs.getString(i+1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return choiceArr;
		
	}

		
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String [] arr = choice("choice2");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
