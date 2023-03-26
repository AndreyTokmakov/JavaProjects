package nio_tcp_servers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TcpAsyncServer {
	 
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		// Selector: multiplexor of SelectableChannel objects
		Selector selector = Selector.open(); // selector is open here
		
		// ServerSocketChannel: selectable channel for stream-oriented listening sockets
		ServerSocketChannel socketChannel = ServerSocketChannel.open();
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 52525);
	 
		// Binds the channel's socket to a local address and configures the socket to listen for connections
		socketChannel.bind(address);
	 
		// Adjusts this channel's blocking mode.
		socketChannel.configureBlocking(false);
	 
		int ops = socketChannel.validOps();
		SelectionKey selectKy = socketChannel.register(selector, ops, null);
	 
		// Infinite loop.. Keep server running
		while (true) {
			log("i'm a server and i'm waiting for new connection and buffer select...");
			// Selects a set of keys whose corresponding channels are ready for I/O operations
			selector.select();
	 
			// token representing the registration of a SelectableChannel with a Selector
			Set<SelectionKey> crunchifyKeys = selector.selectedKeys();
			Iterator<SelectionKey> crunchifyIterator = crunchifyKeys.iterator();
	 
			while (crunchifyIterator.hasNext()) {
				SelectionKey myKey = crunchifyIterator.next();
				if (myKey.isAcceptable()) { // Tests whether this key's channel is ready to accept a new socket connection
					SocketChannel clientSocket = socketChannel.accept();
					// Adjusts this channel's blocking mode to false
					clientSocket.configureBlocking(false);
					// Operation-set bit for read operations
					clientSocket.register(selector, SelectionKey.OP_READ);
					log("Connection Accepted: " + clientSocket.getLocalAddress() + "\n");
				} // Tests whether this key's channel is ready for reading
				else if (myKey.isReadable()) {				
					SocketChannel crunchifyClient = (SocketChannel) myKey.channel();
					ByteBuffer crunchifyBuffer = ByteBuffer.allocate(256);
					crunchifyClient.read(crunchifyBuffer);
					String result = new String(crunchifyBuffer.array()).trim();
	 
					log("Message received: " + result);
					if (true == result.equals("QUIT")) {
						crunchifyClient.close();
						log("\nIt's time to close connection as we got last company name 'Crunchify'");
						log("\nServer will keep running. Try running client again to establish new connection");
					}
				}
				crunchifyIterator.remove();
			}
		}
	}
	 
	private static void log(String str) {
		System.out.println(str);
	}
}
