package socket_client_server;

import java.net.*;
import java.io.*;

public class SocketServer
{
  public void runWithTimeout(int timeout)
  {
	try {
		int serverPort = 52525;
		ServerSocket serverSocket = new ServerSocket(serverPort);
		serverSocket.setSoTimeout(timeout);

		while(true) {
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "..."); 

			Socket server = serverSocket.accept();
			System.out.println("Just connected to " + server.getRemoteSocketAddress()); 

			PrintWriter toClient = new PrintWriter(server.getOutputStream(),true);
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(server.getInputStream()));
			String line = fromClient.readLine();
			System.out.println("Server received: " + line); 
			toClient.println("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!"); 
		}
	}
	catch(IOException e){
		e.printStackTrace();
	}
  }
	
  public static void main(String[] args) {
	  SocketServer srv = new SocketServer();
		srv.runWithTimeout(10000);
  }
}