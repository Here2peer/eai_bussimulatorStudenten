package bussimulator.create;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

public class CreateSession {
    Session session;

    public CreateSession(Connection connection) throws JMSException {
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public Session getSession() {
        return session;
    }
}
