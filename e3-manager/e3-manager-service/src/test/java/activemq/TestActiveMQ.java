package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * FileName: TestActiveMQ
 * DESCRIPTION:
 *
 * @author: SLY
 * @create: 2019/2/3
 */
public class TestActiveMQ {

    /**
     * @Description: 点到点形式发送消息
     * @param:
     * @Return: void
     * @Author: SLY
     * @Date:   2019/2/3 12:06
     */
    @Test
    public void sendMessage() throws Exception {
        // 1.创建连接工厂, 端口号61616是broker的端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616/");
        // 2.使用连接工厂创建连接, 开启连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        /**
         * 创建session
         * 第一个参数: true表示开启事务, 一般不开启。设置为开启false，第二个参数无意义。
         * 第二个参数：应答模式： 一般选择自动应答，如果选择CLIENT_ACKNOWLEDGE，则需要自己写代码应答；
         */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4.使用session创建Destination, 有Queue和Topic两种
        Queue queue = session.createQueue("test-queue");
        // 5.创建消息提供者
        MessageProducer producer = session.createProducer(queue);
        // 6.利用session创建消息
        TextMessage textMessage = session.createTextMessage("Hello ActiveMQ");
        // 7.发送消息
        producer.send(textMessage);
        // 8.关闭资源
        connection.close();
        session.close();
        producer.close();
    }

    @Test
    public void messageConsumer() throws Exception {
        // 1.创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");

        // 2.使用连接工厂创建连接, 开启连接
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // 3.使用连接对象创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 4.使用session创建Destination对象(Queue)
        Queue queue = session.createQueue("test-queue");

        // 5.使用session创建消费者
        MessageConsumer consumer = session.createConsumer(queue);

        // 6.接收消息, 打印消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                String text = null;
                try {
                    text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();

        // 7.关闭资源
        connection.close();
        session.close();
        consumer.close();
    }


}
