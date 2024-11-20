package modules;

import lombok.Getter;
import lombok.Setter;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsumerExperiments
{
    private static abstract class ConsumerBase
    {
        final protected BlockingDeque<Message<byte[]>> queue;
        final protected AtomicBoolean stopFlag;

        public ConsumerBase(BlockingDeque<Message<byte[]>> queue,
                            AtomicBoolean stopFlag)
        {
            this.queue = queue;
            this.stopFlag = stopFlag;
        }

        public boolean isStopRequested()
        {
            return !stopFlag.getOpaque();
        }

        public void Stop()
        {
            stopFlag.setOpaque(true);
        }
    }

    private final static class Printer extends ConsumerBase implements Runnable
    {
        public Printer(BlockingDeque<Message<byte[]>> queue,
                       AtomicBoolean stopFlag)
        {
            super(queue, stopFlag);
        }

        public void run()
        {
            Message<byte[]> msg = null;
            while (isStopRequested())
            {
                try {
                    msg = queue.poll(100, TimeUnit.MILLISECONDS);
                    if (null != msg) {

                        System.out.println(new String(msg.getData()));
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    @Getter
    @Setter
    private final static class PulsarConsumer extends ConsumerBase implements Runnable
    {
        private final static int PULSAR_PORT = 6650;
        private String topic;
        private String pulsarHost;

        public PulsarConsumer(BlockingDeque<Message<byte[]>> queue,
                              AtomicBoolean stopFlag,
                              String pulsarHost,
                              String topic) {
            super(queue, stopFlag);
            this.pulsarHost = pulsarHost;
            this.topic = topic;
        }

        public void run()
        {
            try (final PulsarClient client = PulsarClient.builder()
                    .serviceUrl(String.format("pulsar://%s:%d", pulsarHost, PULSAR_PORT))
                    //.authentication(AuthenticationFactory.token(pulsarToken))
                    .build();
                 final Consumer<byte[]> consumer = client.newConsumer()
                         .topic(topic)
                         .subscriptionName("my-subscription")
                         .subscribe())
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
        String topic = "notifications", pulsarHost = "localhost";

        final LinkedBlockingDeque<Message<byte[]>> messages = new LinkedBlockingDeque<Message<byte[]>>();
        final AtomicBoolean stopFlag = new AtomicBoolean(false);

        final ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new PulsarConsumer(messages, stopFlag, pulsarHost, topic));
        service.submit(new Printer(messages, stopFlag));

        TimeUnit.SECONDS.sleep(10);

        stopFlag.set(true);
        service.shutdown();
    }
}
