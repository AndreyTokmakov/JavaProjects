/**
 * 
 */
package kafka_tests;

/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

//import org.apache.jena.shared.uuid.*;

import org.apache.kafka.consumer.ConsumerConnector;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.producer.KeyedMessage;
import kafka.utils.ZkUtils;
import kafka_tests.Drop;

import org.I0Itec.zkclient.ZkClient;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import scala.collection.JavaConversions;
import scala.collection.Seq;

@SuppressWarnings("unused")
class Pair {
	public String ip;
	public int  ipId;

	public Pair(String ip, int ipId) {
		this.ip = ip;
		this.ipId = ipId;
	}	
};

class FinesBundle {
	public int ipId;
	public List<Fine> fines;

	public FinesBundle(int ipId,  List<Fine> fines) {
		this.ipId = ipId;
		this.fines = fines;
	}	
};


public class KafkaTester {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String GetFileLinesEx(final String fileName) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		String text = "";
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				text += line;
			}
		}
		return text;
	}
	
	public static String getTimeUtc() {
		TimeZone tm_utc = TimeZone.getTimeZone("UTC");
		DateFormat df_utc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df_utc.setTimeZone(tm_utc);
		return df_utc.format(new Date());
	}
	
	public static void SendMessageTo_FromCMD(String[] args) {
		if (3 != args.length) {
			System.out.println("Wrong number of params");
			System.out.println("Expected input:java -jar <JAR_FILE> <Kafka_node_IP> <topic_name> <message_file_path>");
			System.out.println("	IP");
			return;
		}
		
		String address = String.format("%s:9092", args[0]), topic = args[1], message = "";
		try {
			 message = KafkaTester.GetFileLinesEx(args[2]);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		System.out.println("Sending message to " + address);
		System.out.println("	Topic: " + topic);
		System.out.println("	Message: " + message);
	
		Properties props = new Properties();
		props.put("bootstrap.servers", address);
		
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		final Producer<String, String> producer = new KafkaProducer<>(props);
		ProducerRecord<String, String> record  = new ProducerRecord<String, String>(topic, message);
		producer.send(record);
		producer.close();
		
		System.out.println("Done");
	}
	
	
	public static void SendMessageToKafka() {
		final String message = "{test_message}", topic = "tests";
		final String server = "127.0.0.1:9092";
		

	
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

	public static void main(String[] args) throws InterruptedException {
		
		//MessageBusUtils U = new MessageBusUtils();
		
		 SendMessageToKafka();
		// SendMessageTo_FromCMD(args);
		

		
		//ConsumerEvents();
		//ConsumerEvents1();
		
		
		//ProduceEvents();
		//ProduceEventsLoop();
		
		// Consume();
		
		//ConsumerEvents();
		//GetTopicsList();
		//ConsumerEx();
		//ProduceEvents();
		
		//AnomalyStatsRealTime();
		//RealTimeTrafficAndAttacks();
		//AnomalyStats();
		
		//ProxyLogTests();
		
		//KeyTests();
		//TestFines();
		//TestBlocks();
		//TestFinesLoadTest();
		//TestMonitoring();
		
		//AttackStats();
		
		//SSL_Stats();
		//SSLAnomaly();
		
		//GEO_IP_STATS_Generator();
	}
	

	public static void Consume() {
		List<String> topics = new ArrayList<String>();
		List<String> nodes = new ArrayList<String>();
		Properties properties = new Properties();
		KafkaConsumer<String, String> consumer = null;
		
		nodes.add("127.0.0.1:49198");
		
		topics.add("test_topic");
		
	    properties.put("group.id", "TESTSSTSS");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("bootstrap.servers", nodes.toString().replace("[", "").replace("]", ""));

		
		consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(topics);
		
		while (true)  {
			ConsumerRecords<String, String> records = consumer.poll(100);
	        for (ConsumerRecord<String, String> record : records) {
	        	// System.out.println(record.key());
	        	System.out.println(record.value());
	        }
		}

		//consumer.unsubscribe();
		//consumer.close();
	}

	public static void GetTopicsList() {
	    //ZkClient zkClient = new ZkClient("test-kafka.kur.kdp:2181");
		//ZkClient zkClient = new ZkClient("cassandra-1.test.kdp:2181");
		
	    //List<String> topics = JavaConversions.asJavaList(ZkUtils.getAllTopics());
	    //for (String T : topics)
	    //	System.out.println(T);
	}


	public static List<String> GetRandomIPList(int count) {
		List<String> list = new ArrayList<String>();
		int randomNum = 0;
		long result = 0;
		Random rand = new Random();
		for (int i = 0 ; i < count; i++) {
			randomNum = rand.nextInt();
			if (0 > randomNum) 
				result = (long)Integer.MAX_VALUE - (long)randomNum;
			else
				result = (long)randomNum;
			list.add(String.valueOf(result));
		}
		return list;
	}
	
	public static List<Integer> GetResourceIDsList() {
		List<Integer> list = new ArrayList<Integer>();
	
		return list;
	}

	
	@SuppressWarnings("static-access")
	public static void TestMonitoring()
	{
		Properties props = new Properties();
		props.put("bootstrap.servers", "cassandra-1.test.kdp:9092, cassandra-2.test.kdp:9092, cassandra-3.test.kdp:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		String message= "", topic = "monitoring_tokmakov";
		Producer<String, String> producer = new KafkaProducer<>(props);		
		
		int clientId = 182, resourceId = 1317, groupId = 284;
		String timestamp = getTimeUtc();
		

		for (int i = 0; i < 1; i++) {

			message = "";

			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);
			producer.send(record);
		}
		

		producer.close();
	}
	
	@SuppressWarnings("static-access")
	public static void ProxyLogTests()
	{
		Properties props = new Properties();
		props.put("bootstrap.servers", "cassandra-1.test.kdp:9092, cassandra-2.test.kdp:9092, cassandra-3.test.kdp:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		String message= "", topic = "proxylog_tokmakov";
		Producer<String, String> producer = new KafkaProducer<>(props);		
		int nodeId = 5;
		
		
		String host = "novayagazeta.ru", ip = "103.5.149.8";
		//String host = "MIX7YA1.RU", ip = "103.5.149.72";
		
		
		//while (true) {
			message = "";

			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);
			producer.send(record);
			
			try {
				Thread.currentThread().sleep(60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//}

		producer.close();
	}
	
	@SuppressWarnings("static-access")
	public static void TestBlocks()
	{
		Properties props = new Properties();
		props.put("bootstrap.servers", "cassandra-1.test.kdp:9092, cassandra-2.test.kdp:9092, cassandra-3.test.kdp:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		String message= "", topic = "blocks_tokmakov";
		Producer<String, String> producer = new KafkaProducer<>(props);
		
		int ttl = 120;
		int nodeId = 123;
		int ipId = 59;
		String client = "1869573999";
		//String timestamp = getTimeUtc();
		String timestamp = "2018-07-01 00:04:00";
		
		message  = "";
		System.out.println(message);
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);
		producer.send(record);
		
		producer.close();
	}
	
	@SuppressWarnings("static-access")
	public static void TestFines()
	{
		Properties props = new Properties();
		props.put("bootstrap.servers", "cassandra-1.test.kdp:9092, cassandra-2.test.kdp:9092, cassandra-3.test.kdp:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		String message= "", topic = "fines_tokmakov";
		Producer<String, String> producer = new KafkaProducer<>(props);
		
		int ttl = 10;
		int nodeId = 123;
		int ipId = 1317;
		String client = "1869573999";
		//String client = "1869573999";
		//String timestamp = "2017-09-18 08:30:00";
		String timestamp = getTimeUtc();
		
		for (int i = 0; i < 2; i++) {
			message  = "";
			System.out.println(message);
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);
			producer.send(record);
		}
		producer.close();
		
		
		
	}
	
	@SuppressWarnings("static-access")
	public static void TestFinesLoadTest()
	{
		Properties props = new Properties();
		props.put("bootstrap.servers", "cassandra-1.test.kdp:9092, cassandra-2.test.kdp:9092, cassandra-3.test.kdp:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		String message= "", topic = "fines_fv";
		Producer<String, String> producer = new KafkaProducer<>(props);
		List<Integer> ipIds = GetResourceIDsList();
		List<String> clients = GetRandomIPList(2000); 
		
		long finesTotal = 0;

		String clientIP = "";
		Iterator<String> iter = clients.iterator();
		while (true ) {
			for (int ipId : ipIds) {

				if (false == iter.hasNext()) 
					iter = clients.iterator();
				clientIP = iter.next();
				

				message  = "";;
				ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);
				producer.send(record);
				
				finesTotal++;
				if (finesTotal > 1000 - 1)
					break;
			}
			if (finesTotal > 1000 - 1)
				break;
		}
		producer.close();
	}
	
	

	public static void KeyTests() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "cassandra-1.test.kdp:9092, cassandra-2.test.kdp:9092, cassandra-3.test.kdp:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		String message= "", topic = "fines_tokmakov";
		Producer<String, String> producer = new KafkaProducer<>(props);
		

		Map<String, String> messages = new HashMap<String, String>();
		messages.put("1111", "Message1");
		messages.put("5555", "Message2");
		messages.put("9999", "Message3");
		
		while (true) {
			for (Entry<String, String> T : messages.entrySet())
			{
				ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, T.getKey(), T.getValue());
				producer.send(record);
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Send");
			}
		}
		//producer.close();
	}
	
	
	@SuppressWarnings("static-access")
	public static void SSL_Stats() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "cassandra-1.test.kdp:9092, cassandra-2.test.kdp:9092, cassandra-3.test.kdp:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		String message= "", topic = "statistics_tokmakov";
		Producer<String, String> producer = new KafkaProducer<>(props);
		
		
		message = "";
		
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);
		producer.send(record);

		producer.close();
	}
	
	@SuppressWarnings("static-access")
	public static void SSLAnomaly() throws InterruptedException
	{
		Properties props = new Properties();
		props.put("bootstrap.servers", "cassandra-1.test.kdp:9092, cassandra-2.test.kdp:9092, cassandra-3.test.kdp:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		String message= "", topic = "statistics_tokmakov";
		Producer<String, String> producer = new KafkaProducer<>(props);
		
		
		int year = 2018, month = 9, day = 25, hour = 6, min = 35;
		List<Integer> values = Arrays.asList(100, 150, 200, 550, 1000, 2500, 5000, 10000, 20000, 20000, 20000, 20000, 20000, 20000, 10000, 5000, 2500, 1000, 550, 200, 100, 50);	
		for (int v : values) {
			message = MessageBusUtils.BuildStatisticsMessage_SSLStats_v1(62, 1319, 122, 2, v, 125000, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
																         MessageBusUtils.TimeStamp(year, month, day, hour, min++));
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);
			producer.send(record);

			Thread.currentThread().sleep(10 * 1000);
		}
		
		
		producer.close();
	}
	

	
	@SuppressWarnings("static-access")
	public static void AttackStats() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "cassandra-1.test.kdp:9092, cassandra-2.test.kdp:9092, cassandra-3.test.kdp:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		String message= "", msgData = "", statistics = "statistics_tokmakov";
		Producer<String, String> producer = new KafkaProducer<>(props);
		String timestamp = getTimeUtc();
		
		//msgData = "491,RU,udp,10000,1000000,600000,60000000,20,20," + ;
		message = MessageBusUtils.BuildStatisticsMessage_GeoIPStat(7,1317,"RU","udp", 10000,1000000,600000,60000000,20,20, timestamp );
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(statistics, message);
		producer.send(record);
		
		
		producer.close();
	}
	
	@SuppressWarnings("static-access")
	public static void GEO_IP_STATS_Generator() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "cassandra-1.test.kdp:9092, cassandra-2.test.kdp:9092, cassandra-3.test.kdp:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		String message= "", statistics = "statistics_tokmakov";
		Producer<String, String> producer = new KafkaProducer<>(props);
		String timestamp = getTimeUtc();
		
		long total = 0;
		for (int month = 5; month <= 8; month++) {
			for (int day = 1; day <= 28; day++) {
				for (int hour = 0; hour <= 23; hour++) {
					for (int minute = 0; minute <= 59; minute++) {
						
						message = MessageBusUtils.
								BuildStatisticsMessage_TCPStats(13, 1317, 1450, 1, 5550, 544628, 3692, 609320, 1, 89, 12.33, 92.50, 
														9077.13, 1.00, 1, MessageBusUtils.TimeStamp(2018, month, day, hour, minute));
						ProducerRecord<String, String> record = new ProducerRecord<String, String>(statistics, message);
						producer.send(record);
						
						try { Thread.currentThread().sleep(10000); } catch (InterruptedException e) { e.printStackTrace();}

					}
				}
			}
		}
		System.out.println(total);
		producer.close();
	}
	
	@SuppressWarnings("static-access")
	public static void ProduceEvents() {
		Properties props = new Properties();
		//props.put("bootstrap.servers", "100.99.4.168:9092");
		props.put("bootstrap.servers", "127.0.0.1:9092");
		
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		final String msg = "TEST_MESSAGE22";
		final String topic = "TutorialTopic";
		final Producer<String, String> producer = new KafkaProducer<>(props);
		ProducerRecord<String, String> record  = new ProducerRecord<String, String>(topic, msg);
		//System.out.println(msg);
		
		record = new ProducerRecord<String, String>(topic, msg);
		producer.send(record);
		
		producer.close();
	}
	
	@SuppressWarnings("static-access")
	public static void ProduceEventsLoop() {
		Properties props = new Properties();
		//props.put("bootstrap.servers", "100.99.4.168:9092");
		props.put("bootstrap.servers", "127.0.0.1:9092");
		
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		final String msg = "TEST_MESSAGE";
		final String topic = "TutorialTopic";
		final Producer<String, String> producer = new KafkaProducer<>(props);
		ProducerRecord<String, String> record  = new ProducerRecord<String, String>(topic, msg);
		System.out.println(msg);
		
		for (int i = 0 ; i < 1000; i++) {
			record = new ProducerRecord<String, String>(topic, msg);
			producer.send(record);
		}
		
		producer.close();
	}
	
	
	public static void ConsumerEx() {
		
		Properties properties = new Properties();
		//properties.put("zookeeper.connect", "cassandra-1.test.kdp:2181");
		properties.put("bootstrap.servers", "cassandra-1.test.kdp:9092, cassandra-2.test.kdp:9092, cassandra-3.test.kdp:9092");
		properties.put("group.id", "andtokm_kafka_veiwer_group");
		properties.put("partition.assignment.strategy", "roundrobin");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		

		System.out.println("111");
		consumer.close();

	}
	
	@SuppressWarnings("resource")
	public static void ConsumerEvents1() {
	
	}
	
	public static void ConsumerEvents() 
	{

		List<String> nodes = new ArrayList<String>();
		//nodes.add("cassandra-1.test.kdp:9092");
		nodes.add("127.0.0.1:9092");
		//nodes.add("cassandra-3.test.kdp:9092");
		

		List<String> topics = new ArrayList<String>();
		topics.add("TutorialTopic");

		final String group = "ATOKMAKOV_CONSUMER_GROUP";


		Properties properties = new Properties();
		properties.put("group.id", group);
		properties.put("bootstrap.servers", nodes.toString().replace("[", "").replace("]", ""));
		//properties.put("partition.assignment.strategy", "roundrobin");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		properties.put("enable.auto.commit", "false");
		properties.put("auto.offset.reset","none");
		//properties.put("auto.commit.enable","false");
	    
	    KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(topics);
		
		while (true)  {
			ConsumerRecords<String, String> records = consumer.poll(0);
	        for (ConsumerRecord<String, String> record : records) {

	        	System.out.println(record.value());
	        	System.out.println(record.offset());
	        }
		}

		//consumer.unsubscribe();
		//consumer.close();
	}
}
*/
