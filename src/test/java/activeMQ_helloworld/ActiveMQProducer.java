package activeMQ_helloworld;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMQProducer {
	@Test
	public void testProduceMQ() throws Exception {
		// ���ӹ���
		// ʹ��Ĭ���û��������롢·��
		// ·�� tcp://host:61616
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		// ��ȡһ������
		Connection connection = connectionFactory.createConnection();
		// �����Ự
		Session session = connection.createSession(true,
				Session.AUTO_ACKNOWLEDGE);
		// �������л��߻������
		Queue queue = session.createQueue("HelloWorld");
		// ���������� ���� ������
		MessageProducer producer = session.createProducer(queue);

		// ������Ϣ
		for (int i = 0; i < 10; i++) {
			producer.send(session.createTextMessage("��ã�activeMQ:" + i));
		}
		// �ύ����
		session.commit();

	}
}
