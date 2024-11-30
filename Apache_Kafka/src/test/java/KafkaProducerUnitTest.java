import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.*;


public class KafkaProducerUnitTest
{
    private MockProducer<String, String> mockProducer;
    private Producer<String, String> producer;

    private void buildMockProducer(boolean autoComplete)
    {
        mockProducer = new MockProducer<String, String>(autoComplete,
                new StringSerializer(), new StringSerializer());
    }

    @Test
    void givenKeyValue_whenSend_thenVerifyHistory() throws ExecutionException, InterruptedException
    {
        buildMockProducer(true);

        final String message = "{test_message}", topic = "events";
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);

        producer = mockProducer;
        Future<RecordMetadata> recordMetadataFuture = producer.send(record);

        // System.out.println(mockProducer.history().getFirst());

        assertEquals(1, mockProducer.history().size());
        assertTrue(mockProducer.history().getFirst().value().contains("message"));
        // assertTrue(recordMetadataFuture.get().partition() == 0);
    }
}
