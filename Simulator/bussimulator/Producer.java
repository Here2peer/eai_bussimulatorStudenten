package bussimulator;

import java.util.Date;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

enum type {TOPIC, QUEUE}

public class Producer {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "json_topic";
    
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
       ConnectionFactory connectionFactory =
           new ActiveMQConnectionFactory(url);
       Connection connection = connectionFactory.createConnection();
       connection.start();

       Session session = createSession();
       Destination destination = createDestiantion(subject);
       MessageProducer messageProducer = createMessageProducer(destination);
    }

    private Session createSession() throws JMSException {
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        return session;
    }

    private Destination createDestiantion(String queueName) throws JMSException {
        Destination destination = session.createTopic(queueName);
        return destination;
    }

    private MessageProducer createMessageProducer(Destination destination) throws JMSException {
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        return producer;
    }

    
    private void sendTextMessage(String themessage) throws JMSException {
//		TODO maak de message aan
//      TextMessage msg = ??????;
//      producer.send(msg);
    }    
}
