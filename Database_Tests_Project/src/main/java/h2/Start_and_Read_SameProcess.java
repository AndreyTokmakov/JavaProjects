/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Start_and_Read_SameProcess.java class
*
* @name    : Start_and_Read_SameProcess.java
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

public class Start_and_Read_SameProcess {
	private final static String url = "jdbc:h2:mem:test";
	private final static String user = "tester";
	private final static String password = "12345";
	
	private static Connection mainConnection = null;
	
	public static void Start() throws SQLException, ClassNotFoundException {
		mainConnection = DriverManager.getConnection(url, user,  password);
	
		Statement statement = mainConnection.createStatement();
		statement.execute("CREATE TABLE unit_tests (id INT, name VARCHAR(256));");
		statement.execute("INSERT INTO unit_tests VALUES (1, 'test1');");
		statement.execute("INSERT INTO unit_tests VALUES (2, 'test2');");
		statement.close();

		// IF WE CLOSE IT - BASE WILL BE DESTROYED!!!
		// mainConnection.close();
	}
	
	public static void Read() throws SQLException {
		final String query = "SELECT * FROM unit_tests";
		
		try (Connection connetion = DriverManager.getConnection(url, user, password);
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
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{
		Start();
		Read();
		
		mainConnection.close();
	}
}
