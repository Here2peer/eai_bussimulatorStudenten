package bussimulator.create;

import javax.jms.*;

public class CreateMessageProducer {

    private final MessageProducer producer;
    private final Session session;
    private final Destination destination;

    public CreateMessageProducer(Session s, Destination d) throws JMSException {
        session = s;
        destination = d;
        producer = createMessageProducer();
    }

    private MessageProducer createMessageProducer() throws JMSException {
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        return producer;
    }

    public MessageProducer getProducer() {
        return producer;
    }
}
