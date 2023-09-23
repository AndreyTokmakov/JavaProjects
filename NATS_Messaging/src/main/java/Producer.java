import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Producer
{
    public static final Integer NATS_PORT = 4222;
    public static final Integer NATS_MGMT_PORT = 8222;
    public static final String channelName = "nats.demo.service";
    private static final String defaultServer = "nats://localhost:4222";

    public static void main(String[] args) throws IOException, InterruptedException
    {
        try (final Connection connection = Nats.connect(defaultServer))
        {
            // publish a message to the channel
            connection.publish(channelName, "Hello NATS".getBytes());
        }
    }
}
