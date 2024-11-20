package handlers;

import java.io.IOException;
import java.io.PrintWriter;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;

@Log4j
public class HelloHandler extends AbstractHandler
{
    private final String greeting;
    private final String body;

    public HelloHandler()  {
    	this("HELLO");
		log.info(this.getClass().getSimpleName() + " created!");
    }

    public HelloHandler( String greeting ) {
        this(greeting, null);
    }

    public HelloHandler( String greeting, String body )
    {
        this.greeting = greeting;
        this.body = body;
    }

    @Override
	public void handle(String target,
                       Request baseRequest,
					   HttpServletRequest request,
					   HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();

        log.info("HelloHandler::handle() called: " + greeting);
        //System.out.println("InternalController::getHealthCheckForBalancer() called");

        out.println("<h1>" + greeting + "</h1>");
        if (body != null) {
            out.println(body);
        }
        baseRequest.setHandled(true);
	}
}