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
		
		/**** 1682: ****/
		// nodes.add("10.62.182.96:9092");   // DSP : ptf01-t03-dsp01.lab.nordigy.ru
		// nodes.add("10.62.182.111:9092");  // TNS : ptf01-t03-tns01.lab.nordigy.ru 
		
		/**** 1600: ****/
		// nodes.add("10.62.176.171:9092");  // DSP : ptf01-t01-dsp01.lab.nordigy.ru
		// nodes.add("10.62.180.105:9092");  // TNS : ptf01-t01-tns01.lab.nordigy.ru
		
		/**** 1601: ****/ 
		// nodes.add("10.62.180.106:9092");  // TNS : ptf01-t02-tns01.lab.nordigy.ru
		// nodes.add("10.62.176.212:9092");  // DSP : ptf01-t02-dsp01.lab.nordigy.ru
		 
		/** 557 **/
		// pop1
		// nodes.add("10.62.16.80:9092");
		// nodes.add("10.62.16.82:9092");
		// nodes.add("10.62.16.83:9092");
		
		// nodes.add("10.62.138.73:9092"); // fun04-c01-dsp01.lab.nordigy.ru
		nodes.add("10.62.80.50:9092"); // TNS - 557
			 
		/**** 1098 ****/
		// nodes.add("10.56.21.16:9092");  // ssp01-c01-tns01.lab.nordigy.ru
		// nodes.add("10.56.21.17:9092");  // ssp01-c01-tns02.lab.nordigy.ru
		// nodes.add("10.56.21.18:9092");  // ssp01-c01-tns03.lab.nordigy.ru
		

		/*************** Calls DSP topics ************************/

		// topics.add("call-sessions_local");
		// topics.add("call-sessions_remote");
		// topics.add("rmx-to-cns-events_local");
		// topics.add("adg-to-cns-events_local");
		// topics.add("missed-and-incoming-calls_local");
		// topics.add("aggregated-status_local");


		// topics.add("csg-subscription-events_local");
		
		/*************** ADT topics ************************/
		
		/*
		topics.add("adt-to-adg-events_local");
		topics.add("adt-to-adg-events_remote");
		topics.add("adt-to-csg-events_local");
		topics.add("adt-to-csg-events_remote");
		topics.add("adt-to-cns-grants-events_local");
		topics.add("adt-to-cns-grants-events_remote");
		topics.add("adt-to-cns-events_local");
		topics.add("adt-to-cns-events_remote");
		topics.add("adt-to-cns-extension-changes-events_remote");
		topics.add("adt-to-cns-extension-changes-events_local");
		topics.add("adt-to-dsh-events_local");
		topics.add("adt-to-dsh-events_remote");
		topics.add("adt-to-cpx-events_local");
		topics.add("adt-to-cpx-events_remote");
		topics.add("adt-to-tpg-events_remote");
		topics.add("adt-to-tpg-events_local");
		topics.add("adt-to-tsx-events_remote");
		topics.add("adt-to-tsx-events_local");
		*/
		
		/*************** TNS topic ************************/
		// topics.add("BXX_Configuration_Notifications_global");
		topics.add("Call_Session_Notifications_local");
		// topics.add("Call_Action_Notifications_local");
		// topics.add("DND_Status_Notifications_local");
		
		/*************** high priority topics ************************/
		// topics.add("apns_high_priority");
		// topics.add("fcm_high_priority");
		// topics.add("pubnub_high_priority");
		// topics.add("webhook_high_priority");
		// topics.add("wsg_high_priority");
		// topics.add("apns_high_priority");

		/*************** low priority topics ************************/

		/*
		topics.add("pubnub_low_priority");
		topics.add("webhook_low_priority");
		topics.add("fcm_low_priority");
		topics.add("wsg_low_priority");
		topics.add("apns_low_priority");
		*/
		
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
