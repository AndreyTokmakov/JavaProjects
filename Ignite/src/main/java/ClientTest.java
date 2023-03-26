import java.util.Collection;
import java.util.Collections;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCluster;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteRunnable;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;

public class ClientTest {
	
	private static final String USER_PRESENCE_CACHE_NAME = "extensionToUserStatus";
	private static final String CALL_SESSION_ENTITY_CACHE_NAME = "extensionToCallSessions";
	private static final String PRESENCE_PERMISSIONS_KEY_CACHE_NAME = "presencePermissions";
	private static final String REMOVED_EXTENSION_CACHE_NAME = "removedExtensions";
	
	public static void main(String[] args) throws IgniteException {
		// ClientTest1();
		
		Test();
	}
	
	private static void Test() {
		final IgniteConfiguration config = new IgniteConfiguration();
		config.setClientMode(true);
		config.setPeerClassLoadingEnabled(true);

		// Setting up an IP Finder to ensure the client can locate the servers.
		TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
		ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47501"));
		config.setDiscoverySpi(new TcpDiscoverySpi().setIpFinder(ipFinder));

		// Starting the node
		Ignite ignite = Ignition.start(config);
		IgniteCluster igniteCluster = ignite.cluster();
		 
		//IgniteCache<Integer, String> cache = ignite.getOrCreateCache(USER_PRESENCE_CACHE_NAME);
		
		Collection<ClusterNode> nodes = igniteCluster.forDataNodes(USER_PRESENCE_CACHE_NAME).nodes();
		if (nodes.isEmpty()) {
            System.err.println("nodes are empty");
        }
		else {
			System.out.println("OK. Size = " + nodes.size());
			
		}
		
		System.out.println(igniteCluster.forServers());
		System.out.println(nodes);
		
		
		ignite.close();
	}

	private static void ClientTest1() {
		// Preparing IgniteConfiguration using Java APIs
		IgniteConfiguration cfg = new IgniteConfiguration();

		// The node will be started as a client node.
		cfg.setClientMode(true);

		// Classes of custom Java logic will be transferred over the wire from this app.
		cfg.setPeerClassLoadingEnabled(true);

		// Setting up an IP Finder to ensure the client can locate the servers.
		TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
		ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47501"));
		cfg.setDiscoverySpi(new TcpDiscoverySpi().setIpFinder(ipFinder));

		// Starting the node
		Ignite ignite = Ignition.start(cfg);

		// Create an IgniteCache and put some values in it.
		IgniteCache<Integer, String> cache = ignite.getOrCreateCache(USER_PRESENCE_CACHE_NAME);
		cache.put(1, "Hello");
		cache.put(2, "World 2");

		System.out.println(">> Created the cache and add the values.");

		// Executing custom Java compute task on server nodes.
		ignite.compute(ignite.cluster().forServers()).broadcast(new RemoteTask());

		System.out.println(">> Compute task is executed, check for output on the server nodes.");

		// Disconnect from the cluster.
		ignite.close();
	}
	
	/**
	* A compute tasks that prints out a node ID and some details about its OS and JRE.
	* Plus, the code shows how to access data stored in a cache from the compute task.
	*/
	private static class RemoteTask implements IgniteRunnable {
		@IgniteInstanceResource
		Ignite ignite;

		@Override public void run() {
			System.out.println(">> Executing the compute task");

			System.out.println(
	                "   Node ID: " + ignite.cluster().localNode().id() + "\n" +
	                "   OS: " + System.getProperty("os.name") +
	                "   JRE: " + System.getProperty("java.runtime.name"));

			IgniteCache<Integer, String> cache = ignite.cache("myCache");
			System.out.println(">> " + cache.get(1) + " " + cache.get(2));
		}
	}
}