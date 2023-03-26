/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* SimpleHttpServer_Executor.java class
*
* @name    : SimpleHttpServer_Executor.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 31, 2021
****************************************************************************/

package http_servers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer_Executor implements HttpHandler  {
	private final String httpResponse = "<html><body bgcolor = \"gray\"><h1>Hello</h1></body></html>";
	
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
		httpExchange.sendResponseHeaders(200, httpResponse.getBytes().length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(httpResponse.getBytes());
		os.close();
    }
	
	public static void main(String[] args) throws IOException {
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8080), 0);
		HttpContext context = server.createContext("/");
		context.setHandler(new SimpleHttpServer_Executor());
		
		server.setExecutor(threadPoolExecutor);
		server.start();
	}
}
