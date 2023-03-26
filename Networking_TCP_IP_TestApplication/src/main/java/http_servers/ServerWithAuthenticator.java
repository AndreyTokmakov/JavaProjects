/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* ServerWithAuthenticator.java class
*
* @name    : ServerWithAuthenticator.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 31, 2021
****************************************************************************/

package http_servers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpPrincipal;
import com.sun.net.httpserver.HttpServer;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

public class ServerWithAuthenticator {
	
	public static void main(String[] args) throws IOException {
		new HTTPServer().start();
	}
	
	@Log4j
	private final static class HTTPServer {
		private final static String host = "0.0.0.0";
		private final static int port = 8080;
		
		public HTTPServer() {
			log.info(this.getClass().getSimpleName() + " created!");
		}
		
		public void start() throws IOException
		{
			HttpServer server = HttpServer.create(new InetSocketAddress(host, port), 0);
			// server.createContext("/", new WelcomePageHaner());
			HttpContext context = server.createContext("/admin", new AdminPage());
			context.setAuthenticator(new Auth());
			server.start();
			log.info(String.format("Server stated at %s:%d", host,port));
		}
	}
	
	@Log4j
	private final static class WelcomePageHaner implements HttpHandler {
		private final static String httpResponse = "<html><body bgcolor = \"gray\"><h1>Welcome</h1></body></html>";
		
		public WelcomePageHaner() {
			log.info(this.getClass().getSimpleName() + " created!");
		}
		
	    @Override
	    public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder builder = new StringBuilder();

            builder.append("<h1>URI: ").append(httpExchange.getRequestURI()).append("</h1>");
            Headers headers = httpExchange.getRequestHeaders();
            for (String header : headers.keySet()) {
                builder.append("<p>").append(header).append("=")
                        .append(headers.getFirst(header)).append("</p>");
            }

            byte[] bytes = builder.toString().getBytes();
            httpExchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
			
			log.info(httpExchange);
	    }
	}
	
	@Log4j
	private final static class AdminPage implements HttpHandler {
		private final static String httpResponse = "<html><body bgcolor = \"gray\"><h1>This is the admin page.</h1></body></html>";
		
		public AdminPage() {
			log.info(this.getClass().getSimpleName() + " created!");
		}
		
	    @Override
	    public void handle(HttpExchange httpExchange) throws IOException {
            byte[] bytes = httpResponse.getBytes();
            httpExchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
			
			log.info(httpExchange);
	    }
	}
	
	@Log4j
	private final static class Auth extends Authenticator {
		public Auth() {
			log.info(this.getClass().getSimpleName() + " created!");
		}
	
        @Override
        public Result authenticate(HttpExchange httpExchange) {
            if ("/forbidden".equals(httpExchange.getRequestURI().toString()))
                return new Failure(403);
            else
                return new Success(new HttpPrincipal("c0nst", "realm"));
        }
    }
}
