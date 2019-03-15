package cn.e3mall.search.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * FileName: ActiveMQMessageListener
 * DESCRIPTION: ActiveMQ接收消息
 *
 * @author: SLY
 * @create: 2019/2/3
 */
public class ActiveMQMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            System.out.println(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
