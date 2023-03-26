/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* HTTP Client tests application
*
* @name      : Http_Test_Client.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : October 17, 2020
****************************************************************************/ 

package http_clients;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


class Utilities {
	protected static void PrintHttpResponse(final HttpResponse<String> response) 
	{
		//System.out.println(response.statusCode());
		System.out.println(response.toString());
		System.out.println("------------------------------------ Headerds : ------------------------------------------");
		response.headers().map().forEach((k, v) -> System.out.println("  " + k + ": " + v));
		System.out.println("------------------------------------ Body : ----------------------------------------------");
		System.out.println(response.body());
	}
}

class HttpClientSynchronous { 
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
		  
		 HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		 
		 Utilities.PrintHttpResponse(response);
	}
	
	public void GetRequest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8080/hello"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
	}
	
	public void GetRequest_LongUrl() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i < 1; ++i) {
        	builder.append("/Context_" + i);
        }
        
        
    
    	List<CompletableFuture<Void>> tasks = new ArrayList<CompletableFuture<Void>>();
		for (int i = 0; i < 20 ; i++) {
			tasks.add(CompletableFuture.runAsync(() -> {
				HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("http://10.62.185.22:52525/publish"))
		                .build();
				System.out.println(new Date() + ": Load thread " + Thread.currentThread().getId() + " started");
				HttpResponse<String> response;
				try {
					response = client.send(request, HttpResponse.BodyHandlers.ofString());
					System.out.println(new Date() + ": " + response);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}));
		}
		tasks.forEach(F -> F.join());
	
        
        /*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://10.62.185.22:52525/publish"))
                .build();
        for (int i = 0; i < 10; ++i) {
        	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(new Date() + ": " + response);
        }
         */
	}
}


class HttpClient_AsycnTests { 
	
	private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
	
	public void GetRequest(String host) throws InterruptedException, ExecutionException 
	{
		HttpRequest request = HttpRequest.newBuilder()
										.GET()
									    .uri(URI.create(host))
									  //.setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
									    .build();
		
		
		HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();

		// HttpClient client = HttpClient.newHttpClient();
		CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request, bodyHandler);
		response.thenApply(HttpResponse::body).thenAccept(System.out::println).join();
		
		HttpResponse<String> httpResponse = response.get();
		System.out.println(httpResponse);
	}
	
    public void GetRequest_Simple() throws Exception {

    	final HttpClient httpClient = HttpClient.newBuilder()
								                .version(HttpClient.Version.HTTP_2)
								                .connectTimeout(Duration.ofSeconds(10))
								                .build();
    	
    	final String host = "https://httpbin.org/get";
        HttpRequest request = HttpRequest.newBuilder()
							             .GET()
							             .uri(URI.create(host))
							           //.setHeader("User-Agent", "Java 11 HttpClient Bot")
							             .build();

        CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
        System.out.println(result);
    }
}

class HttpClient_CustomExecutor {
	private static final int THREAD_POOL_SIZE = 5; 

    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .executor(executorService)
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
    public void Test() throws Exception {

        List<URI> targets = Arrays.asList(
                new URI("https://httpbin.org/get?name=mkyong1"),
                new URI("https://httpbin.org/get?name=mkyong2"),
                new URI("https://httpbin.org/get?name=mkyong3"));

        List<CompletableFuture<String>> result = targets.stream()
                .map(url -> httpClient.sendAsync(
                        HttpRequest.newBuilder(url)
                                .GET()
                                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                                .build(),
                        HttpResponse.BodyHandlers.ofString())
                        .thenApply(response -> response.body()))
                .collect(Collectors.toList());

        for (CompletableFuture<String> future : result) {
            System.out.println(future.get());
        }

    }
}

class HttpClient_POST {

    private static final HttpClient httpClient = 
    	HttpClient.newBuilder()
	              .version(HttpClient.Version.HTTP_2)
	              .connectTimeout(Duration.ofSeconds(10))
	              .build();

    public void SendPostRequest() throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();
        data.put("extension", "abc");

        
        String URL = "http://127.0.0.1:8080/posts/internal/cache/mailboxInfoCache/invalidate";

        HttpRequest request = HttpRequest.newBuilder()
						                 .POST(this.ofFormData(data))
						                 .uri(URI.create(URL))
						                 .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
						                 .header("Content-Type", "application/x-www-form-urlencoded")
						                 .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    
    public void SendPostRequest_DEBUG() throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();
        data.put("username", "abc");
        data.put("password", "123");
        data.put("custom", "secret");
        data.put("ts", System.currentTimeMillis());
        
        final String host = "http://127.0.0.1:52525";

        HttpRequest request = HttpRequest.newBuilder()
						                 .POST(this.ofFormData(data))
						                 .uri(URI.create(host))
						                 .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
						                 .header("Content-Type", "application/x-www-form-urlencoded")
						                 .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    // Sample: 'password=123&custom=secret&username=abc&ts=1570704369823'
    public HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
    
    public void Send_POST_Json() throws IOException, InterruptedException {
        String json = new StringBuilder().append("{")
						                 .append("\"name\":\"mkyong\",")
						                 .append("\"notes\":\"hello\"")
						                 .append("}").toString();
        
        HttpRequest request = HttpRequest.newBuilder()
						                 .POST(HttpRequest.BodyPublishers.ofString(json))
						                 .uri(URI.create("https://httpbin.org/post"))
						                 .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
						                 .header("Content-Type", "application/json")
						                 .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}

class HttpClient_Authentication {
	
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .authenticator(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication( "user", "password".toCharArray());
                }
            }).connectTimeout(Duration.ofSeconds(10)).build();
    
    
    public void SendRequest() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/books"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}


public class HttpClient_Tests {
	public static void main(String[] args) throws Exception {
		
		HttpClientSynchronous client = new HttpClientSynchronous();
		// client.send_request("https://httpbin.org/get");
		client.send_request("http://127.0.0.1:52525");
		// client.GetRequest();
		// client.GetRequest_LongUrl();
		
		HttpClient_AsycnTests asycn_client = new HttpClient_AsycnTests();
		// asycn_client.GetRequest_Simple();
		// asycn_client.GetRequest("http://www.example.com");
		
		HttpClient_CustomExecutor custom_executor_client = new HttpClient_CustomExecutor();
		// custom_executor_client.Test();
		
		HttpClient_POST client_post = new HttpClient_POST();
		// client_post.SendPostRequest();
		// client_post.SendPostRequest_DEBUG();
		// client_post.Send_POST_Json();
		
		HttpClient_Authentication auth_client = new HttpClient_Authentication();
		// auth_client.SendRequest();
	}
}
