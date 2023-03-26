package asynch_socket_channel_server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.net.InetSocketAddress;

public class AsynchronousSocketChannelServer {
	public static void main (String [] args) throws Exception {
		new AsynchronousSocketChannelServer().go();
	}
	     
	private void go() throws IOException, InterruptedException, ExecutionException {
		AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
		InetSocketAddress hostAddress = new InetSocketAddress("127.0.0.1", 52525);
		serverChannel.bind(hostAddress);
	         
		System.out.println("Server channel bound to port: " + hostAddress.getPort() + "\nWaiting for client to connect... ");
	
		Future<AsynchronousSocketChannel> acceptResult = serverChannel.accept();
		AsynchronousSocketChannel clientChannel = (AsynchronousSocketChannel) acceptResult.get();
		System.out.println("Messages from client: ");
	 
		if ((clientChannel != null) && (clientChannel.isOpen())) {
			while (true) {
				ByteBuffer buffer = ByteBuffer.allocate(32);
				Future result = clientChannel.read(buffer);
				while (false == result.isDone()) {
					// do nothing
				}
				buffer.flip();
				String message = new String(buffer.array()).trim();
				System.out.println(message);
	
				if (message.equals("Bye.")) {
					break; // while loop
				}
				else 
					System.out.println("No bye");
				buffer.clear();
			} 
			clientChannel.close();
		} 
		serverChannel.close();
	}
}
