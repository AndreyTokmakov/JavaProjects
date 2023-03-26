/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Http_Performance_Tests.java class
*
* @name    : Http_Performance_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 20, 2020
****************************************************************************/

package http_clients;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

class HTTPPerfTester { 
	/*
	HttpClient httpClient_EXAMPLE = HttpClient.newBuilder()
		      .version(HttpClient.Version.HTTP_2)
		      .followRedirects(HttpClient.Redirect.NORMAL)                                          // REDIRECT
		      .connectTimeout(Duration.ofSeconds(20))
		      .proxy(ProxySelector.of(new InetSocketAddress("proxy.yourcompany.com", 80)))          // PROXY
		      .authenticator(Authenticator.getDefault())                                            // AUTH
		      .build();
	*/
	
	private static final HttpClient httpClient = HttpClient.newBuilder()
	            .version(HttpClient.Version.HTTP_1_1)
	            .connectTimeout(Duration.ofSeconds(10))
	            .build();
	
	public void send_request(String request_path) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
						                 .GET()
						                 .uri(URI.create(request_path))
						                 .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
						                 .build();
		for (int i = 0; i < 10000; i++) {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			// Utilities.PrintHttpResponse(response);
		}
	}
}

public class Http_Performance_Tests 
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		HTTPPerfTester client = new HTTPPerfTester();
		client.send_request("http://127.0.0.1:52525/index.html");
	}
}
