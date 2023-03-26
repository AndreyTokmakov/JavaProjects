package http_proxy;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

class HttpClientSynchronous {
    /*
    private static final HttpClient httpClient_EXAMPLE = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)                                          // REDIRECT
            .connectTimeout(Duration.ofSeconds(20))
            .proxy(ProxySelector.of(new InetSocketAddress("proxy.yourcompany.com", 80)))          // PROXY
            .authenticator(Authenticator.getDefault())                                            // AUTH
            .build();
    */

    private static final HttpClient httpClientWithProxy = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(20))
            .proxy(ProxySelector.of(new InetSocketAddress("164.92.251.154", 1080)))
            // .authenticator(Authenticator.getDefault())
            .build();

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public void SendRequest(String request_path) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(request_path))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        PrintHttpResponse(response);
    }

    public void SendRequestWithProxy(String request_path) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(request_path))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();

        HttpResponse<String> response = httpClientWithProxy.send(request, HttpResponse.BodyHandlers.ofString());
        PrintHttpResponse(response);
    }

    public void GetRequest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8080/hello"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
    }

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



public class HttpProxyTests
{
    public static final String HOST = "https://httpbin.org/get";

    public static HttpClient getClientWithProxy() throws NoSuchAlgorithmException {
        return HttpClient.newBuilder()
                .proxy(ProxySelector.of(new InetSocketAddress("185.162.228.135", 80)))
                .build();
    }

    public static HttpClient getClientSimple()  {
        HttpClient client = HttpClient.newHttpClient();
        return client;
    }

    public static void main(String[] args)
            throws IOException, InterruptedException, NoSuchAlgorithmException
    {
        // HttpClient client = getClientSimple();
        HttpClient client = getClientWithProxy();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HOST))
                .header("Accept", "application/json")
                .build();

        int statusCode = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApplyAsync(HttpResponse::statusCode)
                .join();

        System.out.println(statusCode);
    }
}
