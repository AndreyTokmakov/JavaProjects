//============================================================================
// Name        : DatabaseConnection.java
// Created on  : Dec 01, 2015
// Author      : Tokmakov Andrey
// Version     :
// Copyright   : Your copyright notice
// Description :
//============================================================================

package mysqlconnector;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.SQLException;

/** DatabaseConnection class declaration: **/
public class DatabaseConnection {
    /** Singleton implementation : **/
    private static volatile DatabaseConnection __instance;
    /** SQL Connection: **/
    private Connection connection = null;
    /** JDBC Driver load status: **/
    private boolean isDriverLoaded = false;
    /** Connections status: **/
    private boolean isConnected = false;
    
    private String dbHost = "";
    private int    dbPort = 0;
    private String dbUserLogin = "";
    private String dbUserPassword = "";
    
    /** DatabaseConnection class constructor: **/
    protected DatabaseConnection() {
    	isDriverLoaded = false;
    	isConnected = false;
    	connection = null;
    	
    	dbHost = "10.134.13.225";
    	dbPort = 3306;
    	dbUserLogin = "portal_user";
    	dbUserPassword = "1q2w3e4r";
    }   
    /** getInstance: **/
    public static DatabaseConnection getInstance() {
        if (null == __instance) {
            synchronized (DatabaseConnection.class) {
                if (null == __instance) {
                    __instance = new DatabaseConnection();
                }
            }
        }
        return __instance;
    }
    protected String getConnectionString() {
    	return "jdbc:mysql://" + dbHost + ":" + dbPort;
    }
    public boolean OpenConnection()  {
    	if (false == isDriverLoaded) {
    		try {
    			Class.forName("com.mysql.jdbc.Driver");
    			isDriverLoaded = true;
    		} catch (ClassNotFoundException e) {
    			System.out.println("Failed to load com.mysql.jdbc.Driver");
    			e.printStackTrace();
    			return false;
    		}	
    	}
    	if (false == isConnected) {
	    	try {
				connection = DriverManager.getConnection(getConnectionString(), dbUserLogin, dbUserPassword);
				isConnected = true;
			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				isConnected = false;
			}
	    	return isConnected;
    	}
    	return true;
    }
    public boolean isConnected()  {
		return isConnected;
    }
    public boolean CloseConnection()  {
    	try {
			connection.close();
			return true;
		} 
    	catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
    public Connection getConnection()  {
    	return this.connection;
    }
	/** @return the dbHost */
	public String getDbHost() {
		return dbHost;
	}
	/** @param dbHost the dbHost to set **/
	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}
	/** @return the dbPortt **/
	public int getDbPort() {
		return dbPort;
	}
	/** @param dbPort the dbPort to set **/
	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
	}
	/** @return the dbUserLogint **/
	public String getDbUserLogin() {
		return dbUserLogin;
	}
	/** @param dbUserLogin the dbUserLogin to set **/
	public void setDbUserLogin(String dbUserLogin) {
		this.dbUserLogin = dbUserLogin;
	}
	/** @return the dbUserPassword **/
	public String getDbUsetPassword() {
		return dbUserPassword;
	}
	/** @param dbUserPassword the dbUsetPassword to set **/
	public void setDbUsetPassword(String dbUserPassword) {
		this.dbUserPassword = dbUserPassword;
	}

}
