//============================================================================
// Name        : Consumer.java
// Created on  : Aug 08, 2017
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : 
//============================================================================

package viewer.kafka;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JTextArea;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.metrics.KafkaMetric;
import org.apache.kafka.common.serialization.StringDeserializer;

import viewer.ui.JStatusBar;

/** @class Consumer **/
@SuppressWarnings("unused")
public class Consumer implements Runnable {
	/** Kafka poll timeout (in seconds): **/
	private final static Duration KAKFA_POLL_TIMEOUT = Duration.ofMillis(1000);
	
    /* Kafka properties: */
	private Properties properties = new Properties();
	
	/* Kafka consumer : */
	private KafkaConsumer<String, String> consumer = null;
	
	/* Thread handle: */
	private Thread _thread;
	
	/* Run thread flag: */
	private final AtomicBoolean run = new AtomicBoolean(true);
	
	/* Suspended thread flag: */
	private final AtomicBoolean suspended = new AtomicBoolean(false);
	
	/* List of kafka topics to listen : */
	private List<String> topics = new ArrayList<String>();
	
	/* List of kafka cluster nodes: */
	private List<String> nodes = new ArrayList<String>();
	
	/* Kafka consumer group name : */
	private String group = "robotsguard_4";
	
	/* JTextArea instance : */
	private JTextArea textField;
	
	/* Status bar instance : */
	private JStatusBar statusBar;
	
	/* */
	private long mgsReceivedCountTotal = 0;
	
	/* */
	private long mgsReceivedTopic = 0;
	
	/** **/
	private Map<String, Long> rcvdMgsByTopics = new HashMap<String, Long>();
	
	/** Consumer class default constuctor : 
	 * @param responseField **/
	public Consumer(JTextArea responseField, JStatusBar statusBar) {
		/* */
		this.textField = responseField;
		this.statusBar = statusBar;

		nodes.add("192.168.101.2:9092");

		topics.add("events");


		/* */
	    properties.put(CommonClientConfigs.GROUP_ID_CONFIG, this.group);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	}

	public void start ()  {
		/** Starting thread. **/
		run.set(true);
		if (_thread == null) {
			_thread = new Thread(this);
			_thread.start ();
		}
	}
	
	/** **/
	public synchronized void Suspend() {
		suspended.set(true);
	}
	
	/** **/
	public synchronized void Stop() {
		run.set(false);
	}
	
	/** **/
	synchronized void resume() {
		suspended.set(false);
		notify();
	}
	
	/** **/
	@Override
	public void run() {
		/** **/
		properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, this.nodes.toString().replace("[", "").replace("]", ""));
		consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(topics);
		
		/** **/
		statusBar.SetStatusOnline();
		statusBar.setNodesInfo("Nodes : " + nodes.toString() + "    Topics : " + topics.toString());
		
		/** **/
		rcvdMgsByTopics.clear();
		for (String topic : this.topics)
			rcvdMgsByTopics.put(topic, 0L);
		
		/** **/
		while (true == run.get())  {
			ConsumerRecords<String, String> records = consumer.poll(KAKFA_POLL_TIMEOUT);
	        for (ConsumerRecord<String, String> record : records) {
	        	/** **/

	        	this.AddLine("Headers:");
	        	for (final Header header: record.headers()) 
	        		this.AddLine("      " + header.key() + " = " + new String(header.value(), StandardCharsets.UTF_8));
	        	this.AddLine(record.value());
	        	this.AddLine("");
	        	
	        	
	        	/** **/
	        	this.textField.setCaretPosition(textField.getDocument().getLength());
        		/** **/
        		this.mgsReceivedCountTotal++;
        		/** **/
        		if (true == rcvdMgsByTopics.containsKey(record.topic())) {
        			this.mgsReceivedTopic = rcvdMgsByTopics.get(record.topic()) + 1;
        		} else { 
        			this.mgsReceivedTopic = 1;
        		}
        		rcvdMgsByTopics.put(record.topic(), this.mgsReceivedTopic);
        		
        		/** Suspend thread synchronization block : **/
        		synchronized(this) { 
        			while (suspended.get()) { 
        				try { wait(); }  catch (InterruptedException e) { e.printStackTrace(); }
        			}
        		}
	        }
		}
		/** **/
		consumer.unsubscribe();
		consumer.close();
		/** **/
		statusBar.SetStatusOffline();
		statusBar.setNodesInfo("");
		/** **/
		_thread = null;
	}
	
	/** **/
	protected void Parse(String text)
	{
		/*if (text.contains("fine")) {
			int posBeg = text.indexOf("\"ttl\":");
			int posEnd = text.indexOf(",", posBeg);
			int fine = Integer.valueOf(text.substring(posBeg + 6, posEnd));

			String tmp = "TTL = "  + String.valueOf(fine) + " . Delta = " + String.valueOf(fine - 600);
			this.AddLine(tmp);
		}*/
		
		if (text.contains("1869573999") || text.contains("3739147998") || text.contains("2071690107"))
			this.AddLine(text);
	}
	
	/** **/
	protected void AddLine(String text) {
		textField.append(text +  "\n");
	}
	
	/** @return the rcvdMgsByTopics : **/
	public Map<String, Long> getRcvdMgsByTopics() {
		return rcvdMgsByTopics;
	}
	
	/** @return the group: **/
	public String getGroup() {
		return group;
	}

	/** @param group the group to set: **/
	public void setGroup(String group) {
		this.group = group;
	}
	
	/** @return the list of topics: **/
	public List<String> getTopics() {
		return this.topics;
	}
	
	/** @param kafka cluster topic name: **/
	public void addTopic(String topic) {
		this.topics.add(topic);
	}
	
	/** @param set topics : Clear the old ones and define the new topics: **/
	public void setTopics(final List<String> topics) {
		this.topics = topics;
	}
	
	/** Clear topic list: **/
	public void clearTopics() {
		this.topics.clear();
	}
	
	/** @return the list of nodes: **/
	public List<String> getNodes() {
		return this.nodes;
	}
	
	/** @param kafka cluster node address <IP:PORT> : **/
	public void addNode(String node) {
		this.nodes.add(node);
	}
	
	/** @param set nodes : Clear the old ones and define the new topics: **/
	public void setNodes(String[] nodes) {
		this.nodes.clear();
		for (String node : nodes)
			this.nodes.add(node);
	}
	
	/** Clear : **/
	public void Clear() {
		this.mgsReceivedCountTotal = 0;
		this.mgsReceivedTopic = 0;
		this.rcvdMgsByTopics.clear();
	}
	
	public String GetTopics() {
		String result = "";
		
		Properties config = new Properties();
		config.put("group.id", this.group);
		config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		config.put("bootstrap.servers", this.nodes.toString().replace("[", "").replace("]", ""));
		
		KafkaConsumer<String, String> T = new KafkaConsumer<String, String>(config);
		for (Entry<String, List<PartitionInfo>> entry : T.listTopics().entrySet())
			result += entry.getKey() + " = " + entry.getValue() + "\n";
		T.close();
	    return result;
	}
	
	/** @return the mgsReceivedCountTotal : **/
	public long getMgsReceivedCountTotal() {
		return mgsReceivedCountTotal;
	}


	public void Info()
	{
	    
    
    
	    /*for (Entry<MetricName, ? extends Metric> entry : kafkaConsumer.metrics().entrySet()) {
	    	System.out.println(entry.getKey());
	    	KafkaMetric m = (KafkaMetric)entry.getValue();
	    	System.out.println(m.value());
	    	System.out.println(m.config().tags());
	    	System.out.println("------------------------------------------------------");
	    }
	    kafkaConsumer.close();*/
    
	    /*
	    while (true) {
	        ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
	        for (ConsumerRecord<String, String> record : records) {
	        	System.out.println(record.value());
	        }
	    }*/
	}
}
