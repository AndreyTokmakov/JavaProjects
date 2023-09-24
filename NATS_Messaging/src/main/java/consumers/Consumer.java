package consumers;

import io.nats.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

public class Consumer
{
    public static final Integer NATS_PORT = 4222;
    public static final Integer NATS_MGMT_PORT = 8222;
    public static final String channelName = "nats.demo.service";
    private static final String defaultServer = "nats://localhost:" + NATS_PORT;

    private static void consumeAsync() throws IOException, InterruptedException
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

    private static void consumeSynchronous() throws IOException, InterruptedException
    {
        try (final Connection connection = Nats.connect(defaultServer))
        {
            Subscription sub = connection.subscribe(channelName);

            while (true) {
                Message msg = sub.nextMessage(Duration.ofMillis(10_000));

                String response = new String(msg.getData(), StandardCharsets.UTF_8);
                System.out.println("Message: " + response);
            }
        }
    }

    private static void consumeJetStream() throws IOException, InterruptedException, JetStreamApiException {
        try (final Connection connection = Nats.connect(defaultServer))
        {
            final JetStream jetStream = connection.jetStream();

            //jetStream.

            /*
            final PullSubscribeOptions opts = PullSubscribeOptions.builder()
                    .stream("stream")
                    .durable("durable-name")
                    .bind(true)
                    .bind(true)
                    .build();
            */

            final PullSubscribeOptions opts = PullSubscribeOptions.builder()
                    .durable("durable-name-is-optional")
                    .build();
            JetStreamSubscription sub = jetStream.subscribe(channelName, opts);
            List<Message> messages = sub.fetch(100, Duration.ofSeconds(10));

            /*
            for (Message m : messages) {
                m.ack(); // process message
            }
            */
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, JetStreamApiException {
        // consumeAsync();
        consumeSynchronous();
        // consumeJetStream();
    }
}
