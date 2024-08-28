/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* Consumer_Module_Tests.java class
*
* @name    : Consumer_Module_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Mar 7, 2021
****************************************************************************/

package Consumers.Multithread_Consumers;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.function.Consumer;

interface KafkaRecordHandler<T> {
	public T handle(T x, T y);
}


/** Consumer worker class: **/
final class Worker extends Thread {
	
	/** Kafka poll timeout (in seconds): **/
	private static final Duration KAKFA_POLL_TIMEOUT = Duration.ofMillis(1000);
	
	/** Kafka default consumer group name: **/
	private static final String DEFAULT_CONSUMER_GROUP = "KafkaViewerModuleConsumer";
	
	/** **/
	private final AtomicBoolean run = new AtomicBoolean(false);
	
	/** **/
	private final Consumer<? super ConsumerRecord<String, String>> handler;
			
	/* Kafka consumer : */
	private final KafkaConsumer<String, String> consumer;
	
	/* Kafka consumer properties: */
	private final Properties properties = new Properties();

	/** Worker class constructor: **/
	public Worker(List<String> bootstrapServers, 
				  List<String> topics,
				  Consumer<? super ConsumerRecord<String, String>> handler) {	
		this.handler = handler;
		
		properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	    properties.put(CommonClientConfigs.GROUP_ID_CONFIG, DEFAULT_CONSUMER_GROUP);
	    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
	    properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
	    properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	
		consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(topics);
	}

	@Override
	public void run() {
		run.set(true);
		while (true == run.get()) {
			ConsumerRecords<String, String> batch = consumer.poll(KAKFA_POLL_TIMEOUT);
	        for (final ConsumerRecord<String, String> record : batch) {
	        	this.handler.accept(record);
	        }
		}
		
		consumer.unsubscribe();
		consumer.close();
	}
	
	public void close()  {
		this.run.set(false);
	}
}


final class ConsumerModule {
	/** **/
	private Worker consumerWorker = null;
	
	/* Consumer records : */
	private final ArrayList<ConsumerRecord<String, String>> records = new ArrayList<ConsumerRecord<String, String>>();
	
	public void run() {
		
		List<String> topics = new ArrayList<String>(Arrays.asList("tests"));
		List<String> bootstrapServers = new ArrayList<String>(Arrays.asList("127.0.0.1:9092"));
		
		this.consumerWorker = new Worker(bootstrapServers, topics, this::handleRecord);
		consumerWorker.start();
		
		Consumer_Threads_Tests.sleep(20000);
		consumerWorker.close();
	}
	
	private void handleRecord(final ConsumerRecord<String, String> record) {
    	System.out.println("Headers:");
    	record.headers().forEach(h-> System.out.println("      " + h.key() + " = " + new String(h.value(), StandardCharsets.UTF_8)));
    	System.out.println(record.value());
	}
}


public class Consumer_Module_Tests {

	public static void main(String[] args) throws InterruptedException 
	{
		new ConsumerModule().run();

	}
}
