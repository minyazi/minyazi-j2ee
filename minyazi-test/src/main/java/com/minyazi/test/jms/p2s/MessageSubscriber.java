package com.minyazi.test.jms.p2s;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息订阅者
 * 
 * @author minyazi
 */
public class MessageSubscriber {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址
    
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null; // 连接工厂
        Connection connection = null; // 连接
        Session session = null; // 会话
        Topic topic = null; // 消息主题
        MessageConsumer messageConsumer = null; // 消息消费者
        try {
            // 实例化连接工厂
            connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
            // 通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建会话
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建消息主题
            topic = session.createTopic("MESG_TOPIC");
            // 创建消息消费者
            messageConsumer = session.createConsumer(topic);
            
            /*
            while (true) {
                // 订阅消息
                TextMessage textmessage = (TextMessage) messageConsumer.receive(10000);
                if (textmessage != null) {
                    System.out.println("订阅消息：" + textmessage.getText());
                } else {
                    System.out.println("队列中没有消息");
                    break;
                }
            }
            */
            
            // 注册消息监听器
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("订阅消息：" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            /*
            try {
                if (messageConsumer != null) {
                    messageConsumer.close();
                }
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
            */
        }
    }
}