//============================================================================
//Name        : StatisticsMBMessagesBuilder.java
//Created on  : Jan 25, 2017
//Author      : Tokmakov Andrey
//Version     :
//Copyright   : Your copyright notice
//Description :
//============================================================================

package kafka_tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;

/** @author tokmakov                   **/
/** @class StatisticsMBMessagesBuilder **/
public class MessageBusUtils {
	/** **/
	private static final SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** **/
	private static final TimeZone timeZoneUTC = TimeZone.getTimeZone("UTC");
	/** **/
	private static final DateFormat fineDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	/** StatisticsMBMessagesBuilder class constructor : **/
	public MessageBusUtils() {
		// TODO Auto-generated constructor stub
		fineDateFormat.setTimeZone(timeZoneUTC);
	}
	
	public static String getFineTimestamp() {
		return fineDateFormat.format(new Date());
	}

	public static String TimeStamp(int year, int month, int day, int hour, int min) {
		return timestampFormat.format(new GregorianCalendar(year, month - 1, day, hour, min, 0).getTime());
	}
	
	public static String GetTimeUTC() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(date);
	}
	
	public static String GetTimeUTCWithOffset(int minutes) {
		Date date = new Date();
		if (date.getMinutes() >= minutes)
			date.setMinutes(date.getMinutes() - minutes);
		else {
			date.setHours(date.getHours() - 1);
			date.setMinutes(date.getMinutes() + 60 - minutes);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(date);
	}
	
	/** BuildStatisticsMessage_GeoIPStat : **/
	public static String BuildStatisticsMessage_GeoIPStat(int nodeId,  int ipId, String country, String protocol,
      	  												  int sendPackets, long sendBytes, int rcvdPackets,
      	  												  long rcvdBytes, int ipsCount, int synCount, String time) {
		String data = "\"" + ipId  + "," 
					 	   + country  + "," 
					 	   + protocol   + "," 
					 	   + sendPackets  + ","
					 	   + sendBytes  + ","
					 	   + rcvdPackets  + ","
					 	   + rcvdBytes  + ","
					 	   + ipsCount  + ","
					 	   + synCount + "," 
					 	   + time + "\"";
		String result = "{\"type\": \"geoipstat\", "
					     + "\"node_id\": " + nodeId + ", "
					     + "\"data\": {\"ip_id\": [" + ipId + "],"
					     + "\"data\": " + data + ", "
					     + "\"type\": {\"import_schema_id\": 20, \"aggregation_schema_id\": [6]}},"
					     + "\"uid\" : \"" + UUID.randomUUID() + "\"}";
		return result;
	}
	
	/** BuildStatisticsMessage_TestStat : **/
	public static String BuildStatisticsMessage_TCPStats(int nodeId, int ipId, int connections, int addresses, int receivedPackets, 
														 int receivedData, int sendPackets, int sendData, 
														 int maxAllTime, int maxIdleTime, 
														 double maxCps, double maxPps, double maxBps, double maxConnections,
														 int uniqueAddresses, String time) {
		String data = "\"" + ipId  + "," 
					 	   + time  + "," 
					 	   + connections + ","
					 	   + addresses + ","
					 	   + receivedPackets + ","
					 	   + receivedData + ","
					 	   + sendPackets + ","
					 	   + sendData + ","
					 	   + maxAllTime + ","
					 	   + maxIdleTime + ","
					 	   + maxCps + ","
					 	   + maxPps + ","
					 	   + maxBps + ","
					 	   + maxConnections + ","
					 	   + uniqueAddresses + "\"";
		String result = "{\"type\": \"tcpstat\", "
					     + "\"node_id\": " + nodeId + ", "
					     + "\"data\": {\"ip_id\": [" + ipId + "],"
					     + "\"data\": " + data + ", "
					     + "\"type\": {\"import_schema_id\": 11, \"aggregation_schema_id\": []}},"
					     + "\"uid\" : \"" + UUID.randomUUID() + "\"}";
		return result;
	}
	
	/** BuildStatisticsMessage_TestStat : **/
	public static String BuildStatisticsMessage_TCPStats2(int nodeId, int ipId, int connections, int addresses, int receivedPackets, 
														  int receivedData, int sendPackets, int sendData, 
														  int maxAllTime, int maxIdleTime, 
														  double maxCps, double maxPps, double maxBps, double maxConnections,
														  int uniqueAddresses, String time) {
		String data = "\"" + ipId  + "," 
					 	   + time  + "," 
					 	   + connections + ","
					 	   + addresses + ","
					 	   + receivedPackets + ","
					 	   + receivedData + ","
					 	   + sendPackets + ","
					 	   + sendData + ","
					 	   + maxAllTime + ","
					 	   + maxIdleTime + ","
					 	   + maxCps + ","
					 	   + maxPps + ","
					 	   + maxBps + ","
					 	   + maxConnections + ","
					 	   + uniqueAddresses + "\"";
		String result = "{\"type\": \"tcpstat\", "
					     + "\"node_id\": " + nodeId + ", "
					     + "\"data\": {\"ip_id\": [" + ipId + "],"
					     + "\"data\": " + data + ", "
					     + "\"type\": {\"import_schema_id\": 11, \"aggregation_schema_id\": []}},"
					     + "\"uid\" : \"" + UUID.randomUUID() + "\"}";
		return result;
	}
	
	/** BuildStatisticsMessage_SSLStats : **/
	public static String BuildStatisticsMessage_SSLStats_v1(int nodeId, int ipId, int count_net_ip, int max_handshakes, int max_key_renegotiations, int handshakes,
															int key_renegotiations, int connections, int unknown_records, int first_packet_isnt_CH, int alerts, 
														    int CH_not_random, int compression_requests, int min_version_violations, int max_suites_violations, 
														    int max_suites, int packets, int bytes, int proto_less_than_SSL_3_0, int proto_is_SSL_3_0, 
														    int proto_is_TLS_1_0, int proto_is_TLS_1_1, int proto_is_TLS_1_2, int proto_more_than_TLS_1_2, String time) {
		String data = ipId  + "," 
					+ time  + "," 
					+ count_net_ip  + "," 
					+ max_handshakes  + ","
					+ max_key_renegotiations  + ","
					+ handshakes  + "," 
					+ key_renegotiations  + "," 
					+ connections  + "," 
					+ unknown_records  + "," 
					+ first_packet_isnt_CH + "," 
					+ alerts + "," 
					+ CH_not_random + "," 
					+ compression_requests  + "," 
					+ min_version_violations + "," 
					+ max_suites_violations + "," 
					+ max_suites + "," 
					+ packets  + "," 
					+ bytes  + "," 
					+ proto_less_than_SSL_3_0  + "," 
					+ proto_is_SSL_3_0  + "," 
					+ proto_is_TLS_1_0  + "," 
					+ proto_is_TLS_1_1  + "," 
					+ proto_is_TLS_1_2  + "," 
					+ proto_more_than_TLS_1_2 + "\"";		
		
		String result = "{\"uid\": \"" + UUID.randomUUID() + "\"," + 
				"\"data\": {" + 
					"\"data\": \"" + data + "," + 
					"\"type\": {\"import_schema_id\": 15,\"aggregation_schema_id\": []}}," + 
				"\"ts\": 1537540140," + 
				"\"node_id\": " + nodeId + "," + 
				"\"key\": \"node#" + nodeId + "#0\"," + 
				"\"type\": \"sslstat\"}";
		
		return result;
	}
	
	/** BuildStatisticsMessage_SSLStats : **/
	public static String BuildStatisticsMessage_SSLStats_v2(int nodeId, int ipId, int count_net_ip, int max_handshakes, int max_key_renegotiations, int handshakes,
															int key_renegotiations, int connections, int unknown_records, int first_packet_isnt_CH, int alerts, 
														    int CH_not_random, int compression_requests, int min_version_violations, int max_suites_violations, 
														    int max_suites, int packets, int bytes, int proto_less_than_SSL_3_0, int proto_is_SSL_3_0, 
														    int proto_is_TLS_1_0, int proto_is_TLS_1_1, int proto_is_TLS_1_2, int proto_more_than_TLS_1_2, String time) {
		String data = ipId  + "," 
					+ time  + "," 
					+ count_net_ip  + "," 
					+ max_handshakes  + ","
					+ max_key_renegotiations  + ","
					+ handshakes  + "," 
					+ key_renegotiations  + "," 
					+ connections  + "," 
					+ unknown_records  + "," 
					+ first_packet_isnt_CH + "," 
					+ alerts + "," 
					+ CH_not_random + "," 
					+ compression_requests  + "," 
					+ min_version_violations + "," 
					+ max_suites_violations + "," 
					+ max_suites + "," 
					+ packets  + "," 
					+ bytes  + "," 
					+ proto_less_than_SSL_3_0  + "," 
					+ proto_is_SSL_3_0  + "," 
					+ proto_is_TLS_1_0  + "," 
					+ proto_is_TLS_1_1  + "," 
					+ proto_is_TLS_1_2  + "," 
					+ proto_more_than_TLS_1_2 + ",0\"";		
		
		String result = "{\"uid\": \"" + UUID.randomUUID() + "\"," + 
				"\"data\": {" + 
					"\"data\": \"" + data + "," + 
					"\"type\": {\"import_schema_id\": 115,\"aggregation_schema_id\": []}}," + 
				"\"ts\": 1537540140," + 
				"\"node_id\": " + nodeId + "," + 
				"\"key\": \"node#" + nodeId + "#0\"," + 
				"\"type\": \"sslstat\"}";
		
		return result;
	}
	
}
