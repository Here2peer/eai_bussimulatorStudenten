package bussimulator.create;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class CreateConnection {

    private Connection connection;

    public CreateConnection() throws JMSException {
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createConnection();
        connection.start();
    }

    public Connection getConnection() {
        return connection;
    }
}
