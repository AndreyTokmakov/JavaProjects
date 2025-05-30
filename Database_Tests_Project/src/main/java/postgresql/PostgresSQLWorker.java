/**
 * 
 */
package postgresql;

// Import required packages
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class PostgresSQLWorker {
	//  Database credentials
	static final String DB_URL = "jdbc:postgresql://0.0.0.0:5433/atc-backend";
	static final String USER = "postgres";
	static final String PASS = "postgres";
	 
	public static void main(String[] argv) {
		System.out.println("Testing connection to PostgreSQL JDBC");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
			e.printStackTrace();
			return;
		}
	 
		System.out.println("PostgreSQL JDBC Driver successfully connected");
		Connection connection = null;
	 
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
			return;
		}
		
		if (connection != null) {
			System.out.println("You successfully connected to database now");
		} else {
			System.out.println("Failed to make connection to database");
		}
		
		
		String sql = "select * from public.users;";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}