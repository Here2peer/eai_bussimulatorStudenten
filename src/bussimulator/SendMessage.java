package bussimulator;

import javax.jms.*;

import bussimulator.create.CreateConnection;
import bussimulator.create.CreateDestination;
import bussimulator.create.CreateMessageProducer;
import bussimulator.create.CreateSession;


public class SendMessage {

    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private Destination destination;

    public void sendBericht(String bericht) {
    	try {
            String subject = "xml_queue";
    		CreateConnection createConnection = new CreateConnection();
            connection = createConnection.getConnection();
            CreateSession createSession = new CreateSession(connection);
            session = createSession.getSession();
            CreateDestination createDestination = new CreateDestination(subject,session);
            destination = createDestination.getDestination();
            CreateMessageProducer createMessageProducer = new CreateMessageProducer(session, destination);
            producer = createMessageProducer.getProducer();
    		sendTextMessage(bericht);
            connection.close();
    	} catch (JMSException e) {
    		e.printStackTrace();
    	}
    }

    private void sendTextMessage(String themessage) throws JMSException {
        TextMessage msg = session.createTextMessage(themessage);
        producer.send(msg);
    }    
}
