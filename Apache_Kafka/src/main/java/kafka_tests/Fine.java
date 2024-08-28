/**
 * 
 */
package kafka_tests;

import java.util.UUID;

/** @author Tokmakov **/
public class Fine {
	/** Fine amount : **/
	private  int fine;
	/** IP address Integer representation : **/
	private int ipAddress;
	/** Fine id Id : **/
	private  int ipId;	
	/** Unique UUID : **/
	private UUID uuid;
	/** Fine record TTL : **/
	private int ttl;
	/** Timestamp : **/
	private String timestamp;
	/** Module name : */
	private String module;
	/** Metric name : **/
	private String metric;
	/** **/
	private Drop drop = null;
	
	
	/** Fine class default constructor : **/
	public Fine() {
	}
	
	/**
	 * @param ipId
	 * @param fine
	 * @param ipAddres
	 * @param ttl
	 * @param module
	 * @param metric
	 */
	public Fine(int ipId, int fine, int ipAddres, int ttl, String module, String metric) {
		this.ipId = ipId;
		this.fine = fine;
		this.ipAddress = ipAddres;
		this.ttl = ttl;
		this.module = module;
		this.metric = metric;
		
		this.uuid = UUID.randomUUID();
		this.timestamp = MessageBusUtils.getFineTimestamp();
	}
	
	/**
	 * @param ipId
	 * @param fine
	 * @param ipAddres
	 * @param ttl
	 * @param module
	 * @param metric
	 * @param timestamp
	 */
	public Fine(int ipId, int fine, int ipAddres, int ttl, String module, String metric, String timestamp) {
		this.ipId = ipId;
		this.fine = fine;
		this.ipAddress = ipAddres;
		this.ttl = ttl;
		this.timestamp = timestamp;
		this.module = module;
		this.metric = metric;
		this.uuid = UUID.randomUUID();
	}
	
	/**
	 * @param ipId
	 * @param fine
	 * @param ipAddres
	 * @param ttl
	 * @param module
	 * @param metric
	 * @param timestamp
	 * @param drop
	 */
	public Fine(int ipId, int fine, int ipAddres, int ttl, String module, String metric, String timestamp, Drop drop) {
		this.ipId = ipId;
		this.fine = fine;
		this.ipAddress = ipAddres;
		this.ttl = ttl;
		this.timestamp = timestamp;
		this.module = module;
		this.metric = metric;
		this.uuid = UUID.randomUUID();
		this.drop = drop;
	}	

	/**  @return the fine : **/
	public int getIpId() {
		return ipId;
	}
	/**  @param fine the fine to set : **/
	public void setIpId(int ipId) {
		this.ipId = ipId;
	}
	/**  @return the fine : **/
	public int getFine() {
		return fine;
	}
	/**  @param fine the fine to set : **/
	public void setFine(int fine) {
		this.fine = fine;
	}
	/** @return the ipAddres : **/
	public int getIpAddres() {
		return ipAddress;
	}
	/** @param ipAddres the ipAddres to set : **/
	public void setIpAddres(int ipAddress) {
		this.ipAddress = ipAddress;
	}
	/** @return the uuid : **/
	public UUID getUuid() {
		return uuid;
	}
	/** @return the ttl : **/
	public int getTtl() {
		return ttl;
	}
	/** @param ttl the ttl to set : **/
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}
	/** @return the timestamp : **/
	public String getTimestamp() {
		return timestamp;
	}
	/** @param timestamp the timestamp to set : **/
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**  @return the module : **/
	public String getModule() {
		return module;
	}
	/** @param module the module to set : **/
	public void setModule(String module) {
		this.module = module;
	}
	/** @return the metric : **/
	public String getMetric() {
		return metric;
	}
	/** @param metric the metric to set : **/
	public void setMetric(String metric) {
		this.metric = metric;
	}
	/** @return the drop : **/
	public Drop getDrop() {
		return drop;
	}
	
	public String toJsonString() {
		System.out.println(11111);
		String text = "{" + 
				"\"fine\": " + this.fine + ", " + 
				"\"ip\": " + this.ipAddress + ", " + 
				"\"ttl\": " + this.ttl + ", " + 
				"\"t\": " + this.timestamp + ", " + 
				"\"module\": " + this.module + ", " + 
				"\"uuid\": " + this.uuid + ", " + 
				"\"metrics_name\": " + this.metric;
		System.out.println(this.drop);
		if (null != this.drop) {
			System.out.println(1111);
			text += ", "  + getDropJson();
		}
		text += "}";
		return text;
	}
	
	protected String getDropJson() {
		return "\"drop\": {\"src_ip\": " + this.drop.getSrcIP() + ", \"src_port\": " + this.drop.getSrcPort() + 
						", \"dst_ip\": " + this.drop.getDstIP() + ", \"dst_port\": " + this.drop.getDstPort() + "}";
	}

}


class Drop {
	/** **/
	private long srcIP;
	/** **/
	private int srcPort;
	/** **/
	private long dstIP;
	/** **/
	private int dstPort;
	
	
	/**
	 * @param srcIP
	 * @param srcPort
	 * @param dstIP
	 * @param dstPort
	 */
	public Drop(long srcIP, int srcPort, long dstIP, int dstPort) {
		this.srcIP = srcIP;
		this.srcPort = srcPort;
		this.dstIP = dstIP;
		this.dstPort = dstPort;
	}
	
	
	/** @return the srcIP : **/
	public long getSrcIP() {
		return srcIP;
	}
	/**  @param srcIP the srcIP to set : **/
	public void setSrcIP(int srcIP) {
		this.srcIP = srcIP;
	}
	/** @return the srcPort : **/
	public int getSrcPort() {
		return srcPort;
	}
	/** @param srcPort the srcPort to set : **/
	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}
	/** @return the dstIP : **/
	public long getDstIP() {
		return dstIP;
	}
	/** @param dstIP the dstIP to set : **/
	public void setDstIP(int dstIP) {
		this.dstIP = dstIP;
	}
	/** @return the dstPort : **/
	public int getDstPort() {
		return dstPort;
	}
	/**  @param dstPort the dstPort to set : **/
	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}
}