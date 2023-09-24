package consumers;

import io.nats.client.*;
import io.nats.client.api.ConsumerConfiguration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ConsumerOptions
{
    public static final Integer NATS_PORT = 4222;
    public static final Integer NATS_MGMT_PORT = 8222;
    public static final String channelName = "nats.demo.service";
    private static final String defaultServer = "nats://localhost:" + NATS_PORT;

    public static void main(String[] args) throws IOException, InterruptedException
    {
        final Options options = Options.builder().server(defaultServer).build();
        try (final Connection connection = Nats.connect(options))
        {
            final Dispatcher dispatcher = connection.createDispatcher((msg) -> {
                System.out.printf("%s on subject %s\n", new String(msg.getData(), StandardCharsets.UTF_8), msg.getSubject());
            });

            dispatcher.subscribe(channelName);
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
