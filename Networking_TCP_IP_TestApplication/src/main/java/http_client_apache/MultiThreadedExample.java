package http_client_apache;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


class GetThread extends Thread {
    private final CloseableHttpClient httpClient;
    private final HttpContext context;
    private final HttpGet httpget;

    public GetThread(CloseableHttpClient httpClient, HttpGet httpget) {
        this.httpClient = httpClient;
        this.context = HttpClientContext.create();
        this.httpget = httpget;
    }

    @Override
    public void run() {
        try (CloseableHttpResponse response = httpClient.execute(httpget, context)){
            String str = EntityUtils.toString(response.getEntity());
            System.out.println(str);
        } catch (ClientProtocolException ex) {
            // Handle protocol errors
        } catch (IOException ex) {
            // Handle I/O errors
        }
    }
}

class MTests {
	
	public void Test() throws InterruptedException {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		CloseableHttpClient httpClient = HttpClients.custom()
		        .setConnectionManager(cm)
		        .build();

		List<String> urls = Arrays.asList(
			"http://127.0.0.1:8080/hello",
			"http://127.0.0.1:8080/hello",
			"http://127.0.0.1:8080/hello",
			"http://127.0.0.1:8080/hello"
		);

		// create a thread for each URI
		GetThread[] threads = new GetThread[urls.size()];
		for (int i = 0; i < threads.length; i++) {
		    HttpGet httpget = new HttpGet(urls.get(i));
		    threads[i] = new GetThread(httpClient, httpget);
		}

		// start the threads
		for (int j = 0; j < threads.length; j++) {
		    threads[j].start();
		}

		// join the threads
		for (int j = 0; j < threads.length; j++) {
		    threads[j].join();
		}	
	}
}

public class MultiThreadedExample {
	public static void main(String[] args) throws InterruptedException {
		MTests tests = new MTests();
		tests.Test();
	}
}