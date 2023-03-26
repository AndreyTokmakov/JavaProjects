package http_servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpPrincipal;
import com.sun.net.httpserver.HttpServer;

class Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
		URI requestURI = httpExchange.getRequestURI();
		printRequestInfo(httpExchange);
		String response = "This is the response at " + requestURI;
		httpExchange.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
    }
    
	private void printRequestInfo(HttpExchange exchange) {
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
}

/*****************  MAIN : **********************/

public class BasicHttpServerExample {
	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(52525), 0);
		HttpContext context = server.createContext("/example");
		context.setHandler(new Handler());
		server.start();
	}
}