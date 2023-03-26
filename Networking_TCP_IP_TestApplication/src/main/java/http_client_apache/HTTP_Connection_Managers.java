package http_client_apache;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

class Tests {
    private final static int port = 8080;
    private final static String host = "127.0.0.1";
    private final static String url = "http://" + host + ":"+ 8080 + "/hello";
	
	public void ConnectionTest() 
			throws InterruptedException, ExecutionException, IOException
	{
		HttpClientContext context = HttpClientContext.create();
		HttpClientConnectionManager connMrg = new BasicHttpClientConnectionManager();
		HttpRoute route = new HttpRoute(new HttpHost(host, 8080));
		
		// Request new connection. This can be a long process
		ConnectionRequest connRequest = connMrg.requestConnection(route, null);
		
		// Wait for connection up to 10 sec
		HttpClientConnection conn = connRequest.get(10, TimeUnit.SECONDS);
		try {
		    // If not open
		    if (!conn.isOpen()) {
		        // establish connection based on its route info
		        connMrg.connect(conn, route, 1000, context);
		        
		        // and mark it as route complete
		        connMrg.routeComplete(conn, route, context);
		    }
		    // Do useful things with the connection.
		} finally {
			System.out.println();
		    connMrg.releaseConnection(conn, null, 1, TimeUnit.MINUTES);
		}
	}
	
	public void SimpleManagerTest() throws ClientProtocolException, IOException {
		HttpHost localhost = new HttpHost(host, port);
		
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		connManager.setMaxTotal(200);
		// Increase default max connection per route to 20
		connManager.setDefaultMaxPerRoute(20);
		// Increase max connections for localhost:80 to 50
		connManager.setMaxPerRoute(new HttpRoute(localhost), 50);
		
		
		CloseableHttpClient httpClient = HttpClients
				.custom()
				.setConnectionManager(connManager)
		        .build();
		
        ResponseHandler<String> responseHandler = new CustomResponseHandler();
        HttpGet request = new HttpGet(url);

        String httpresponse = httpClient.execute(request, responseHandler);
        System.out.println(httpresponse);
	}
}

public class HTTP_Connection_Managers {
	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException
	{
		Tests tests = new Tests();
		
		// tests.ConnectionTest();
		// tests.Test();
	}
}
