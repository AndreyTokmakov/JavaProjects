package producers;

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
    private static final String defaultServer = "nats://localhost:" + NATS_PORT;

    public static void main(String[] args) throws IOException, InterruptedException
    {
        try (final Connection connection = Nats.connect(defaultServer))
        {
            // publish a message to the channel
            for (int i = 0; i < 1; ++i) {
                final String msg = String.format("Hello NATS World %d", i);
                connection.publish(channelName, msg.getBytes());
            }
        }
    }
}
