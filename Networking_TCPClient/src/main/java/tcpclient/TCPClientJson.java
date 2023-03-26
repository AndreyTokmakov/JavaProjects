//============================================================================
//Name        : TCPClientJson.java
//Created on  : Sep 01, 2017
//Author      : Tokmakov Andrey
//Version     : 1.0
//Copyright   : Your copyright notice
//Description : TCP client
//============================================================================

package tcpclient;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;

/** TCPClient class definition : **/
public class TCPClientJson {
	/** **/
	private BufferedReader bufferedReader = null;
	/** **/
	private Socket socket = null;
	/** **/
	private OutputStream outputStream = null;
	/** **/
	private BufferedReader socketReader = null;
	/** **/
	private boolean connected = false;
	/** **/
	private boolean run = true;
	
	/** **/
	public TCPClientJson() {
		// TODO Auto-generated constructor stub
	}
	
	public void Run() {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	        while (true == run) {
	        	System.out.print("> ");
	            String command = bufferedReader.readLine();
	            String response = HandleCommand(command);
	            System.out.println(response);
	        }
		} catch (IOException e) {
	            e.printStackTrace();
	    } finally {
	    	if (bufferedReader != null) {
	    		try {
	    			bufferedReader.close();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    }
	}
	
	/** Handle command : **/
	protected String HandleCommand(String inputText)  {
		String command = "", params = "";
		
		int pos = inputText.indexOf(" ");
		if (-1 != pos) {
			command = inputText.substring(0, pos);
			params =  inputText.substring(pos + 1, inputText.length());
		} else {
			command = inputText;
		}
		
		String result = "";
		switch (command) {
			case "connect" : case "C" : result = HandleConnect(command, params); break;
			case "quit" : case "Q" : result = HandleQuit(command); break;
			case "authorize" : case "A": result = HandleAuthorizationCommand(command, params); break;
			case "disconnect" : case "D" : result = HandleDisconnect(command); break;
			case "test" : result = HandleTestCommand(command); break;
			default : result = "Handle command error : " + command; break;
		} return result;
	}
	
	/** BuildJsonMessage : **/
	@SuppressWarnings("unchecked")
	protected JSONObject BuildAuthorizatioJsonMessage(String params) {
		int pos = params.indexOf("@");
		String login = params.substring(0, pos);
		String password = params.substring(pos + 1, params.length());
		
		JSONObject json = new JSONObject();
		json.put("type", "AuthorizationRequest");
		json.put("login", login);
		json.put("password", password);
        return json;
	}
	
	/** BuildTestJsonMessage : **/
	protected JSONObject BuildTestJsonMessage() {
		JSONObject json = new JSONObject();
		json.put("type",     "test");
        return json;
	}
	
	/** Handle authorization command : **/
	protected String HandleAuthorizationCommand(String command, String params)  {
		return SendCommandToServer(BuildAuthorizatioJsonMessage(params).toString());
	}	
	
	/** Handle test command : **/
	protected String HandleTestCommand(String command)  {
		String cmd = BuildTestJsonMessage().toString();
		return SendCommandToServer(cmd);
	}	

	protected String SendCommandToServer(String command) {
		String result = "";
		try {
			outputStream.write(command.getBytes("iso8859_1"));
			result = socketReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** Handle connect command : **/
	protected String HandleConnect(String command, String params)  {
		
		
		if (params.isEmpty())
			params = "127.0.0.1:52525";
		System.out.println(params);
		
		if (true == connected)
			return "Already connected";
		if (false == params.contains(":"))
			return "Incorrect syntat. Expected : connect IP:PORT";

		String result = "";
		String tmp[] = params.split(":");
		
		String ip = tmp[0];
		int port  = Integer.valueOf(tmp[1]);
		
		System.out.println("Connecting to " + ip + ":" + port + "...");
		
		this.connected = this.Connect(ip, port);
		return result;
	}
	
	/** Handle disconnect command : **/
	protected String HandleDisconnect(String command)  {
		String result = "";
		if (true == this.Disconnect())
			this.connected = false;
		return result;
	}
	
	/** Handle quit command : **/
	protected String HandleQuit(String command)  {
		if (true == connected)
			return "Clienst still connected. Use command 'disconnect' first";
		
		String result = "Good bye";
		this.run = false;
		return result;
	}
	
	/** Connect : **/
	private boolean Connect(String ip, int port) {
		try {
			this.socket = new Socket(ip, port);
			this.outputStream = socket.getOutputStream();
			socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/** Disconnect : **/
	private boolean Disconnect() {
		if (true == this.socket.isConnected()) {
			try {
				this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}	
		}
		return true;
	}

	/** **/
	public static void main(String[] args) {
		TCPClientJson client = new TCPClientJson();
		client.Run();
	}
}
