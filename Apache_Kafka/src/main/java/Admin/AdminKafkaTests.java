/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* AdminKafkaTests.java class
*
* @name    : AdminKafkaTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Feb 13, 2021
****************************************************************************/

package Admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.clients.admin.DescribeFeaturesResult;
import org.apache.kafka.clients.admin.FeatureMetadata;
import org.apache.kafka.clients.admin.ListConsumerGroupsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.acl.AclOperation;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.errors.TimeoutException;
import org.apache.kafka.common.errors.TopicExistsException;
import org.apache.kafka.common.errors.UnknownTopicOrPartitionException;


class KafkaAdmin
{
	private final static List<String> topics = new ArrayList<String>(List.of("tests"));

	private final static List<String> nodes = new ArrayList<String>(List.of("0.0.0.0:9092"));
	// private final static List<String> nodes = new ArrayList<String>(List.of("192.168.101.2:9092"));

	private Admin kafkaAdmin = null;

	public KafkaAdmin()
	{
		Properties config = new Properties();
		config.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, nodes);

        this.kafkaAdmin = Admin.create(config);
	}
	
	public void getTopics() throws ExecutionException, InterruptedException
	{
		final ListTopicsResult topicList = kafkaAdmin.listTopics();
		for (TopicListing topicListing : topicList.listings().get()) {
			System.out.println(topicListing);
		}
	}
	
	public void createTopic() throws ExecutionException, InterruptedException
	{
		System.out.println("Topics: " + kafkaAdmin.listTopics().listings().get());
		
		NewTopic newTopic = new NewTopic("events", 1, (short) 1);
		kafkaAdmin.createTopics(Collections.singleton(newTopic));

		System.out.println("Topics: " + kafkaAdmin.listTopics().listings().get());
	}
	
	public void deleteTopic() throws ExecutionException, InterruptedException
	{
		System.out.println("Topics: " + kafkaAdmin.listTopics().listings().get());
		
		Collection<String> topics = Collections.singleton("my-new-topic");
		DeleteTopicsResult result = kafkaAdmin.deleteTopics(topics);
		System.out.println(result.values());
		
		System.out.println("Topics: " + kafkaAdmin.listTopics().listings().get());
	}
	
	public void getTopics_Deprecated() throws ExecutionException, InterruptedException
	{
		Properties config = new Properties();
		config.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, nodes.toString().replace("[", "").replace("]", ""));
		
		// Will be DEPRICATED
		AdminClient adminClient = AdminClient.create(config);
		final ListTopicsResult topicList = adminClient.listTopics();
		
		for (TopicListing topicListing : topicList.listings().get()) {
			System.out.println(topicListing);
		}
	}
	
	public void getNodes() throws InterruptedException, ExecutionException
	{
		KafkaFuture<Collection<Node>> nodesFuture = kafkaAdmin.describeCluster().nodes();
		Collection<Node> nodes = nodesFuture.get();
		System.out.println(nodes);
	}
	
	public void getNodesConfigs() throws InterruptedException, ExecutionException
	{
		KafkaFuture<Collection<Node>> nodesFuture = kafkaAdmin.describeCluster().nodes();
		Collection<Node> nodes = nodesFuture.get();

		for (final Node node : nodes) 
			getNodeConfig(node);
	}
	
	private void getNodeConfig(final Node node) throws InterruptedException, ExecutionException
	{
		System.out.println("-------- Node: " + node);
		ConfigResource cfgResource = new ConfigResource(ConfigResource.Type.BROKER, String.valueOf(node.id()));
		DescribeConfigsResult dcr = kafkaAdmin.describeConfigs(Collections.singleton(cfgResource));
		dcr.all().get().forEach((k, c) -> {
              c.entries().forEach(configEntry -> {
            	  System.out.println("   " + configEntry.name() + "= " + configEntry.value());
              });
        });
	}
	
	public void getConsumers() throws InterruptedException, ExecutionException
	{
		ListConsumerGroupsResult result = kafkaAdmin.listConsumerGroups();
		
		System.out.println("ConsumerGroupsListing: ");
		for (final ConsumerGroupListing group: result.all().get()) {
			System.out.println("   " + group);
		}
	}
	
	public void getFeatures() throws InterruptedException, ExecutionException
	{
		DescribeFeaturesResult features = kafkaAdmin.describeFeatures();
		FeatureMetadata featureMetadata = features.featureMetadata().get();
		
		System.out.println(featureMetadata);
	}
	
	public void TESTS() throws InterruptedException, ExecutionException
	{
		// System.out.println("Topics: " + kafkaAdmin.listTopics().listings().get());
		
		
		/*
		ListConsumerGroupsResult result = kafkaAdmin.listConsumerGroups();
		System.out.println("ConsumerGroupsListing: ");
		for (final ConsumerGroupListing group: result.all().get()) {
			System.out.println("   " + group);
		}
		*/
		
		/*
		DescribeFeaturesResult features = kafkaAdmin.describeFeatures();
		FeatureMetadata featureMetadata = features.featureMetadata().get();
		System.out.println(featureMetadata);
		*/
		
		DescribeClusterResult describeCluster = kafkaAdmin.describeCluster();
		
		String clusterId = describeCluster.clusterId().get();
		System.out.println("clusterId = " + clusterId);
		
		Node node = describeCluster.controller().get();
		System.out.println("controller node:  " + node);
		
		Collection<Node> nodes = describeCluster.nodes().get();
		System.out.println("Nodes:  " + nodes);
		
		Set<AclOperation> authorizedOperations = describeCluster.authorizedOperations().get();
		System.out.println("authorizedOperations:  " + authorizedOperations + "\n");
		
		for (final Node broker : nodes) {
			ConfigResource cfgResource = new ConfigResource(ConfigResource.Type.BROKER, String.valueOf(node.id()));
			DescribeConfigsResult configResult = kafkaAdmin.describeConfigs(Collections.singleton(cfgResource));
			
			final Map<ConfigResource, Config> configuration = configResult.all().get();
			for (Entry<ConfigResource, Config> resourceCfg: configuration.entrySet()) {
				System.out.println("********** " + resourceCfg.getKey()+ "**********");
				for (final ConfigEntry configEntry: resourceCfg.getValue().entries())
				{
					  System.out.println("    " + configEntry.name() + " = " + configEntry.value());
				}
			}
		}
	}
}

public class AdminKafkaTests
{
	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		final KafkaAdmin kafkaAdmin = new KafkaAdmin();

		kafkaAdmin.getTopics();
		// kafkaAdmin.getTopics_Deprecated();
		// kafkaAdmin.createTopic();
		// kafkaAdmin.deleteTopic();
		// kafkaAdmin.getNodes();
		// kafkaAdmin.getNodesConfigs();
		// kafkaAdmin.getConsumers();
		// kafkaAdmin.getFeatures();
		// kafkaAdmin.TESTS();
	}
}
