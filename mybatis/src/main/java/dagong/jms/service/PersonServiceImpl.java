package dagong.jms.service;

import javax.annotation.Resource;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import dagong.jms.PersonService;

/**
 * 生产者1
 * 
 * @author Administrator
 *
 */
@Service("personServiceImpl")
@Scope("prototype")
public class PersonServiceImpl implements PersonService {

	private Destination destination;
	private JmsTemplate jsmTemplate;
	private Destination replyQueueDestination;

	@Override
	public void sendMessage(String message) {
		System.out.println("生产者发送消息:" + message);
		// 回调
		jsmTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// 创建一个文本消息
				Message msg = session.createTextMessage(message);
				// 指定为非持久化方式
				msg.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
				return msg;
			}

		});
	}

	@Resource(name = "queueDestination")
	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	@Resource(name = "jmsTemplate")
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jsmTemplate = jmsTemplate;
	}

	@Resource(name = "replyQueueDestination")
	public void setReplyQueueDestination(Destination replyQueueDestination) {
		this.replyQueueDestination = replyQueueDestination;
	}
}
