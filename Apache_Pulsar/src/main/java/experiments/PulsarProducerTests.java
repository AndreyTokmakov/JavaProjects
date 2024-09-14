/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* AdminKafkaTests.java class
*
* @name    : AdminKafkaTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Feb 13, 2021
****************************************************************************/

package experiments;

import org.apache.pulsar.client.api.*;


public class PulsarProducerTests
{
	private static final String PULSAR_HOST = "localhost";
	private static final String SERVICE_URL = String.format("pulsar://%s:6650", PULSAR_HOST);
	private static final String TOPIC_NAME = "notifications";
	private static final String pulsarToken = "<REPLACE_WITH_PULSAR_TOKEN>";

	public static void main(String[] args) throws PulsarClientException
	{
		System.out.println("Apache_Pulsar");

		// https://pulsar.apache.org/docs/next/client-libraries-producers/
		try (final PulsarClient client = PulsarClient.builder()
				.serviceUrl(SERVICE_URL)
				// .authentication(AuthenticationFactory.token(pulsarToken))
				.build())
		{
			try (final Producer<byte[]> producer = client.newProducer().topic(TOPIC_NAME)
					.compressionType(CompressionType.LZ4).create())
			{
				MessageId messageId = producer.newMessage()
						.value("my-sync-message".getBytes())
						.send();
			}



			/* Publish messages asynchronously:
			producer.newMessage()
				.value("my-sync-message")
				.sendAsync()
				.thenAccept(messageId -> {
					log.info("Message ID: {}", messageId);
				});
			 */

		}
		catch (Exception exc) {
			System.err.println(exc.toString());
		}
	}
}
