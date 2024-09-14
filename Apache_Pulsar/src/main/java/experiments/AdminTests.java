package experiments;

import org.apache.pulsar.client.admin.*;
import org.apache.pulsar.client.api.CompressionType;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;

import java.util.List;

public class AdminTests
{

    // private static final String PULSAR_HOST = "192.168.101.2";
    private static final String PULSAR_HOST = "0.0.0.0";

    private static final String SERVICE_URL = String.format("pulsar://%s:6650", PULSAR_HOST);
    private static final String PULSAR_ADMIN_URL = String.format("http://%s:8080", PULSAR_HOST);
    private static final String TOPIC_NAME = "test-topic";

    private static final String authPluginClassName = "com.org.MyAuthPluginClass";
    private static final String authParams = "param1=value1";
    private static final boolean tlsAllowInsecureConnection = false;


    public static void printAllTopics(PulsarAdmin admin) throws PulsarAdminException
    {
        final Tenants tenants = admin.tenants();
        final Namespaces namespaces = admin.namespaces();
        final Topics topics = admin.topics();

        final List<String> tenantList = tenants.getTenants();
        for (final String tenant: tenantList)
        {
            System.out.println("\t" + tenant);
            final List<String> namespacesList = namespaces.getNamespaces(tenant);
            for (final String namespace: namespacesList)
            {
                System.out.println("\t\t" + namespace);
                final List<String> topicsList = topics.getList(namespace);
                for (final String topic: topicsList)
                {
                    System.out.println("\t\t\t" + topic);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        try (final PulsarAdmin admin = PulsarAdmin.builder()
                // .authentication(authPluginClassName,authParams)
                // .tlsTrustCertsFilePath(tlsTrustCertsFilePath)
                // .allowTlsInsecureConnection(tlsAllowInsecureConnection)
                .serviceHttpUrl(PULSAR_ADMIN_URL)
                .build())
        {

            printAllTopics(admin);

        }
        catch (final Exception exc) {
            System.err.println(exc.getMessage());
        }
    }
}
