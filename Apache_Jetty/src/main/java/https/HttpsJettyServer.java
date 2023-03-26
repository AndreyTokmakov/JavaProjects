package https;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.IOException;
import java.net.URL;
import java.security.ProtectionDomain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpsJettyServer {

	public static void main(String[] args) {
		/*
		Server server = new Server();
		 
		// HTTP connector
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8080);
		
		//HTTPS configuration
		HttpConfiguration https = new HttpConfiguration();
		https.addCustomizer(new SecureRequestCustomizer());
	 
		// Configuring SSL :
		SslContextFactory sslContextFactory = new SslContextFactory();
		

		final String keyStoreFile1 = "R:\\Projects\\JavaExternalLibraries\\jetty-distribution-9.4.10.v20180503\\modules\\ssl\\keystore";
		final String keyStoreFile2 = "R:\\Temp\\SSL\\testkey.jks";
				
		sslContextFactory.setKeyStorePath(keyStoreFile1);
		sslContextFactory.setKeyStorePath("");
		
		//sslContextFactory.setKeyStorePath(args[0]);
		
		sslContextFactory.setKeyStorePassword("password");
		sslContextFactory.setKeyManagerPassword("password");
		//Configuring the connector
		ServerConnector sslConnector = new ServerConnector(server, new SslConnectionFactory(sslContextFactory, "http/1.1"), new HttpConnectionFactory(https));
		sslConnector.setPort(443);
	 
		// Setting HTTP and HTTPS connectors
		server.setConnectors(new Connector[]{connector, sslConnector});
	 
		// add handler
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setDirectoriesListed(true);
		resourceHandler.setWelcomeFiles(new String[]{"index.html"});
		resourceHandler.setResourceBase(".");
		
		ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(HelloServlet.class, "/");
        
        ContextHandler postHandler = new ContextHandler("/post");
        postHandler.setHandler(new HelloHandler("postHandler"));
        
		ProtectionDomain domain = HttpsJettyServer.class.getProtectionDomain();
		URL location = domain.getCodeSource().getLocation();
	 
		// add context
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/ls");
		webapp.setWar(location.toExternalForm());
	 
		HandlerList handlers = new HandlerList();
		
		handlers.addHandler(handler);
		//handlers.addHandler(postHandler);
		//handlers.addHandler(resourceHandler);
		//handlers.addHandler(webapp);
	 
		server.setHandler(handlers);
	 
		try {
			server.start();
			server.join();
		} catch (Throwable t) {
			t.printStackTrace(System.err);
		}
		*/
	}
}
