package simple_asynch_server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class SimpleNonBlockingServer extends Thread {
	
	public static void main(String[] args) throws IOException {
	    System.out.println("Starting echo server...");
	    SimpleNonBlockingServer server = new SimpleNonBlockingServer();
	    server.start_server();
	}

    private ConcurrentMap<Integer, SelectionKey> keys; // ID -> associated key
    private ConcurrentMap<SocketChannel, List<byte[]>> dataMap_out;
    ConcurrentLinkedQueue<String> in_msg; //incoming messages to be fetched by messenger thread
    
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

    public SimpleNonBlockingServer() throws IOException {
    	this.dataMap_out = new ConcurrentHashMap<SocketChannel, List<byte[]>>();
    	this.keys = new ConcurrentHashMap<Integer, SelectionKey>();
    	this.socketAddress = new InetSocketAddress(HOST_ADDRESS, DEFAULT_PORT);
    	this.readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        this.selector = initSelector();
    }

	private Selector initSelector() throws IOException {
	    Selector socketSelector = SelectorProvider.provider().openSelector();
	    ServerSocketChannel serverChannel = ServerSocketChannel.open();
	    serverChannel.configureBlocking(false);
	    serverChannel.socket().bind(socketAddress);
	    serverChannel.register(socketSelector, SelectionKey.OP_ACCEPT);
	    return socketSelector;
	}
    
    public void start_server() throws IOException {
        while (true) {
            selector.select();
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            while (selectedKeys.hasNext()) {
                SelectionKey key = (SelectionKey) selectedKeys.next();
                // this is necessary to prevent the same key from coming up 
                // again the next time around.
                selectedKeys.remove();

                if (! key.isValid()) {
                    continue;
                }

                if (key.isAcceptable()) {
                    this.accept(key);
                }
                else if (key.isReadable()) {
                    this.read(key);
                }
                else if (key.isWritable()) {
                    this.write(key);
                }
                else if(key.isConnectable()) {
                    this.connect(key);
                }
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        send_message(key, "Welcome."); //DEBUG

        Socket socket = channel.socket();
        SocketAddress remoteAddr = socket.getRemoteSocketAddress();
        log("Connected to: " + remoteAddr);

        // register channel with selector for further IO
        dataMap_out.put(channel, new ArrayList<byte[]>());
        channel.register(this.selector, SelectionKey.OP_READ);

        //store key in 'keys' to be accessable by ID from messenger thread //TODO first get ID
        keys.put(0, key);
    }

    //TODO verify, test
    public void init_connect(String addr, int port){
        try {
            SocketChannel channel = createSocketChannel(addr, port);
            channel.register(this.selector, channel.validOps()/*, SelectionKey.OP_?*/);
        }
        catch (IOException e) {
            //TODO handle
        }
    }

    //TODO verify, test
    private void connect(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        try {
            channel.finishConnect(); //try to finish connection - if 'false' is returned keep 'OP_CONNECT' registered
            //store key in 'keys' to be accessable by ID from messenger thread //TODO first get ID
            keys.put(0, key);
        }
        catch (IOException e0) {
            try {
                //TODO handle ok?
                channel.close();
            }
            catch (IOException e1) {
                //TODO handle
            }
        }

    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        int numRead = -1;
        try {
            numRead = channel.read(buffer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (numRead == -1) {
            this.dataMap_out.remove(channel);
            Socket socket = channel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            log("Connection closed by client: " + remoteAddr); //TODO handle
            channel.close();
            return;
        }

        byte[] data = new byte[numRead];
        System.arraycopy(buffer.array(), 0, data, 0, numRead);
        in_msg.add(new String(data, "utf-8"));
    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        List<byte[]> pendingData = this.dataMap_out.get(channel);
        Iterator<byte[]> items = pendingData.iterator();
        while (items.hasNext()) {
            byte[] item = items.next();
            items.remove();
            //TODO is this correct? -> re-doing write in loop with same buffer object
            ByteBuffer buffer = ByteBuffer.wrap(item);
            int bytes_to_write = buffer.capacity();
            while (bytes_to_write > 0) {
                bytes_to_write -= channel.write(buffer);
            }
        }
        key.interestOps(SelectionKey.OP_READ);
    }

    public void queue_data(SelectionKey key, byte[] data) {
        //SocketChannel channel = (SocketChannel) key.channel();
        List<byte[]> pendingData = this.dataMap_out.get(key.channel());
        key.interestOps(SelectionKey.OP_WRITE);

        pendingData.add(data);
    }

    public void send_message(int id, String msg) {
        SelectionKey key = keys.get(id);
        if (key != null)
            send_message(key, msg);
        //else
            //TODO handle
    }

    public void send_message(SelectionKey key, String msg) {
        try {
            queue_data(key, msg.getBytes("utf-8"));
        }
        catch (UnsupportedEncodingException ex) {
            //is not thrown: utf-8 is always defined
        }
    }

    public String get_message() {
        return in_msg.poll();
    }

    private static void log(String s) {
        System.out.println(s);
    }

    @Override
    public void run() {
        try {
            start_server();
        }
        catch (IOException e) {
            System.out.println("IOException: " + e);
            //TODO handle exception
        }
    }    


    // Creates a non-blocking socket channel for the specified host name and port.
    // connect() is called on the new channel before it is returned.
    public static SocketChannel createSocketChannel(String hostName, int port) throws IOException {
        // Create a non-blocking socket channel
        SocketChannel sChannel = SocketChannel.open();
        sChannel.configureBlocking(false);

        // Send a connection request to the server; this method is non-blocking
        sChannel.connect(new InetSocketAddress(hostName, port));
        return sChannel;
    }
}