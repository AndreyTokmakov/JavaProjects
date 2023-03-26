package Consumers;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.serialization.StringDeserializer;

import kafka.common.OffsetAndMetadata;

class TestConsumer {
	/** Kafka poll timeout (in seconds): **/
	private final static Duration KAKFA_POLL_TIMEOUT = Duration.ofMillis(1000);
	
	
	/* Kafka consumer : */
	private KafkaConsumer<String, String> consumer = null;
	
	private final AtomicBoolean run = new AtomicBoolean(true);


	public void run() {
		String group = "robotsguard_4";
		List<String> topics = new ArrayList<String>(Arrays.asList("webhook_low_priority"));
		
		// List<String> nodes = new ArrayList<String>(Arrays.asList("127.0.0.1:9092"));
		List<String> nodes = new ArrayList<String>(Arrays.asList("10.62.176.171:9092")); // DSP : ptf01-t01-dsp01.lab.nordigy.ru 
		
		final Properties properties = new Properties();
		properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, nodes.toString().replace("[", "").replace("]", ""));
	    properties.put(CommonClientConfigs.GROUP_ID_CONFIG, group);
	    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
	    properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
	    properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(topics);
		
		while (true == run.get())  {
			ConsumerRecords<String, String> records = consumer.poll(KAKFA_POLL_TIMEOUT);
	        for (final ConsumerRecord<String, String> record : records) {
	        	handleRecord(record);
	        }
	        
	        Map<TopicPartition, OffsetAndMetadata> offsets = consumerOffsets();
	        System.out.println(offsets);

            printWithTxnId("Message remaining: " + messagesRemaining(consumer) + "\n");
		}
		
		consumer.unsubscribe();
		consumer.close();
	}
	
	
	public void Read_From_Partitions() {
		final String group = "SomeTestConsumer";
		final List<String> topics = new ArrayList<String>(Arrays.asList("webhook_low_priority"));
		final List<String> bootstrapServers = new ArrayList<String>(Arrays.asList("10.62.176.171:9092")); 
		
		final Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	    properties.put(ConsumerConfig.GROUP_ID_CONFIG, group);
	    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
	    properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
	    properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		final List<TopicPartition> topicPartitions = new ArrayList<TopicPartition>();
		topics.stream().map(consumer::partitionsFor).collect(Collectors.toList()).forEach(list -> { list.stream().forEach(
				info -> topicPartitions.add(new TopicPartition(info.topic(), info.partition()))
		);});

		final Map<TopicPartition, Long> endOffsets = consumer.endOffsets(topicPartitions);
		consumer.assign(topicPartitions);
        endOffsets.forEach(consumer::seek);

        int max = 60;
		boolean run = true;
		while (true == run && max-- > 0)  {
			final ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
	        for (final ConsumerRecord<String, String> record : records) {
	        	handleRecord(record);
	        	run = false;
	        }
		}
		
		consumer.unsubscribe();
		consumer.close();
	}

	private Map<TopicPartition, OffsetAndMetadata> consumerOffsets() {
		Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
		for (TopicPartition topicPartition : consumer.assignment()) {
			offsets.put(topicPartition, new OffsetAndMetadata(consumer.position(topicPartition), null, null, 0, null));
		}
		return offsets;
	}
	
    private long messagesRemaining(final KafkaConsumer<String, String> consumer) {
        final Map<TopicPartition, Long> fullEndOffsets = consumer.endOffsets(new ArrayList<>(consumer.assignment()));
        // If we couldn't detect any end offset, that means we are still not able to fetch offsets.
        if (fullEndOffsets.isEmpty()) {
            return Long.MAX_VALUE;
        }

        return consumer.assignment().stream().mapToLong(partition -> {
            long currentPosition = consumer.position(partition);
            printWithTxnId("Processing partition " + partition + " with full offsets " + fullEndOffsets);
            if (fullEndOffsets.containsKey(partition)) {
                return fullEndOffsets.get(partition) - currentPosition;
            }
            return 0;
        }).sum();
    }
    
    private void printWithTxnId(final String message) {
        System.out.println("ID: " + message);
    }
	
	private void handleRecord(final ConsumerRecord<String, String> record) {
    	System.out.println("Headers:");
    	for (final Header header: record.headers()) 
    		System.out.println("      " + header.key() + " = " + new String(header.value(), StandardCharsets.UTF_8));
    	System.out.println("\nMessage:");
    	System.out.println(record.value());
	}
}



public class ConsumerTests {

	public static void main(String[] args) {
		TestConsumer consumer = new TestConsumer();
		// consumer.run();
		consumer.Read_From_Partitions();
	}

}
