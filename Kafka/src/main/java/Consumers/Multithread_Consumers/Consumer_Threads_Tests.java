/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* Consumer_Threads_Tests.java class
*
* @name    : Consumer_Threads_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Mar 6, 2021
****************************************************************************/

package Consumers.Multithread_Consumers;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.serialization.StringDeserializer;


public class Consumer_Threads_Tests {
	
	public static void main(String[] args) throws InterruptedException 
	{
		TestConsumer consumer = new TestConsumer();
		consumer.run();
	}
	
	protected static void sleep(int timeout) {
		try {
			TimeUnit.MILLISECONDS.sleep(timeout);
		} catch (InterruptedException exc) {
			System.err.println(exc.getMessage());
		}
	}
}

class TestConsumer {
	/** **/
	private final static String group = "robotsguard_4";
	
	/** **/
	private Worker consumerWorker = null;
	
	public void run() throws InterruptedException {
		
		List<String> topics = new ArrayList<String>(Arrays.asList("tests"));
		List<String> bootstrapServers = new ArrayList<String>(Arrays.asList("127.0.0.1:9092"));
		
		this.consumerWorker = new Worker(bootstrapServers, topics);
		consumerWorker.start();
		
		Consumer_Threads_Tests.sleep(10000);
		consumerWorker.close();
	}
	
	//---------------------------------------------------------
	
	/** Consumer worker class: **/
	class Worker extends Thread {
		/** Kafka poll timeout (in seconds): **/
		private final Duration KAKFA_POLL_TIMEOUT = Duration.ofMillis(1000);
		
		/** **/
		private final AtomicBoolean run = new AtomicBoolean(false);
				
		/* Kafka consumer : */
		private KafkaConsumer<String, String> consumer = null;
				
	    /* Kafka properties: */
		private Properties properties = new Properties();
		
		/* Consumer records : */
		// private final ArrayList<ConsumerRecord<String, String>> records = new ArrayList<ConsumerRecord<String, String>>();
		
		/** Worker class constructor: **/
		public Worker(List<String> bootstrapServers, List<String> topics) {	
			properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers.toString().replace("[", "").replace("]", ""));
		    properties.put(CommonClientConfigs.GROUP_ID_CONFIG, group);
		    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
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
		        	// records.add(record);
		        	// handleRecord(records.get(records.size() - 1));
		        	handleRecord(record);
		        }
			}
			
			consumer.unsubscribe();
			consumer.close();
		}
		
		public void close()  {
			this.run.set(false);
		}
		
		private void handleRecord(final ConsumerRecord<String, String> record) {
	    	System.out.println("Headers:");
	    	record.headers().forEach(h-> System.out.println("      " + h.key() + " = " + new String(h.value(), StandardCharsets.UTF_8)));
	    	System.out.println(record.value());
		}
	}
}