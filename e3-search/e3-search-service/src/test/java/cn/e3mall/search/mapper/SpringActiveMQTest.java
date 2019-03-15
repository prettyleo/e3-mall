package cn.e3mall.search.mapper;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.IOException;

/**
 * FileName: SpringActiveMQTest
 * DESCRIPTION:
 *
 * @author: SLY
 * @create: 2019/2/3
 */
public class SpringActiveMQTest {

    /**
     * @Description: AciveMQ和Spring整合, 发送消息
     * @param:
     * @Return: void
     * @Author: SLY
     * @Date:   2019/2/3 18:30
     */
    @Test
    public void springActiveMQSend() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        Destination destination = applicationContext.getBean(ActiveMQQueue.class);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("send spring-activemq message");
            }
        });
    }

    @Test
    public void springActiveMQReceiveMessage() throws IOException {
        // 初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");

        // 等待消息
        System.in.read();
    }

}
