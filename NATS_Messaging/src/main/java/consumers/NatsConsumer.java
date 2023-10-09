package consumers;

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class NatsConsumer
{
    private final String connectString;

    public NatsConsumer(String host, int port)
    {
        this.connectString = String.format("nats://%s:%d", host, port);
    }

    public String getMessage(String channelName,
                             Duration duration) throws InterruptedException, IOException {
        try (final Connection connection = Nats.connect(this.connectString))
        {
            return getMessage(channelName, duration, connection);
        }
    }

    public String getMessage(String channelName,
                             Duration duration,
                             Connection exisingConnection) throws InterruptedException
    {
        Subscription sub = exisingConnection.subscribe(channelName);
        final Message msg = sub.nextMessage(duration);

        if (null != msg) {
            //sub.unsubscribe();
            return new String(msg.getData(), StandardCharsets.UTF_8);
        }

        return "";
    }

    public String readMessages(String channelName) throws  InterruptedException, IOException
    {
        try (final Connection connection = Nats.connect(this.connectString))
        {
            Subscription sub = connection.subscribe(channelName);
            while (true) {
                final Message msg = sub.nextMessage( Duration.ofMillis(3_000));
                if (null == msg) {
                    // System.err.println("Timeout");
                    continue;
                }
                System.out.println("--------------------------------------------------------------");
                System.out.println("SID: " + msg.getSID());
                System.out.println("getHeaders");
                System.out.println("\tname: " + msg.getHeaders().get("name"));
                System.out.println("\twhere_to_reply: " + msg.getHeaders().get("where_to_reply"));


                System.out.println("Subject: " + msg.getSubject());
                System.out.println("getReplyTo: " + msg.getReplyTo());
                System.out.println("getSubscription");
                System.out.println("\tgetQueueName: " + msg.getSubscription().getQueueName());
                System.out.println("\tgetDispatcher: " + msg.getSubscription().getDispatcher());
                System.out.println("\tgetSubject: " + msg.getSubscription().getSubject());


                final String message = new String(msg.getData(), StandardCharsets.UTF_8);
                System.out.println("Payload: "+ message);

                System.out.println();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException, IOException {
        final String channel = "nats.demo.service";
        NatsConsumer consumer = new NatsConsumer("localhost", 4222);

        /*
        final String msg = consumer.getMessage(channel, Duration.ofMillis(3_000));
        System.out.println(msg);
        */

        consumer.readMessages(channel);
    }
}
