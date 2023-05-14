package servers;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class SocketServer
{
	private static final int PORT = 52525;

  	public void run()
  	{
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server is listening on port " + PORT);
			while (true) {
				final Socket clientSocket = serverSocket.accept();
				System.out.println("New client connected: " + clientSocket.getRemoteSocketAddress());

				try (final OutputStream output = clientSocket.getOutputStream();
					 final PrintWriter writer = new PrintWriter(output, true)) {
					writer.println(new Date());
				}
			}

		} catch (IOException ex) {
			System.err.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
  	}

	public static void main(String[] args) {
		SocketServer server = new SocketServer();
		server.run();
	}
}