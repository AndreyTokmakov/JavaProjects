package experiments;

import org.apache.pulsar.client.api.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


// https://pulsar.apache.org/docs/next/client-libraries-java-use/
public class ConsumerQueue
{
    // private static final String PULSAR_HOST = "localhost";
    private static final String PULSAR_HOST = "192.168.101.2";
    private static final String SERVICE_URL = String.format("pulsar://%s:6650", PULSAR_HOST);
    private static final String pulsarToken = "<REPLACE_WITH_PULSAR_TOKEN>";

    // private static final String TOPIC_NAME = "notifications";
    private static final String TOPIC_NAME = "persistent://OPNX-V1/ME-POSTTRADE/CMD-OUT";
    // private static final String TOPIC_NAME = "non-persistent://OPNX-V1/ME-POSTTRADE/HEARTBEAT";

    private static class Task
    {
        final protected BlockingDeque<Message<byte[]>> queue;
        final protected AtomicBoolean stopFlag;

        public Task(BlockingDeque<Message<byte[]>> queue, AtomicBoolean stopFlag)
        {
            this.queue = queue;
            this.stopFlag = stopFlag;
        }

        protected boolean isStopRequested()
        {
            return !stopFlag.getAcquire();
        }
    }

    private final static class Printer extends Task implements Runnable
    {
        public Printer(BlockingDeque<Message<byte[]>> queue, AtomicBoolean stopFlag) {
            super(queue, stopFlag);
        }

        public void run()
        {
            Message<byte[]> message = null;
            while (isStopRequested())
            {
                try {
                    message = queue.poll(100, TimeUnit.MILLISECONDS);
                    if (null != message)
                    {
                        System.out.println("Properties:");
                        for (final Map.Entry<String, String> entry: message.getProperties().entrySet()) {
                            System.out.println("\t" + entry.getKey() + " = " + entry.getValue());
                        }

                        System.out.println(new String(message.getData()));
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private final static class PulsarConsumer extends Task implements Runnable
    {
        public PulsarConsumer(BlockingDeque<Message<byte[]>> queue, AtomicBoolean stopFlag) {
            super(queue, stopFlag);
        }

        public void run()
        {
            try (final PulsarClient client = PulsarClient.builder()
                     .serviceUrl(SERVICE_URL) // .authentication(AuthenticationFactory.token(pulsarToken))
                     .build();
                 final Consumer<byte[]> consumer = client.newConsumer()
                         .topic(TOPIC_NAME)
                         .subscriptionName("my-subscription_" +  LocalDateTime.now()).subscribe())
            {
                while (isStopRequested())
                {
                    Message<byte[]> msg = consumer.receive(1, TimeUnit.SECONDS);
                    try {
                        if (null != msg) {
                            queue.add(msg);
                            consumer.acknowledge(msg);
                        }
                    } catch (Exception e) {
                        // Message failed to process, redFeliver later
                        consumer.negativeAcknowledge(msg);
                    }
                }
            }  catch (final Exception exc) {
                System.err.println(exc.toString());
            }
        }
    }


    public static void main(String[] args) throws InterruptedException
    {
        final LinkedBlockingDeque<Message<byte[]>> messages = new LinkedBlockingDeque<Message<byte[]>>();
        final AtomicBoolean stopFlag = new AtomicBoolean(false);

        final ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new PulsarConsumer(messages, stopFlag));
        service.submit(new Printer(messages, stopFlag));

        TimeUnit.SECONDS.sleep(60);

        stopFlag.set(true);
        service.shutdown();
    }
}
