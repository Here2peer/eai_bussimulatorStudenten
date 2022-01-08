package bussimulator;

import java.util.Date;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

enum type {TOPIC, QUEUE}

public class Producer {

    private Session session;
    private Connection connection;
    private MessageProducer producer;
    
    public Producer() {
    }
    
    public void sendBericht(String bericht) {
    	try {
    		createConnection();
    		sendTextMessage(bericht);
            connection.close();
    	} catch (JMSException e) {
    		e.printStackTrace();
    	}
    }
        
    
    private void createConnection() throws JMSException {
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        ConnectionFactory connectionFactory =
           new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createConnection();
        connection.start();

        session = createSession();
        String subject = "json_topic";
        Destination destination = createDestiantion(subject);
        producer = createMessageProducer(destination);
    }

    private Session createSession() throws JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    private Destination createDestiantion(String queueName) throws JMSException {
        return session.createQueue(queueName);
    }

    private MessageProducer createMessageProducer(Destination destination) throws JMSException {
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        return producer;
    }

    
    private void sendTextMessage(String themessage) throws JMSException {
        TextMessage msg = session.createTextMessage(themessage);
        producer.send(msg);
    }    
}
