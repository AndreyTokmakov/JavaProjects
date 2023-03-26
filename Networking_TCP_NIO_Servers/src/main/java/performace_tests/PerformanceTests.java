package performace_tests;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PerformanceTests {
	/** Server IP address: **/
	private static final String host = "127.0.0.1";
	/** Server port :**/
	private static final int port = 52525;
	
	protected void SendRequest_ReadResponse() throws IOException {
		String sendBuffer = "test\n", receivedBuffer  = "";
		Socket clientSocket = new Socket(host, port);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		//long startTime = System.currentTimeMillis();
		long rCount = 10;
		for (int i = 0; i < rCount; i++) {
			outToServer.writeBytes(sendBuffer);
			receivedBuffer = inFromServer.readLine();
			System.out.println("Response : " + receivedBuffer);
		}
		//long endTime = System.currentTimeMillis();
		
		//System.out.println("Job done. Elapsed time : " + (endTime - startTime) + ", Rate : "  + (rCount * 1000)/ (endTime - startTime) + " rps");
		clientSocket.close();
	}
	
	protected void SendRequest_ReadResponse2() throws IOException {
		String sendBuffer = "test";
		long rCount = 1;
		for (int i = 0; i < rCount; i++) {
			Socket clientSocket = new Socket(host, port);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeBytes(sendBuffer);
			clientSocket.close();
		}
	}	
	
	/** MAIN: **/
	public static void main(String[] args) throws IOException {
		PerformanceTests tester = new PerformanceTests();
		tester.SendRequest_ReadResponse();
		// tester.SendRequest_ReadResponse2();
	}
}
