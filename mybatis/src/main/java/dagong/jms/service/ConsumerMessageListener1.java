package dagong.jms.service;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消费者1
 * 
 * @author Administrator
 *
 */
/**
 * 监听器的两种实现方案： 1.一种是采用元素的JMS的MessageListener
 * 2.另一种是采用Spring方案的SessionAwareMessageListener
 * 
 * @author Administrator
 *
 */
// 这里的MessageListener接口是JMS的接口
public class ConsumerMessageListener1 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			System.out.println("接收到的消息是一个文本消息:" + textMessage);
			// 这种方式无法回复,所以采用第二种
		}
	}

}
