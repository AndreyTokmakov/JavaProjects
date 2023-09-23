/**
 * 
 */
package servers;

import java.io.*;
import java.net.InetSocketAddress;
import java.lang.*;
import java.net.URL;

import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import com.sun.net.httpserver.*;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;

import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

import java.net.InetAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsExchange;

/**
 * @author a.tokmakov
 **/
public class HTTPSServer {
	
	  public static class MyHandler implements HttpHandler {
	        @Override
	        public void handle(HttpExchange httpExchange) throws IOException {
	            String response = "This is the response";
	            
	            ///////////////////////////////////////////////////////////
	            
	            InputStream input = httpExchange.getRequestBody();
                StringBuilder stringBuilder = new StringBuilder();

                System.out.println("Request method:");
                System.out.println("     " + httpExchange.getRequestMethod() + "\n");
                
                System.out.println("Request URI:");
                System.out.println("     " + httpExchange.getRequestURI() + "\n");

                System.out.println("Request data:");
                new BufferedReader(new InputStreamReader(input))
                    .lines().forEach( (String s) -> stringBuilder.append("     " + s + "\n") );
                System.out.println(stringBuilder);
	            
                Headers headers = httpExchange.getRequestHeaders();
                System.out.println("Request headers:");
                headers.forEach((k, v) -> System.out.println("     " + k + " : " + v));
                
                System.out.println("\n");
                ///////////////////////////////////////////////////////////
	            
	            HttpsExchange httpsExchange = (HttpsExchange) httpExchange;
	            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
	            httpExchange.sendResponseHeaders(200, response.length());
	            OutputStream os = httpExchange.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	        }
	    }

	    public static void main(String[] args) throws Exception {

	        try {
	        	String host = "0.0.0.0";
	        	//String host = "data.browser.mail.ru";
	        	int port  = 8443;
	        	
	            // setup the socket address
	            InetSocketAddress address = new InetSocketAddress(host, port);

	            // initialize the HTTPS server
	            HttpsServer httpsServer = HttpsServer.create(address, 0);
	            SSLContext sslContext = SSLContext.getInstance("TLS");

	            /*
	            // initialize the keystore
	            char[] password = "password".toCharArray();
	            KeyStore ks = KeyStore.getInstance("JKS");
	            String keyStoreFile = "R:\\Temp\\SSL\\RootAndLocalCert\\testkey.jks";
	            FileInputStream fis = new FileInputStream(keyStoreFile);
	            ks.load(fis, password);
	            */
	            
                // initialize the keystore
                final String password = "password";
				final String keyStoreFile = "/home/andtokm/Temp/Java/KeyStore.jks";
				final KeyStore ks = KeyStore.getInstance("JKS");
                FileInputStream fis = new FileInputStream(keyStoreFile);
                ks.load(fis, password.toCharArray());

	            // setup the key manager factory
	            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
	            kmf.init(ks, password.toCharArray());

	            // setup the trust manager factory
	            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
	            tmf.init(ks);

	            // setup the HTTPS context and parameters
	            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
	            httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
	                public void configure(HttpsParameters params) {
	                    try {
	                        // initialise the SSL context
	                        final SSLContext c = SSLContext.getDefault();
	                        SSLEngine engine = c.createSSLEngine();
	                        params.setNeedClientAuth(false);
	                        params.setCipherSuites(engine.getEnabledCipherSuites());
	                        params.setProtocols(engine.getEnabledProtocols());

	                        // get the default parameters
	                        final SSLParameters defaultSSLParameters = c.getDefaultSSLParameters();
	                        params.setSSLParameters(defaultSSLParameters);

	                    } catch (Exception ex) {
	                        System.out.println("Failed to create HTTPS port");
	                    }
	                }
	            });
	            httpsServer.createContext("/version.json", new MyHandler());
	            httpsServer.setExecutor(null); // creates a default executor
	            httpsServer.start();

	        } catch (Exception exception) {
	            System.out.println("Failed to create HTTPS server on port " + 443 + " of localhost");
	            exception.printStackTrace();

	        }
	    }

}
