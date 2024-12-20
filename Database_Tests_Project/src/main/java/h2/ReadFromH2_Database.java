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

public class ReadFromH2_Database
{
	private final static String local_in_memory_url = "jdbc:h2:mem:test";
	private final static String remote_H2_url = "jdbc:h2:tcp://localhost:9092/mem:test";
	private final static String user = "tester";
	private final static String password = "12345";


	public static void Read() throws SQLException
	{
		final String query = "SELECT * FROM unit_tests";
		
		try (Connection connection = DriverManager.getConnection(local_in_memory_url, user, password);
			Statement statement = connection.createStatement()) {

			ResultSet result;
			result = statement.executeQuery(query);
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				System.out.println(id + ", " + name);
			}
		} 
		catch (SQLException exc) {
			System.out.println(exc.getMessage());
		}
	}

	public static void showDatabases() throws SQLException
	{
		final String query = "show databases;";

		try (Connection connection = DriverManager.getConnection(local_in_memory_url, user, password);
			 Statement statement = connection.createStatement())
		{
			ResultSet result = statement.executeQuery(query);
			System.out.println(result.getString(1));
		}
		catch (SQLException exc) {
			System.err.println(exc.getMessage());
		}
	}
	
	public static void main(String[] args) 
			throws SQLException, ClassNotFoundException 
	{
		showDatabases();
		// Read();
	}
}
