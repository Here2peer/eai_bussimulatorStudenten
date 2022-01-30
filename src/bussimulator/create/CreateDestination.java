package bussimulator.create;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

public class CreateDestination {
    Destination destination;
    public CreateDestination(String queueName, Session session) throws JMSException {
        destination = session.createQueue(queueName);
    }

    public Destination getDestination() {
        return destination;
    }
}
