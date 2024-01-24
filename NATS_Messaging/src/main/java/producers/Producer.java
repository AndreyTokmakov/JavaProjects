package producers;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import io.nats.client.impl.Headers;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Producer
{
    public static final Integer NATS_PORT = 4222;
    public static final Integer NATS_MGMT_PORT = 8222;
    public static final String channelName = "nats.demo.service";
    public static final String natsHost = "192.168.101.2";
    private static final String defaultServer = "nats://" + natsHost + ":" + NATS_PORT;
    private final String connectString;

    public Producer(String host, int port)
    {
        this.connectString = String.format("nats://%s:%d", host, port);
    }

    public void publish(String channelName,
                        String message) throws IOException, InterruptedException
    {
        try (final Connection connection = Nats.connect(this.connectString))
        {
            publish(channelName, message, connection);
        }
    }

    public void publishNoexcept(String channelName,
                                String message)
    {
        try (final Connection connection = Nats.connect(this.connectString))
        {
            publish(channelName, message, connection);
        }
        catch (final IOException | InterruptedException exc) {
            System.err.println(exc.getMessage());
        }
    }

    public void publish(String channelName,
                        String message,
                        Connection connection)
    {
        connection.publish(channelName, message.getBytes());
    }


    public static void getServerInfo() throws IOException, InterruptedException {
        final String connString = "nats://localhost:" + 4222;

        try (final Connection connection = Nats.connect(connString))
        {
            System.err.println(connection.getConnectedUrl());
            System.err.println(connection.getClientInetAddress() + "\n");

            System.err.println(connection.getServerInfo() + "\n");
            System.err.println(connection.getServers() + "\n");
            System.err.println(connection.getStatus() + "\n");
            System.err.println(connection.getStatistics() + "\n");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException
    {
        final String channel = "nats.demo.service";

        try (final Connection connection = Nats.connect(defaultServer))
        {
            System.err.println(connection.getConnectedUrl());
            System.err.println(connection.getClientInetAddress());
            System.err.println(connection.getServerInfo());
            System.out.println();


            for (int i = 0; i < 1; ++i) {
                final String msg = String.format("Hello NATS World %d", i);

                Headers headers = new Headers();
                headers.add("name", "Andrei");
                headers.add("where_to_reply", "Some test adddess");

                connection.publish(channel, headers, msg.getBytes());
            }
        }

        // getServerInfo();

    }
}
