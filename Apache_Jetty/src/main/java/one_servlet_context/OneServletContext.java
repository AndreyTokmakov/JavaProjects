package one_servlet_context;

import embedding_servlets.MinimalServlets.HelloServlet;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.ServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ListenerHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.io.IOException;
import java.util.EnumSet;

public class OneServletContext
{
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        server.setHandler(context);

   
        //context.addServlet(context.addServlet(DumpServlet.class, "/dump/*"),"*.dump");
        context.addServlet(HelloServlet.class, "/hello/*");
        context.addServlet(DefaultServlet.class, "/");

        context.addFilter(TestFilter.class,"/*", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(TestFilter.class,"/test", EnumSet.of(DispatcherType.REQUEST,DispatcherType.ASYNC));
        context.addFilter(TestFilter.class,"*.test", EnumSet.of(DispatcherType.REQUEST,DispatcherType.INCLUDE,DispatcherType.FORWARD));

        //context.getServletHandler().addListener(context.getServletHandler().  newListenerHolder(InitListener.class));
        context.getServletHandler().addListener(new ListenerHolder(RequestListener.class));

        server.start();
        server.dumpStdErr();
        server.join();
    }


    public static class TestFilter implements Filter
    {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException
        {

        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
        {
            chain.doFilter(request, response);
        }

        @Override
        public void destroy()
        {

        }
    }

    public static class InitListener implements ServletContextListener
    {
        @Override
        public void contextInitialized(ServletContextEvent sce)
        {
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce)
        {
        }
    }


    public static class RequestListener implements ServletRequestListener
    {
        @Override
        public void requestDestroyed(ServletRequestEvent sre)
        {

        }

        @Override
        public void requestInitialized(ServletRequestEvent sre)
        {

        }
    }
}