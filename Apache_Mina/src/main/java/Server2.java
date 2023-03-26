import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.SimpleBufferAllocator;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * An TCP server used for performance tests.
 * It does nothing fancy, except receiving the messages, and counting the number of
 * received messages.
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class Server2 extends IoHandlerAdapter {
    /** The listening port (check that it's not already in use) */
    public static final int PORT = 52525;

    /** The number of message to receive */
    public static final int MAX_RECEIVED = 100000;
    
    /** Clients count: */
    private long clientsCount = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        session.closeNow();
    }
    
    /**
     * {@inheritDoc}
     */
    /*
    @Override
    public void messageSent(IoSession session, java.lang.Object message)  throws java.lang.Exception {
    	if (message instanceof IoBuffer) {
        	IoBuffer buffer = (IoBuffer) message;
        	String msg = buffer.getString(Charset.forName("UTF8").newDecoder());
        	System.out.println("messageSent : " + msg);
        }
        
    }*/
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        SessionContext ctx = (SessionContext)session.getAttribute("context");
        System.out.println("Request from " + ctx.name + " : Count = " + ctx.count.incrementAndGet());

        if (message instanceof IoBuffer) {
        	IoBuffer buffer = (IoBuffer) message;
        	String request = buffer.getString(Charset.forName("UTF8").newDecoder());
        	//System.out.print(request);
        	try {
        		Object obj = new JSONParser().parse(request);
                JSONObject jsonObject = (JSONObject) obj;
                String type = (String) jsonObject.get("type");
                System.out.println("Type = " + type);
            } catch (ParseException e) {
            	e.printStackTrace();
            }
        }
        
        /** Build JSON response : **/
        JSONObject json = new JSONObject();
		json.put("type",   "authorization_response");
		json.put("result", "OK");
        
        String response = json.toString() + "\r\n";
        IoBuffer buffer = IoBuffer.allocate(response.length());
        buffer.putString(response, Charset.forName("UTF8").newEncoder());
        buffer.flip();
        
        session.write(buffer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("Session closed...");
    }

    /**
     * {@inheritDoc}
    */
   @Override
   public void sessionCreated(IoSession session) throws Exception {
	   clientsCount++;
	   SessionContext ctx = new SessionContext("Client_" + clientsCount, 0);
	   session.setAttribute("context", ctx);
       System.out.println("Session created. [" + ctx.name + "]");
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
       System.out.println("Session idle...");
   }

   /**
    * {@inheritDoc}
    * @param session the current seession
    * @throws Exception If something went wrong
    */
   @Override
   public void sessionOpened(IoSession session) throws Exception {
       System.out.println("Session Opened...");
   }

   /**
    * Create the TCP server
    * @return 
    * @throws IOException If something went wrong
    */
   public Server2(String host, int port) throws IOException {
       NioSocketAcceptor acceptor = new NioSocketAcceptor();
       acceptor.setHandler(this);

       // The logger, if needed. Commented atm
       //DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
       //chain.addLast("logger", new LoggingFilter());

       InetSocketAddress hostAddress = new InetSocketAddress(InetAddress.getByName(host), port);
       acceptor.bind(hostAddress);

       System.out.println("Server started...");
   }

   /**
    * The entry point.
    * @param args The arguments
    * @throws IOException If something went wrong
    */
   public static void main(String[] args) throws IOException {
	   new Server2("127.0.0.1", 52525);
   }
}


class SessionContext {
	public String name;
    /** A counter incremented for every recieved message */
	public AtomicInteger count = null;
	
	public SessionContext(String name, int count) {
		super();
		this.name = name;
		this.count = new AtomicInteger(count);
		
	}
}