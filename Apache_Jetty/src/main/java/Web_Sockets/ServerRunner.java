package Web_Sockets;

import SimpleHelloWorldServer.HelloWorld;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class ServerRunner {

    public final class EchoSocketHandler extends WebSocketHandler
    {
        @Override
        public void configure(WebSocketServletFactory factory)
        {
            // factory.setCreator(new EchoCreator());
        }
    }

    public static void main(String[] args) throws Exception
    {
        final WebSocketHandler wsHandler = new WebSocketHandler()  {
            @Override
            public void configure(WebSocketServletFactory factory) {
                factory.register(MyWebSocket.class);
            }
        };

        ContextHandler context = new ContextHandler();
        context.setContextPath("/ws");
        context.setHandler(wsHandler);

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        server.join();
    }
}
