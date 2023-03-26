/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* CloseableHttpClient_POST.java class
*
* @name    : CloseableHttpClient_POST.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Feb 22, 2021
****************************************************************************/

package http_client_apache;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.entity.mime.MultipartEntityBuilder;

final class HttpPostClient {
	/** **/
    private final static int port = 8080;
    /** **/
    private final static String URL = "http://127.0.0.1:" + 52525;
	
    public void GET_Request() throws ClientProtocolException, IOException 
    {
        HttpGet request = new HttpGet(URL + "/inspect");
        request.addHeader(HttpHeaders.USER_AGENT, "ApacheTestClient");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
        	Utilities.logResponse(response);
        }
    }
    
    public void Simple_POST_Request() throws ClientProtocolException, IOException {
        HttpPost request = new HttpPost(URL + "/posts/debug");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", "John"));
        params.add(new BasicNameValuePair("password", "pass"));
        request.setEntity(new UrlEncodedFormEntity(params));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
           	Utilities.logResponse(response);
        }
    }
    
    public void Send_POST_Multipart() 
    		throws ClientProtocolException, IOException {
        HttpPost request = new HttpPost(URL + "/posts/context1");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("username", "John");
        builder.addTextBody("password", "pass");
        builder.addBinaryBody("file", new File("C:\\Temp\\Folder_For_Testing\\Data.json"), ContentType.APPLICATION_OCTET_STREAM, "file.ext");

        HttpEntity multipart = builder.build();
        request.setEntity(multipart);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
        	Utilities.logResponse(response);
        }
    }
    
    public void Send_POST_JSON() throws ClientProtocolException, IOException {
        HttpPost request = new HttpPost(URL + "/posts/debug");

        String json = "{\"id\":1,\"name\":\"John\"}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.addHeader(HttpHeaders.USER_AGENT, "ApacheTestClient");
        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
           	Utilities.logResponse(response);
        }
    }

    public void Send_POST_JSON_BigData() throws ClientProtocolException, IOException {
        HttpPost request = new HttpPost(URL + "/posts/debug");

        // String json = "{\"id\":1,\"name\":\"John\"}";
        final String jsonData = Files.readString(Path.of("C:\\Temp\\Folder_For_Testing\\Data.json"));

        System.out.println(jsonData);

        final StringEntity entity = new StringEntity(jsonData);
        request.setEntity(entity);
        request.addHeader(HttpHeaders.USER_AGENT, "ApacheTestClient");
        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            Utilities.logResponse(response);
        }
    }
    
    public void Post_Authentication()
    		throws ClientProtocolException, IOException, AuthenticationException {
        HttpPost request = new HttpPost(URL + "/posts/debug");
        request.setEntity(new StringEntity("test post"));
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("John", "pass");
        request.addHeader(new BasicScheme().authenticate(creds, request, null));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
        	 CloseableHttpResponse response = httpClient.execute(request)) {
        	Utilities.logResponse(response);
        }
    }
}

public class CloseableHttpClient_POST {
	/** HTTP Client. **/
	private final static HttpPostClient client = new HttpPostClient();
	
	public static void main(String[] args)  throws Exception
	{
		// client.GET_Request();
		// client.Simple_POST_Request();

		// client.Send_POST_JSON();
        client.Send_POST_JSON_BigData();
		// client.Send_POST_Multipart();
		
		// client.Post_Authentication();              // <---- Create EndPoint for AUTH
	}
}
