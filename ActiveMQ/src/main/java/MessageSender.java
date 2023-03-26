
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        System.out.println("23232");
        connection.start();

        System.out.println("23232");
        // JMS messages are sent and received using a Session. We will
        // create here a non-transactional session object. If you want
        // to use transactions you should set the first parameter to 'true'
        Session session = connection.createSession(false,  Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("testt");
        MessageProducer producer = session.createProducer(topic);

        // We will send a small text message saying 'Hello'
        TextMessage message = session.createTextMessage();

        message.setText("HELLO JMS WORLD");
        // Here we are sending the message!
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");

        connection.close();
    }
}