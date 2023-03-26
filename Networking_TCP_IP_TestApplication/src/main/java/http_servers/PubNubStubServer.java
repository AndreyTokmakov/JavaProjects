/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* PubNubStubServer.java class
*
* @name    : PubNubStubServer.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Feb 18, 2021
****************************************************************************/

package http_servers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

final class Statistics {
	/** **/
	public long requestsCount = 0;
	
	public final static LocalDateTime startUpDate = LocalDateTime.now();
	
}

abstract class ServerBase {
	/** Server default binding host address. **/
	private final static String DEFAULT_HOST = "0.0.0.0";
	
	/** Server default binding port. **/
	private final static int DEFAULT_PORT = 52525;
	
	/** Delay the maximum time in seconds to wait until exchanges have finished. **/
	private final static int DELAY = 1;
	
	/** This is the maximum number of
     *  queued incoming connections to allow on the listening socket. **/
	protected final static int DEFAULT_BACKLOG = 10;
	
	/** Server handle: **/
	protected HttpServer server = null;
	
	/** Statistics: **/
	protected final Statistics statistics;
	
	/** Statistics: **/
	protected final InetSocketAddress addr;
	
	protected ServerBase(Statistics statistics) {
		this(statistics, DEFAULT_PORT);
	}

	protected ServerBase(Statistics statistics, int port) {
		this(statistics, new InetSocketAddress(DEFAULT_HOST, port));
	}
	
	protected ServerBase(Statistics statistics, InetSocketAddress addr) {
		this.statistics = statistics;
		this.addr = addr;
	}
	
	protected HttpServer create() throws IOException {
		return HttpServer.create(addr, DEFAULT_BACKLOG);
	}
	
	public void stop() {
		this.server.stop(DELAY);
	}
}

class StubPubNumServer extends ServerBase implements HttpHandler  {
	/** PubNub publish path. **/
	private final static String PUBNUB_PUBLISH_PATH = "/publish";
	
	/** Handlers thread pool size. **/
	private final static int THREAD_POOL_SIZE = 50;
	
	/** Stub HTTP response: **/
	private final byte[] DEFAULT_RESPONSE 
		= new String("[1,\"Sent\",\"16136555496680266\"]").getBytes();
	
	public StubPubNumServer(Statistics statistics) {
		super(statistics);
	}
	
	public void start() throws IOException {
		this.server = create();
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		server.createContext(PUBNUB_PUBLISH_PATH, this);
		server.setExecutor(threadPoolExecutor);
		server.start();
	}
	
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		statistics.requestsCount++;
		
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		httpExchange.sendResponseHeaders(200, DEFAULT_RESPONSE.length);
		OutputStream stream = httpExchange.getResponseBody();
		stream.write(DEFAULT_RESPONSE);
		stream.close();
	}
}

class GatewayServer extends ServerBase implements HttpHandler {
	/** Server default binding port. **/
	private final static int GATEWAY_PORT = 8888;
	
	/** Date time formatter: **/
	private final static SimpleDateFormat DATE_FORMATTER = 
			new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
	
	public GatewayServer(Statistics statistics) {
		super(statistics, GATEWAY_PORT);
	}
	
	public void start() throws IOException {
		this.server = create();
		server.createContext("/", this);
		server.start();
	}
	
	private String buildResponse() {
		StringBuilder strBuilder = new StringBuilder("<body>");

	    Duration upTime = Duration.between(statistics.startUpDate, LocalDateTime.now());
	    String upTimeStr = DateTimeFormatter.ISO_LOCAL_TIME.format(upTime.addTo(LocalTime.of(0, 0)));
		
		strBuilder.append("Up time: ").append(upTimeStr).append("<br>");
		strBuilder.append("Count  : ").append(statistics.requestsCount).append("<br>");
		strBuilder.append("</body>");
		
		return strBuilder.toString();
	}
	
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		final String response = buildResponse();
			
		httpExchange.sendResponseHeaders(200, response.getBytes().length);
		OutputStream stream = httpExchange.getResponseBody();
		stream.write(response.getBytes());
		stream.close();
	}
}

public class PubNubStubServer {
	public static void main(String[] args) throws IOException
	{
		Statistics statistics = new Statistics();
		new StubPubNumServer(statistics).start();
		new GatewayServer(statistics).start();
	}
}
