package simple_asynch_server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;

public class SimpleNioServer {
	// Server configuration constants :
	private final static int BUFFER_SIZE = 1024;
	private final static int DEFAULT_PORT = 52525;
	private final static String HOST_ADDRESS = "127.0.0.1";
	
	// CharsetDecoder variables :
	private final static Charset charset = Charset.forName("ISO-8859-1");
	private final static CharsetDecoder decoder = charset.newDecoder();
	
	// The buffer into which we'll read data when it's available
	protected ByteBuffer readBuffer = null;
	private Selector selector = null;
	private InetSocketAddress socketAddress = null;
	
	/** Server default constructor : **/
	public SimpleNioServer() throws IOException {
		this.socketAddress = new InetSocketAddress(HOST_ADDRESS, DEFAULT_PORT);
		this.readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
	    this.selector = initSelector();
	}
	
	/** Run server method : **/
	public void runServer() {
	    while (true) {
	        try{
	            selector.select();
	            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
	            while (selectedKeys.hasNext()) {
	            	/** **/
	                SelectionKey key = selectedKeys.next();
	                selectedKeys.remove();
	                /** **/
	                if (false == key.isValid()) {
	                    continue;
	                }
	                /** Check what event is available and deal with it: **/
	                if (key.isAcceptable()) {
	                    accept(key);
	                } else if (key.isReadable()) {
	                	readFromChannel(key);
	                } else if (key.isWritable()) {
	                   //write(key);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.exit(1);
	        }
	    }
	}
	
	private void accept(SelectionKey key) throws IOException {
	    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
	    SocketChannel socketChannel = serverSocketChannel.accept();
	    socketChannel.configureBlocking(false);
	    socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
	    socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
	    socketChannel.register(selector, SelectionKey.OP_READ);
	    System.out.println("Client is connected");
	}
	
	private void readFromChannel(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel)key.channel();
		String request = "";
		int bytesRead = -1;
		try {
			do {
				bytesRead = socketChannel.read(readBuffer);
				readBuffer.flip();
				request += decoder.decode(readBuffer).toString();
				readBuffer.clear();
			} while (-1 != bytesRead);
			System.out.println(request);
			socketChannel.close();
		} catch (final IOException exception) {
			exception.printStackTrace();
			socketChannel.close();
	        key.cancel();
		}
	}
	
	@SuppressWarnings("unused")
	private void write(SelectionKey key) throws IOException {
	    SocketChannel socketChannel = (SocketChannel) key.channel();
	    ByteBuffer dummyResponse = ByteBuffer.wrap("TEST\n".getBytes("UTF-8"));
	    socketChannel.write(dummyResponse);
	    if (dummyResponse.remaining() > 0) {
	        System.err.print("Filled UP");
	    }
	    key.interestOps(SelectionKey.OP_READ);
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
	    System.out.println("Starting echo server...");
	    SimpleNioServer server = new SimpleNioServer();
	    server.runServer();
	}
}