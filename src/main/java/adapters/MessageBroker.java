package adapters;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import domain.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Adapts an {@link OutputService} to a RabbitMQ queue
 */
public class MessageBroker implements OutputService {
    private Logger logger = Logger.getLogger(MessageBroker.class);
    private MessageFormatter formatter;
    private String uri;
    private String queueName;

    private Connection connection = null;
    private Channel channel = null;

    public MessageBroker(String uri, String queueName, MessageFormatter formatter){
        this.uri = uri;
        this.queueName = queueName;
        this.formatter = formatter;
    }

    @Override
    public void putMessage(MessageDTO messageDTO) throws CommunicationException {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri(uri);
            factory.setRequestedHeartbeat(30);
            factory.setConnectionTimeout(30000);

            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(queueName, false, false, false, null);

            String xmlString = formatter.format(messageDTO);

            channel.basicPublish("", queueName, null, xmlString.getBytes());
            logger.info("Sent message to the RabbitMQ queue: " + queueName);
        } catch (Exception e){
            throw new CommunicationException("Error during placing message on the queue", e);
        }
    }

    @Override
    public void shutdown() throws CommunicationException {
        try {
            channel.close();
            connection.close();
        } catch (TimeoutException | IOException e) {
            throw new CommunicationException("Unable to close connection to RabbitMQ, e");
        }
    }

    public MessageFormatter getFormatter() { return formatter; }
    public void setFormatter(MessageFormatter formatter) { this.formatter = formatter; }
}
