import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import io.nats.client.Nats;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Consumer
{
    public static final Integer NATS_PORT = 4222;
    public static final Integer NATS_MGMT_PORT = 8222;
    public static final String channelName = "nats.demo.service";
    private static final String defaultServer = "nats://localhost:4222";

    public static void main(String[] args) throws IOException, InterruptedException
    {
        try (final Connection connection = Nats.connect(defaultServer))
        {
            final Dispatcher dispatcher = connection.createDispatcher((msg) -> {
                System.out.printf("%s on subject %s\n", new String(msg.getData(), StandardCharsets.UTF_8), msg.getSubject());
            });

            //dispatcher.subscribe(channelName);

            dispatcher.subscribe(channelName, msg -> {
                System.out.println("Received : " + new String(msg.getData()));
            });
            System.out.println("Done");
        }
    }
}
