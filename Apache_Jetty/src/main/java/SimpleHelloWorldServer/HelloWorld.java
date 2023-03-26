package SimpleHelloWorldServer;
 
import java.io.IOException;
 
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloWorld extends AbstractHandler {
	
    public void handle(String target, 
    				   Request baseRequest,
    				   HttpServletRequest request,
    				   HttpServletResponse response) throws IOException, ServletException {
        // Declare response encoding and types
        response.setContentType("text/html; charset=utf-8");
        // Declare response status code
        response.setStatus(HttpServletResponse.SC_OK);
        // Write back response
        response.getWriter().println("<h1>Hello World</h1>");
        // Inform jetty that this request has now been handled
        baseRequest.setHandled(true);
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		server.setHandler(new HelloWorld());
		server.start();
		server.join();
	}
}
