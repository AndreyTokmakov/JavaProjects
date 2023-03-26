package https;

import java.io.BufferedReader;
import java.util.Enumeration;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet
{
	int count = 0;
	
	public HelloServlet() {
		System.out.println(HelloServlet.class.getName());
	}
	
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,IOException {
    	
    	System.out.println(HelloServlet.class.getName() + ": handling get request");
    	
    	Enumeration<String> headerNames = request.getHeaderNames();
    	System.out.println("Request Header Names : ");
    	while(headerNames.hasMoreElements()){
    		System.out.print(headerNames.nextElement() + "  ");
    	}
    	
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello from HelloServlet3</h1>");

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	
        StringBuffer buffer = new StringBuffer();
        String line = null;
        try {
        	BufferedReader reader = request.getReader();
        	while ((line = reader.readLine()) != null)
        		buffer.append(line);
        } catch (Exception e) { 
        	/*report an error*/ 
        }
        System.out.println("**************************************************************************************************************"); 
        System.out.println(buffer); 
       
        /** Build response : **/
        
        if (buffer.toString().contains("123456")) {
        	if (3 > count) {
        		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        		count++;
        	} else {
        		response.setStatus(HttpServletResponse.SC_OK);
        		count = 0;
        	}
        }
        else
        	response.setStatus(HttpServletResponse.SC_OK);    
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().println("<h1>We've got it.</h1>");
        
        System.out.println("**************************************************************************************************************"); 
        System.out.println(response); 
    }
}