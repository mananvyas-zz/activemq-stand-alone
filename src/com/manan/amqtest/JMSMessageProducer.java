package com.manan.amqtest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
 
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
 
public class JMSMessageProducer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://localhost:61616");
        Destination destination = new ActiveMQQueue("Queue-1");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        try {
            String payload = "Hi..Its Saturday";
            Message msg = session.createTextMessage(payload);
            MessageProducer producer = session.createProducer(destination);
            System.out.println("Send text '" + payload + "'");
            producer.send(msg);
            session.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
 
    }
 
}