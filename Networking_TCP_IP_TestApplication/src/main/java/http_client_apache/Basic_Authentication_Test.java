/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* Basic_Authentication_Test.java class
*
* @name    : Basic_Authentication_Test.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Feb 22, 2021
****************************************************************************/

package http_client_apache;

import java.io.IOException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class Basic_Authentication_Test {
	
	public static void main(String[] args) throws IOException 
	{
		Test();
	}
	
	public static void Test() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/books");

        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("user", "password"));

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
             CloseableHttpResponse response = httpClient.execute(request)) {
         	Utilities.logResponse(response);
        }
    }
}
