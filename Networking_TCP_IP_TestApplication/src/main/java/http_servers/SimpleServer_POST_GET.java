/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* SimpleServer_POST_GET.java class
*
* @name    : SimpleServer_POST_GET.java
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

public class SimpleServer_POST_GET implements HttpHandler {
	private final String httpResponse_GET = "<html><body bgcolor = \"gray\"><h1>Hello GET</h1></body></html>";
	private final String httpResponse_POST = "<html><body bgcolor = \"gray\"><h1>Hello POST</h1></body></html>";
	
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
	
    	String response = null;
        if("GET".equals(httpExchange.getRequestMethod())) { 
        	response = httpResponse_GET;
        } else if("POST".equals(httpExchange.getRequestMethod())) { 
        	response = httpResponse_POST;       
        }  

		httpExchange.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
    }
	
	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8080), 0);
		server.createContext("/", new SimpleServer_POST_GET());
		server.start();
	}
}
