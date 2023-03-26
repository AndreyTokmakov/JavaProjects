/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* JH2Server.java class
*
* @name    : JH2Server.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 12, 2020
****************************************************************************/

package h2;


import org.h2.tools.Server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StandAlone_H2_Mem_Database {
	
	private final static String url = "jdbc:h2:mem:test";
	private final static String user = "tester";
	private final static String password = "12345";
	
	public static void Start() throws SQLException {
		
		Connection connetion = DriverManager.getConnection(url, user,  password);
		
		final String[] args = new String[] {"-tcpPort", "9092","-tcpAllowOthers"};
		Server server = Server.createTcpServer(args).start();
		
		System.out.println("Status: " + server.getStatus());
		System.out.println("URL   : " + server.getURL());
		System.out.println("Port  : " + server.getPort());
		
		Statement statement = connetion.createStatement();
		statement.execute("CREATE TABLE unit_tests (id INT, name VARCHAR(256));");
		statement.execute("INSERT INTO unit_tests VALUES (1, 'test1');");
		statement.execute("INSERT INTO unit_tests VALUES (2, 'test2');");
		statement.close();
	}
	
	public static void main(String[] args) 
			throws SQLException, ClassNotFoundException {
		Start();
	}
}