package activeMQ_helloworld;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMQConsumer {

	@Test
	// ֱ������
	public void testCosumeMQ() throws Exception {
		// ���ӹ���
		// ʹ��Ĭ���û��������롢·��
		// ·�� tcp://host:61616
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		// ��ȡһ������
		Connection connection = connectionFactory.createConnection();
		// ��������
		connection.start();
		// �����Ự
		// ��һ���������Ƿ�ʹ�������������true��������Ϣ���к󣬱���ʹ�� session.commit();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		// �������л��߻������
		Queue queue = session.createQueue("HelloWorld");
		// ����������
		MessageConsumer messageConsumer = session.createConsumer(queue);

		while (true) {
			TextMessage message = (TextMessage) messageConsumer.receive(10000);
			if (message != null) {
				System.out.println(message.getText());
			} else {
				break;
			}
		}
	}

	@Test
	// ʹ�ü���������
	public void testCosumeMQ2() throws Exception {
		// ���ӹ���
		// ʹ��Ĭ���û��������롢·��
		// ·�� tcp://host:61616
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		// ��ȡһ������
		Connection connection = connectionFactory.createConnection();
		// ��������
		connection.start();
		// �����Ự
		// ��һ���������Ƿ�ʹ�������������true��������Ϣ���к󣬱���ʹ�� session.commit();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		// �������л��߻������
		Queue queue = session.createQueue("HelloWorld");
		// ����������
		MessageConsumer messageConsumer = session.createConsumer(queue);

		messageConsumer.setMessageListener(new MessageListener() {
			// ÿ�ν�����Ϣ���Զ����� onMessage
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println(textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

		while (true) {
			// ������junit�߳�����
		}
	}
}
