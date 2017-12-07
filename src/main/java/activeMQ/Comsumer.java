package activeMQ;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Comsumer {
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = factory.createConnection();
		connection.start();
		
		Enumeration names = connection.getMetaData().getJMSXPropertyNames();
		while (names.hasMoreElements()) {
			String object = (String) names.nextElement();
			System.out.println(object);
		}
		
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("my-queue");
		
		MessageConsumer consumer = session.createConsumer(destination);
		
		int i = 0;
		while (i<3) {
			i++;
			MapMessage message = (MapMessage) consumer.receive();
			session.commit();
			System.out.println("接收到消息:"+message.getBoolean("message"+i)+"propert:"+message.getBooleanProperty("property"+i));
			
		}
		session.close();
		connection.close();
	}

}
