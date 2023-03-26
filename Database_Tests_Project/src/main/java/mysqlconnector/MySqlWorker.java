//============================================================================
// Name        : MySqlWorker.java
// Created on  : Aug 24, 2017
// Author      : Tokmakov Andrey
// Version     :
// Copyright   : Your copyright notice
// Description :
//============================================================================

package mysqlconnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlWorker {
	/**  **/
	public MySqlWorker() {
		// TODO Auto-generated constructor stub
	}

	/** main :*/
	public static void main(String[] args) {
		DatabaseConnection dbConnection = DatabaseConnection.getInstance();
		if (false == dbConnection.OpenConnection())
			return;
		try {
			Connection connection = dbConnection.getConnection();
			String sql = "select * from newportal.UserRole;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
	
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to create PreparedStatement or executeQuery");
		}
		dbConnection.CloseConnection();
	}
}
