package web_sockets;


import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.websocket.ContainerProvider;
import java.net.URI;

@ClientEndpoint
public class WebSocket_Client_Example {

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }

    public static void main(String[] args)
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://echo.websocket.org"; // Example WebSocket server
        try {
            container.connectToServer(WebSocket_Client_Example.class, URI.create(uri));
            System.out.println("Connected to server");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}