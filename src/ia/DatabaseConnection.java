package ia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	public static Object rs;
	public static Object conn;
	public static Object Statement;
	public static Object ResultSet;


	public DatabaseConnection() {
		// TODO Auto-generated constructor stub
		// Database Connection to verify user information
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection("jdbc:mysql://db.burawi.tech:3306/unibudget?verifyServerCertificate=false&useSSL=true", "hesho" , "cQnfD23b8tiYk!7h");
		    Statement stmt = null;
		    ResultSet rs = null;
		    

	} catch (SQLException ex) {
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
};
	

	public static void main(String[] args, String sql) {
		// TODO Auto-generated method stub
		
};

	}


