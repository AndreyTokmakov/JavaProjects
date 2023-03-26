import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Apache_Ignite_Tests {
	private static final Logger logger = LoggerFactory.getLogger(Apache_Ignite_Tests.class);
	private static final String HOST = "127.0.0.1";
	private static final String PORT = "10800";
	private static final String CACHE_NAME = "thin-cache";
	
	
	private static final String USER_PRESENCE_CACHE_NAME = "extensionToUserStatus";
	private static final String CALL_SESSION_ENTITY_CACHE_NAME = "extensionToCallSessions";
	private static final String PRESENCE_PERMISSIONS_KEY_CACHE_NAME = "presencePermissions";
	private static final String REMOVED_EXTENSION_CACHE_NAME = "removedExtensions";

	protected static void client_test() 
	{
		logger.info("Simple Ignite thin client example working over TCP socket.");
		ClientConfiguration cfg = new ClientConfiguration().setAddresses(HOST + ":" + PORT);
		try (IgniteClient igniteClient = Ignition.startClient(cfg))
		{
			ClientCache<String, String> clientCache = igniteClient.getOrCreateCache(CACHE_NAME);
			
			// put a few value
			clientCache.put("Moscow", "095");
			clientCache.put("Vladimir", "033");
			   
			// get the region code of the Vladimir
			String val = clientCache.get("Vladimir");
			
			logger.info("Print value: {}", val);
		} catch (IgniteException e) {
			logger.error("Ignite exception:", e.getMessage());
		} catch (Exception e) {
			logger.error("Ignite exception:", e.getMessage());
		}
	} 
	
	protected static void start_sever() throws IOException {
		IgniteConfiguration config = new IgniteConfiguration();
		config.setClientMode(false);
		config.setLocalHost("127.0.0.1");
		config.setPeerClassLoadingEnabled(true);

		TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder(true);
	    ipFinder.setAddresses(Collections.singleton("127.0.0.1:47500..47501"));
		
		TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
		discoverySpi.setIpFinder(ipFinder);
		// discoverySpi.setClientReconnectDisabled(true);
		config.setDiscoverySpi(discoverySpi);

		/*
		CacheConfiguration ccfg1 = new CacheConfiguration();
		ccfg1.setName(USER_PRESENCE_CACHE_NAME);
		
		CacheConfiguration ccfg2 = new CacheConfiguration();
		ccfg2.setName(CALL_SESSION_ENTITY_CACHE_NAME);
		
		CacheConfiguration ccfg3 = new CacheConfiguration();
		ccfg3.setName(PRESENCE_PERMISSIONS_KEY_CACHE_NAME);
		
		CacheConfiguration ccfg4 = new CacheConfiguration();
		ccfg4.setName(REMOVED_EXTENSION_CACHE_NAME);

		config.setCacheConfiguration(ccfg1, ccfg2, ccfg3, ccfg4);
		*/
		
		try (Ignite ignite = Ignition.start(config)) {
			ignite.createCache(USER_PRESENCE_CACHE_NAME);
			ignite.createCache(CALL_SESSION_ENTITY_CACHE_NAME);
			ignite.createCache(PRESENCE_PERMISSIONS_KEY_CACHE_NAME);
			ignite.createCache(REMOVED_EXTENSION_CACHE_NAME);
			sleep(60 * 1000);
		}
	}
	
	private static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {

		}
	}
	
	public static void main(String[] args) throws IOException
	{
		start_sever();
	}
}
