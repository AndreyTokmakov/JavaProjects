package experiments;

import org.apache.pulsar.client.api.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;


// https://pulsar.apache.org/docs/next/client-libraries-java-use/
public class ConsumerQueue
{
    private static final String PULSAR_HOST = "localhost";
    private static final String SERVICE_URL = String.format("pulsar://%s:6650", PULSAR_HOST);
    private static final String TOPIC_NAME = "notifications";
    private static final String pulsarToken = "<REPLACE_WITH_PULSAR_TOKEN>";

    private final static class Printer implements Runnable
    {
        final BlockingDeque<Message<byte[]>> queue;

        public Printer( BlockingDeque<Message<byte[]>> queue) {
            this.queue = queue;
        }

        public void run()
        {
            Message<byte[]> msg = null;
            while (true)
            {
                try {
                    msg = queue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(msg);
            }
        }
    }

    private final static class PulsarConsumer implements Runnable
    {
        final BlockingDeque<Message<byte[]>> queue;

        public PulsarConsumer( BlockingDeque<Message<byte[]>> queue) {
            this.queue = queue;
        }

        public void run()
        {
            try (final PulsarClient client = PulsarClient.builder()
                     .serviceUrl(SERVICE_URL) // .authentication(AuthenticationFactory.token(pulsarToken))
                     .build();
                 final Consumer<byte[]> consumer = client.newConsumer()
                     .topic(TOPIC_NAME).subscriptionName("my-subscription").subscribe())
            {
                while (true)
                {
                    Message<byte[]> msg = consumer.receive();
                    try {
                        queue.add(msg);
                        consumer.acknowledge(msg);
                    } catch (Exception e) {
                        // Message failed to process, redeliver later
                        consumer.negativeAcknowledge(msg);
                    }
                }
            }  catch (final Exception exc) {
                System.err.println(exc.toString());
            }
        }
    }


    public static void main(String[] args)
    {
        final LinkedBlockingDeque<Message<byte[]>> messages = new LinkedBlockingDeque<Message<byte[]>>();

        final ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(new PulsarConsumer(messages));
        service.submit(new Printer(messages));

        // service.shutdown();
    }
}
