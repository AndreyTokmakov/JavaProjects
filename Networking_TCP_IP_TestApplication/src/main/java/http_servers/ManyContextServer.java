/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* ManyContextServer.java class
*
* @name    : ManyContextServer.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 31, 2021
****************************************************************************/

package http_servers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

class WelcomePageHaner implements HttpHandler {
	private final String httpResponse = "<html><body bgcolor = \"gray\"><h1>Welcome</h1></body></html>";
	
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
		httpExchange.sendResponseHeaders(200, httpResponse.getBytes().length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(httpResponse.getBytes());
		os.close();
    }
}

class PageHandlerOne implements HttpHandler {
	private final String httpResponse = "<html><body bgcolor = \"gray\"><h1>Page One!</h1></body></html>";
	
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
		httpExchange.sendResponseHeaders(200, httpResponse.getBytes().length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(httpResponse.getBytes());
		os.close();
    }
}

class PageHandlerTwo implements HttpHandler {
	private final String httpResponse = "<html><body bgcolor = \"gray\"><h1>Page Two!</h1></body></html>";
	
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
		httpExchange.sendResponseHeaders(200, httpResponse.getBytes().length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(httpResponse.getBytes());
		os.close();
    }
}

public class ManyContextServer {
	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8080), 0);
		server.createContext("/", new WelcomePageHaner());
		server.createContext("/one", new PageHandlerOne());
		server.createContext("/two", new PageHandlerTwo());
		server.start();
	}
}
