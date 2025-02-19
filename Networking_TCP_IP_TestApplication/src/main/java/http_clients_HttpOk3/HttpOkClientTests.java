package http_clients_HttpOk3;

import okhttp3.*;

import java.io.IOException;

public class HttpOkClientTests
{
    private final static OkHttpClient client = new OkHttpClient();

    protected static void printHeaders(Response response) {
        Headers responseHeaders = response.headers();
        System.out.println("Headers:  ");
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println("  " + responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }
    }

    public static void simpleRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            System.out.println(result);
        }
    }

    public static void requestAsync(String url) {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response)
                    throws IOException {
                String result = response.body().string();
                printHeaders(response);
                System.out.println(result);
            }

            public void onFailure(Call call, IOException exc) {
                System.err.println(exc);
            }
        });
    }

    public static void main(String ... params) throws IOException
    {
        System.out.println("HttpOk3 Tests.");

        simpleRequest("https://httpbin.org/get");
        // requestAsync("https://httpbin.org/get");
        // simpleRequest("http://127.0.0.1:52525");
    }
}
