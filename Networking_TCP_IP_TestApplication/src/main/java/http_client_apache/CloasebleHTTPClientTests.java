package http_client_apache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


class CustomResponseHandler implements ResponseHandler<String>{

	@Override
	public String handleResponse(final HttpResponse response) throws IOException{
		int status = response.getStatusLine().getStatusCode();
		
		if (status >= 200 && status < 300) {
			HttpEntity entity = response.getEntity();
			if(entity == null ) {
				return "";
			} else {
				return EntityUtils.toString(entity);
			}
		} else {
			return String.valueOf(status);
		}
	}
}

class HTTP_Client_Tests {
	/** **/
    private final static int port = 8080;
    
    /** **/
    private final static String URL = "http://127.0.0.1:" + 8080;
    private final static String GOOGLE = "https://google.com";

    private final static String STUB_URL = "http://10.62.183.98:" + 8888;
    
    /** **/
    private final static String WIREMOCK_URL = "http://localhost:8085/";
    
	
    public void GET_Request() throws ClientProtocolException, IOException 
    {
        // HttpGet request = new HttpGet(STUB_URL + "/inspect");
        HttpGet request = new HttpGet(GOOGLE);
        request.addHeader(HttpHeaders.USER_AGENT, "ApacheTestClient");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
        	Utilities.logResponse(response);
        }
    }
    
    
    
    public void GET_Request_Config() throws ClientProtocolException, IOException 
    {
        final RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(5000)
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .build();
        
        HttpGet request = new HttpGet(WIREMOCK_URL + "/timeout");
        // HttpGet request = new HttpGet("https://httpbin.org/get");

        request.addHeader(HttpHeaders.USER_AGENT, "ApacheTestClient");
        request.setConfig(requestConfig);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
        	Utilities.logResponse(response);
        }
    }
    
    
    public void GET_Request_Proxy() throws ClientProtocolException, IOException 
    {
    	RequestConfig requestConfig = RequestConfig.custom()
    	            .setProxy(new HttpHost("company.proxy.url", 8080))
    	            .build();
    	
        HttpGet request = new HttpGet(WIREMOCK_URL + "/timeout");
        // HttpGet request = new HttpGet("https://httpbin.org/get");

        request.addHeader(HttpHeaders.USER_AGENT, "ApacheTestClient");
        request.setConfig(requestConfig);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
        	Utilities.logResponse(response);
        }
    }
    
    
    public void GET_Request_CookieSpec() throws ClientProtocolException, IOException 
    {
    	RequestConfig requestConfig = RequestConfig.custom()
    			.setCookieSpec(CookieSpecs.DEFAULT)
    			.build();
        HttpGet request = new HttpGet("https://httpbin.org/get");

        request.addHeader(HttpHeaders.USER_AGENT, "ApacheTestClient");
        request.setConfig(requestConfig);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
        	Utilities.logResponse(response);
        }
    }
    
    public void GET_Request_Timeout() throws ClientProtocolException, IOException 
    {
        final RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(5000)
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .build();
    	
       HttpGet request = new HttpGet(WIREMOCK_URL + "/timeout");
       // HttpGet request = new HttpGet("https://httpbin.org/get");

        request.addHeader(HttpHeaders.USER_AGENT, "ApacheTestClient");
        request.setConfig(requestConfig);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
        	Utilities.logResponse(response);
        }
    }
    
    public void GET_Request_CustomHandler() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new CustomResponseHandler();
        
        HttpGet request = new HttpGet(URL + "/inspect");
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
        	String httpresponse = httpclient.execute(request, responseHandler);
        	System.out.println(httpresponse);
        }
	}
	
    public void GET_Request_DEBUG() throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet request = new HttpGet(URL + "/hello");

        System.out.println(request);
   
        HttpResponse response = httpclient.execute(request);


        Scanner sc = new Scanner(response.getEntity().getContent());
        System.out.println(response.getStatusLine());
        while(sc.hasNext()) {
        	System.out.println(sc.nextLine());
        }
        
        
        /*
        String str = EntityUtils.toString(response.getEntity());
        System.out.println(str);
        */
        
        System.out.println("--------------------------------------------------------------");
        
        /*
        sleep(10 * 1000);
        System.out.println("Close");
        httpclient.close();
        
        sleep(10 * 1000);
        System.out.println("Done");
        */
	}
}

public class CloasebleHTTPClientTests {
	/** HTTP Client. **/
	private final static HTTP_Client_Tests client = new HTTP_Client_Tests();
	
	public static void main(String[] args)  throws Exception 
	{
		 client.GET_Request();
		
		// client.GET_Request_Config();
		// client.GET_Request_Timeout();
		
		// client.GET_Request_Proxy();
		// client.GET_Request_CookieSpec();
		
		// client.GET_Request_DEBUG();
		// client.GET_Request_CustomHandler();
	}
}
