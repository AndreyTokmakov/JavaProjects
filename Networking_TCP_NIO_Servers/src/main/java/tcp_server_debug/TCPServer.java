package tcp_server_debug;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class TCPServer {
	
	private static final int BUFFER_SIZE = 1024;
	private final static int DEFAULT_PORT = 52525;
	private final static String HOST_ADDRESS = "127.0.0.1";
	
	// The buffer into which we'll read data when it's available
	private final ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
	private final Selector selector;
	private InetSocketAddress socketAddress = null;
	private long loopTime;
	private long numMessages = 0;
	

	public TCPServer() throws IOException {
		this.socketAddress = new InetSocketAddress(HOST_ADDRESS, DEFAULT_PORT);
	    this.selector = initSelector();
	    this.runServer();
	}
	
	private void runServer() {
	    while (true) {
	        try{
	            selector.select();
	            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
	            while (selectedKeys.hasNext()) {
	            	//System.out.println("Handling key");
	            	/** **/
	                SelectionKey key = selectedKeys.next();
	                selectedKeys.remove();
	                
	                /** **/
	                if (!key.isValid()) {
	                    continue;
	                }
	                
	                /** Check what event is available and deal with it: **/
	                if (key.isAcceptable()) {
	                    accept(key);
	                } else if (key.isReadable()) {
	                    read(key);
	                } else if (key.isWritable()) {
	                    write(key);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.exit(1);
	        }
	    }
	}
	
	private void accept(SelectionKey key) throws IOException {
		try (final ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel())
		{
			SocketChannel socketChannel = serverSocketChannel.accept();
			socketChannel.configureBlocking(false);
			socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
			socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
			socketChannel.register(selector, SelectionKey.OP_READ);

			// System.out.println("Client is connected");
		}
	}
	
	private void read(SelectionKey key) throws IOException {
	    SocketChannel socketChannel = (SocketChannel)key.channel();
	    // Clear out our read buffer so it's ready for new data
	    readBuffer.clear();
	    // Attempt to read off the channel
	    int bytesRead = - 1;
	    try {
	    	bytesRead = socketChannel.read(readBuffer);
		    if (bytesRead == -1) {
		        //System.out.println("Graceful shutdown");
		        key.channel().close();
		        key.cancel();
		        return;
		    }
		    String data = new String(readBuffer.array()).trim();
	    	System.out.println("'" + data + "'");

	    } catch (IOException e) {
	        key.cancel();
	        socketChannel.close();
	        // System.out.println("Forceful shutdown");
	        return;
	    }


	    socketChannel.register(selector, SelectionKey.OP_WRITE);

	    numMessages++;
	    if (numMessages % 10000 == 0) {
	        long elapsed = System.currentTimeMillis() - loopTime;
	        loopTime = System.currentTimeMillis();
	        System.out.println(elapsed);
	    }
	}
	
	private void write(SelectionKey key) throws IOException {
		//System.out.println("write() entered.");
	    SocketChannel socketChannel = (SocketChannel) key.channel();
	    ByteBuffer dummyResponse = ByteBuffer.wrap("Response!\n".getBytes("UTF-8"));
	    socketChannel.write(dummyResponse);
	    if (dummyResponse.remaining() > 0) {
	        System.err.print("Filled UP");
	    }
	    key.interestOps(SelectionKey.OP_READ);
	    //socketChannel.close();

	    //System.out.println("write() done.");
	}
	
	private Selector initSelector() throws IOException {
	    Selector socketSelector = SelectorProvider.provider().openSelector();
	    ServerSocketChannel serverChannel = ServerSocketChannel.open();
	    serverChannel.configureBlocking(false);
	    serverChannel.socket().bind(socketAddress);
	    serverChannel.register(socketSelector, SelectionKey.OP_ACCEPT);
	    return socketSelector;
	}
	
	public static void main(String[] args) throws IOException {
	    System.out.println("Starting echo server");
	    new TCPServer();
	}
}