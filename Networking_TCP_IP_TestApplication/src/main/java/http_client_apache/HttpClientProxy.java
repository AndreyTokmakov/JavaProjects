package http_client_apache;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class HttpClientProxy
{
    // public static final String HOST = "https://www.google.com";
    public static final String HOST = "https://httpbin.org/get";

    public static void GET_Request() throws ClientProtocolException, IOException
    {
        HttpGet request = new HttpGet(HOST);
        request.addHeader(HttpHeaders.USER_AGENT, "ApacheTestClient");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            Utilities.logResponse(response);
        }
    }

    public static void GET_Request_Proxy() throws ClientProtocolException, IOException
    {
        final RequestConfig requestConfig = RequestConfig.custom()
                 // .setProxy(new HttpHost("193.53.87.216", 33128))
                 // .setProxy(new HttpHost("203.13.32.219", 80))
                 // .setProxy(new HttpHost("185.171.230.11", 80))
                 // .setProxy(new HttpHost("203.24.109.76", 80))
                 .setProxy(new HttpHost("185.162.228.135", 80))
                .build();

        final HttpGet request = new HttpGet(HOST);
        // HttpGet request = new HttpGet("https://httpbin.org/get");

        request.addHeader(HttpHeaders.USER_AGENT, "ApacheTestClient");
        request.setConfig(requestConfig);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            Utilities.logResponse(response);
        }
    }

    public static void main(String[] args) throws Exception  {
        // GET_Request();
        GET_Request_Proxy();
    }
}
