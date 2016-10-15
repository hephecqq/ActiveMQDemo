package dagong.jms.service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 消费者2
 * 
 * SessionAwareMessageListener：是由spring提供，它可以在回调方法中传入session，以此回送信息到生产者
 * 
 * @author Administrator
 *
 */
@Component("consumerMessageListener2")
public class ConsumerMessageListener2 implements SessionAwareMessageListener<TextMessage> {

	private Destination destination;

	@Override
	public void onMessage(TextMessage textMessage, Session session) throws JMSException {
		System.out.println("接收到的消息是一个文本消息:" + textMessage.getText());
		// 通过session创建,producer对象,在会送消息
		// 从message中取出信息会送到目的地,以便创建生产者
		MessageProducer producer = session.createProducer(textMessage.getJMSReplyTo());
		// 创建一条消息
		Message message = session.createTextMessage("生产者发过来的信息已经处理完毕，game over...");
		// 调用发送
		producer.send(message);
	}

	@Resource(name = "sendQueueDestination")
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
}
