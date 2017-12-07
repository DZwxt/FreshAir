package activeMQ;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producter {
//	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
//	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
//	private static final String BROKEN_UEL = ActiveMQConnection.DEFAULT_BROKER_URL;
//	
//	AtomicInteger count = new AtomicInteger(0);
//	
//	ConnectionFactory connectionFactory ;
//	
//	Connection connection;
//	
//	Session session;
//	ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<MessageProducer>();
//	
//	public void init(){
//		try {
//			connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_UEL);
//			connection = connectionFactory.createConnection();
//			connection.start();
//			session = connection.createSession(true, Session.SESSION_TRANSACTED);
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public void sendMessage(String disname){
//		try {
//			Queue queue = session.createQueue(disname);
//			MessageProducer messageProducer = null;
//			if (threadLocal.get()!=null) {
//				messageProducer = threadLocal.get();
//				
//			}else {
//				messageProducer = session.createProducer(queue);
//				threadLocal.set(messageProducer);
//				
//			}
//			while (true) {
//				Thread.sleep(1000);
//				int num = count.getAndIncrement();
//				//创建一条消息
////				TextMessage message = session.createTextMessage(Thread.currentThread().getName()+"productor:我正在生产东西，count:"+num);
//				System.out.println(Thread.currentThread().getName());
////				messageProducer.send(message);
//				session.commit();
//			}
//		} catch (JMSException | InterruptedException e) {
//			e.printStackTrace();
//		}
//		//消息生产者
//		
//	}
//	
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("my-queue");
		MessageProducer producer = session.createProducer(destination);
		int i = 0;
		while (i<3) {
			i++;
			MapMessage message = session.createMapMessage();
			message.setBooleanProperty("property"+i, true);
			message.setBoolean("message"+i, false);
			producer.send(message);
			
		}
		session.commit();
		session.close();
		connection.close();
	}
}
