/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ReadFromH2_Database.java class
*
* @name    : ReadFromH2_Database.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 27, 2020
****************************************************************************/

package h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadFromH2_Database {
	private final static String same_process_url = "jdbc:h2:mem:test";
	private final static String remote_H2_url = "jdbc:h2:tcp://localhost:9092/mem:test";
	private final static String user = "tester";
	private final static String password = "12345";


	public static void Read() throws SQLException {
		final String query = "SELECT * FROM unit_tests";
		
		try (Connection connetion = DriverManager.getConnection(remote_H2_url, user, password);
			Statement statement = connetion.createStatement()) {

			ResultSet result;
			result = statement.executeQuery(query);
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				System.out.println(id + ", " + name);
			}
			statement.close();
			connetion.close();
		} 
		catch (SQLException exc) {
			// Logger logger = Logger.getLogger(JH2Server.class.getName());
			// logger.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println(exc.getMessage());
		}
	}
	
	public static void main(String[] args) 
			throws SQLException, ClassNotFoundException 
	{
		Read();
	}
}
