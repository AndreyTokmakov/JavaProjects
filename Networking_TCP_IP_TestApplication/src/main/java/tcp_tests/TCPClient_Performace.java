/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* TCPClient_Performace class
*
* @name    : TCPClient_Performace.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 28, 2020
****************************************************************************/ 

package tcp_tests;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

class TCPClientWorker {
	/** TCP Server port: **/
	private final static int SERVER_PORT = 52525;

	/** TCP Server host name/address:**/
	private final static String SERVER_HOST = "127.0.0.1";

	public void SimpleTest() throws InterruptedException {
		long start = System.nanoTime();

		TimeUnit.SECONDS.sleep(1);

		long end = System.nanoTime();

		long nsDuration = end - start;
		long mksDuration = TimeUnit.NANOSECONDS.toMicros(nsDuration);
		long msDuration = TimeUnit.NANOSECONDS.toMillis(nsDuration);

		System.out.println(String.format("Slept for %d nano  seconds.", nsDuration));
		System.out.println(String.format("Slept for %d micro seconds.", mksDuration));
		System.out.println(String.format("Slept for %d milli seconds.", msDuration));
	}
	
	public void RunTest() {
		StringBuilder response = new StringBuilder();
		String sendBuffer = "test";
		
		try (Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT)) {
			DataOutputStream writeStream = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader readerStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			while (true) {
				/** Send data: **/
				writeStream.writeBytes(sendBuffer +"\n");
				
				/** Read response: **/
				String line;
				while ((line = readerStream.readLine()) != null) {
					response.append(line + "\n");
					// TODO: break hack!
					if (true == line.contains("</body></html>"))
						break;
	            }
				System.out.println("Response:\n" + response.toString());
				
				TimeUnit.MILLISECONDS.sleep(10);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void OneRequest_PerConnection() {
		String sendBuffer = "Test_12345_Test1111\n";
		String receivedBuffer  = "";
		final int CONN_COUNT = 1;

		/** Start measure time: **/
		long start = System.nanoTime();

		for (int i = 0; i < CONN_COUNT; ++i) {
			try (final Socket socket = new Socket(SERVER_HOST, SERVER_PORT))
			{
				DataOutputStream writeStream = new DataOutputStream(socket.getOutputStream());
				BufferedReader readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				writeStream.writeBytes(sendBuffer);
				receivedBuffer = readerStream.readLine();
				// System.out.println("FROM SERVER: " + receivedBuffer);

				writeStream.close();
				readerStream.close();
			} catch (final IOException exc) {
				System.err.println(exc.getMessage());
			}
		}

		/** Stop measure time: **/
		long end = System.nanoTime();
		long msDuration = TimeUnit.MICROSECONDS.toMillis(end - start);

		System.out.println("Done! Time elapsed: " + msDuration);
	}

	private void sleep(int timeout) {
		try {
			TimeUnit.MILLISECONDS.sleep(timeout);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
}

public class TCPClient_Performace {
	/** **/
	private final static TCPClientWorker client = new TCPClientWorker();

	public static void main(String[] args) throws InterruptedException {

		// client.SimpleTest();
		// client.RunTest();
		 client.OneRequest_PerConnection();
	}
}
