package http_servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader; 
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpPrincipal;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer implements HttpHandler {
	
	private final String httpResponse = "<html><body bgcolor = \"gray\"><h1>Hello</h1></body></html>";
	
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
	
		printRequestInfo(httpExchange);
		httpExchange.sendResponseHeaders(200, httpResponse.getBytes().length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(httpResponse.getBytes());
		os.close();
    }
    
	private void printRequestInfo(HttpExchange exchange) {
		URI uri = exchange.getRequestURI();
		System.out.println(uri);
	
		
		System.out.println("-- headers --");
		Headers requestHeaders = exchange.getRequestHeaders();
		requestHeaders.entrySet().forEach(System.out::println);

		System.out.println("-- principle --");
		HttpPrincipal principal = exchange.getPrincipal();
		System.out.println(principal);

		System.out.println("-- HTTP method --");
		String requestMethod = exchange.getRequestMethod();
		System.out.println(requestMethod);

		System.out.println("-- query --");
		URI requestURI = exchange.getRequestURI();
		String query = requestURI.getQuery();
		System.out.println(query);
	}
	
	public static void main(String[] args) throws IOException {
		final int THREAD_POOL_SIZE = 5;
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		
		HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 52525), 0);
		HttpContext context = server.createContext("/");
		context.setHandler(new SimpleHttpServer());
		server.setExecutor(threadPoolExecutor);
		server.start();
	}
}