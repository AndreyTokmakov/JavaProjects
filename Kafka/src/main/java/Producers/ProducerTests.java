/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* ProducerTests.java class
*
* @name    : ProducerTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Feb 13, 2021
****************************************************************************/

package Producers;

import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/** Kafka test producer: **/
class TestProducer {
	
	public void sendMessage() {
		final String message = "{test_message}", topic = "tests";
		final String server = "127.0.0.1:9092";
		
		/*
		System.out.println("Sending message to 127.0.0.1:9092:");
		System.out.println("	Topic: " + topic);
		System.out.println("	Message: " + message);
		*/
	
		Properties props = new Properties();
		
		props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, server);
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		
	    // props.put(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, transactionTimeoutMs);
	    // props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, transactionalId);
	    // props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotency);
		
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		final Producer<String, String> producer = new KafkaProducer<>(props);
		ProducerRecord<String, String> record  = new ProducerRecord<String, String>(topic, message);
		producer.send(record);
		producer.close();
		
		System.out.println("Done");
	}
}

public class ProducerTests {
	public static void main(String[] args)
	{
		TestProducer producer = new TestProducer();
		producer.sendMessage();

	}
}
