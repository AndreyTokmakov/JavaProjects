//============================================================================
// Name        : TCPClient.py
// Created on  : February 19, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : TCP servers class
//============================================================================


package tcp_tests;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/** TCPClient class : **/
public class TCPClient {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		TCPClient tester = new TCPClient();
		tester.Connect();
		// tester.SendRequest_ReadResponse();
		// tester.Get_DomainName("google.com");
	}
	
	protected void Connect() throws IOException {
		String sendBuffer = "TEST";
		Socket clientSocket = new Socket("127.0.0.1", 52525);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		outToServer.writeBytes(sendBuffer);
		clientSocket.close();
	}
	
	protected void SendRequest_ReadResponse() throws IOException {
		String host = "127.0.0.1";
		//String host = "data.browser.mail.ru";
		//String host = "100.114.17.254";
		String sendBuffer = "test";
		
		Socket clientSocket = new Socket(host, 52525);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		outToServer.writeBytes(sendBuffer +"\n");

		String line;
		while ((line = fromServer.readLine()) != null) {
			System.out.println(line);
		}

		//System.out.println(response);
		clientSocket.close();
	}
	

    public void Get_DomainName(String domainName) {
        String hostname = "whois.internic.net";
        int port = 43;
 
        try (Socket socket = new Socket(hostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(domainName);
            
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
