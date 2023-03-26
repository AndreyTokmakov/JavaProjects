package oracle;

import java.sql.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.dbcp2.BasicDataSource;

class ConnectionPool {
	/** Database driver instance: **/
	private static final Driver DB_DRIVER = new oracle.jdbc.driver.OracleDriver();
	
	/** Database connection string: **/
	private static final String CONNECTION_STRING = "jdbc:oracle:thin:@//ptf01-t02-adb01.lab.nordigy.ru:1521/adb011";
	
	/** Database user name: **/
	private static final String DB_USER_NAME = "zportal";
	
	/** Database user password: **/
	private static final String DB_USER_PASSWORD = "zportal";
	
	/** Number of connection that are created when the pool is started. **/
	private static final int CONN_POOL_INITIAL_SIZE = 5;
	
	/** Maximum number of active connections. **/
	private static final int CONN_POOL_MAXIMUM_SIZE = 15;
	
	/** Maximum idle connections in pool. **/
	private static final int MAX_IDLE_SIZE = 10;
	
	/** Basic DataSource instance: **/
	private final BasicDataSource basicDataSource = new BasicDataSource();
	
	/** Initialization-on-demand keeper/guard: **/
	private static final class PoolInstanceGuard {
		private static final ConnectionPool INSTANCE = new ConnectionPool();
	}
	
	/**  **/
	private ConnectionPool() {
		// Set database driver name
		basicDataSource.setDriver(DB_DRIVER);
		
		// Set database url
		basicDataSource.setUrl(CONNECTION_STRING);
		
		// Set database user
		basicDataSource.setUsername(DB_USER_NAME);
		
		// Set database password
		basicDataSource.setPassword(DB_USER_PASSWORD);
		
		// Set the initial number of connections that are created when the pool is started. 
		basicDataSource.setInitialSize(CONN_POOL_INITIAL_SIZE);
		
		// Set the maximum number of active connections that can be allocated 
		// from this pool at the same time, or negative for no limit.
		basicDataSource.setMaxTotal(CONN_POOL_MAXIMUM_SIZE);
		
		// Set the maximum number of connections that can remain idle in the pool,
		// without extra ones being released, or negative for no limit.
		basicDataSource.setMaxIdle(MAX_IDLE_SIZE);
	}

	/**  **/  
	public static ConnectionPool getInstance() {
		return PoolInstanceGuard.INSTANCE;
	}

	public Connection getConnection() throws SQLException {
		return basicDataSource.getConnection();
	}
}


class OracleWorker {
	/** **/
	private final static String ADB_1682_URL = "jdbc:oracle:thin:@//ptf01-t03-adb01.lab.nordigy.ru:1521/ptfdev3ams_db";
	/** **/
	private final static String ADB_1601_URL = "jdbc:oracle:thin:@//ptf01-t02-adb01.lab.nordigy.ru:1521/adb011";
	
	/** **/
	private final static String USER = "zportal";
	/** **/
	private final static String PASSWORD = "zportal";
	/** **/
	private static boolean isDriverRegistered = false;
	
	private final ConnectionPool connPool = ConnectionPool.getInstance();
	
	public OracleWorker() {
		// registerDriver();
	}
	
	private boolean registerDriver() {
		try { 
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			OracleWorker.isDriverRegistered = true;
		} catch (final SQLException exc) {
			System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
			System.err.println(exc.getMessage());
		}
		return OracleWorker.isDriverRegistered;
	}
	
	private void sleep(int msTimeout) {
		try {
			TimeUnit.MILLISECONDS.sleep(msTimeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public void test_ConnectionPool() {
		//registerDriver();
		
		for(int i = 0; i < 20; ++i) {
			// selectFrom();
			selectFrom_Pool();
			sleep(2500);
		}
	}
	
	public void selectFrom_Pool() {
		final String sql = "select USERID, MAILBOXID, PIN, EXTENSIONTYPE, MAILBOXSTATE from MAILBOXES "
				+ " where USERID = 400167368116 and MAILBOXID = 400167370116";
		
		try (Connection connection = connPool.getConnection();
			 Statement statement = connection.createStatement()) {
			
			ResultSet resultSet = statement.executeQuery(sql);
			int count = 0;
			while (resultSet.next() && 20 > count++) {
			     System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2) + " | " + 
			    		 		    resultSet.getString(3) + " | " + resultSet.getString(4) + " | ");
			}
		} catch (Exception exc) { 
			System.err.println(exc.getMessage());
		} 
	}
	
	public void selectFrom() {
		if (false == isDriverRegistered)
			return;
		
		final String sql = "select USERID, MAILBOXID, PIN, EXTENSIONTYPE, MAILBOXSTATE from MAILBOXES "
				+ " where USERID = 400167368116 and MAILBOXID = 400167370116";
		
		try (Connection connection = DriverManager.getConnection (ADB_1601_URL, USER, PASSWORD);
			 Statement statement = connection.createStatement()) {
			
			ResultSet resultSet = statement.executeQuery(sql);
			int count = 0;
			while (resultSet.next() && 20 > count++) {
			     System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2) + " | " + 
			    		 		    resultSet.getString(3) + " | " + resultSet.getString(4) + " | ");
			}
		} catch (Exception exc) { 
			System.err.println(exc.getMessage());
		} 
	}
	
	public void update() {
		if (false == isDriverRegistered)
			return;
		
		final String sql = "update ZPORTAL.MAILBOXES set EXTENSIONTYPE = 2 where USERID = 400167368116 and MAILBOXID = 400167370116";
		
		try (Connection connection = DriverManager.getConnection (ADB_1601_URL, USER, PASSWORD);
			 Statement statement = connection.createStatement()) {
			
			boolean result = statement.execute(sql);
			System.out.println(result);
		} catch (Exception exc) { 
			System.err.println(exc.getMessage());
		} 
	}
	
	public void callProcedure() {
		if (false == isDriverRegistered)
			return;
		
		final String addEvent = "call ADT_EVENT (?, ?, ?, ?, ?, ?)";
		try (Connection connection = DriverManager.getConnection (ADB_1601_URL, USER, PASSWORD);
			 PreparedStatement statement = connection.prepareStatement(addEvent)) {
			
			/*
			statement.setInt(1, 28);
			statement.setString(2, "100392243957222");
			statement.setString(3, "1125840614160333");
			statement.setInt(4, 1);
			statement.setString(5, null);
			statement.setString(6, null);
			*/
			statement.setInt(1, 17);
			statement.setString(2, "665187127");
			statement.setString(3, "665187127");
			statement.setInt(4, 19);
			statement.setString(5, null);
			statement.setString(6, null);
			
			statement.execute();
	
		} catch (Exception exc) { 
			System.err.println(exc.getMessage());
		} 
	}
}


public class Oracle_Tests {
	/** Oracle worker. **/
	private final static OracleWorker oraWorker = new OracleWorker();

	public static void main(String[] args)
	{
		// oraWorker.selectFrom();
		// oraWorker.selectFrom_Pool();
		// oraWorker.update();
		// oraWorker.callProcedure();
		
		oraWorker.test_ConnectionPool();
	}
}
