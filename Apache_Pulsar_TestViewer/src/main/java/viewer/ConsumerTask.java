package viewer;

import lombok.Getter;
import lombok.Setter;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
public class ConsumerTask extends ConsumerBase implements Runnable
{
    private final static int PULSAR_PORT = 6650;
    private String topic;
    private String pulsarHost;

    public ConsumerTask(BlockingDeque<Message<byte[]>> queue,
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
