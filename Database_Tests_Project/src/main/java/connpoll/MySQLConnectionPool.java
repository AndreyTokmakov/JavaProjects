//============================================================================
//Name        : ConnectionPoolTests.java
//Created on  : Aug 24, 2017
//Author      : Tokmakov Andrey
//Version     :
//Copyright   : Your copyright notice
//Description :
//============================================================================

package connpoll;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.util.Date;

/** @class ConnectionPoolTests : */
public class MySQLConnectionPool {
	private static MySQLConnectionPool __instance = null;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private static final String connectString = "jdbc:mysql://10.134.13.225:3306/cl";
	private static final String dbUserLogin = "atokmakov";
	private static final String dbUserPassword = "ziudjaga";
	private static final int CONN_POOL_SIZE = 5;
	
	private BasicDataSource basicDataSource = new BasicDataSource();
	
	/**  **/
	protected MySQLConnectionPool() {
		// Set database driver name
		basicDataSource.setDriverClassName(driverClassName);
		// Set database url
		basicDataSource.setUrl(connectString);
		// Set database user
		basicDataSource.setUsername(dbUserLogin);
		// Set database password
		basicDataSource.setPassword(dbUserPassword);
		// Set the initial number of connections that are created when the pool is started. 
		basicDataSource.setInitialSize(CONN_POOL_SIZE);
		// Set the maximum number of active connections that can be allocated 
		// from this pool at the same time, or negative for no limit.
		basicDataSource.setMaxTotal(10);
		// Set the maximum number of connections that can remain idle in the pool,
		// without extra ones being released, or negative for no limit.
		basicDataSource.setMaxIdle(10);
	}

	/**  **/  
	public static MySQLConnectionPool getInstance() {
		if (null == __instance) {
			synchronized (MySQLConnectionPool.class) {
				if (null == __instance) {
					__instance = new MySQLConnectionPool();
				}
			}
		}
		return __instance;
	}

	public BasicDataSource getBasicDataSource() {
		return basicDataSource;
	}

	public void setBasicDataSource(BasicDataSource bds) {
		this.basicDataSource = bds;
	}

	/**  **/
	public static void main(String[] args) {
		for (int i = 0; i < 25; i++) {
			 new Client(i).start();
			 /*try {
				Thread.currentThread().sleep(90);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
}


/** Client class : **/
class Client extends Thread {

	private int id = 0; 
	
	public Client(int id) {
		this.id = id;
	}

	@SuppressWarnings("static-access")
	public void run() {
		while (true) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			try {
				BasicDataSource bds = MySQLConnectionPool.getInstance().getBasicDataSource();
				connection = bds.getConnection();
				statement = connection.prepareStatement("select * from cl.anomaly_groups;");
				resultSet = statement.executeQuery();
				
				int count = 0;
				while (resultSet.next()) {
					/*System.out.println(resultSet.getInt(1) + " | " + resultSet.getInt(2) + " | " + 
									   resultSet.getString(3) + "  |  " + resultSet.getString(4));*/
					count++;
				}
				System.out.println("Client " + this.id + " : " + count + ", " + new Date());
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (resultSet != null)
						resultSet.close();
					if (statement != null)
						statement.close();
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
