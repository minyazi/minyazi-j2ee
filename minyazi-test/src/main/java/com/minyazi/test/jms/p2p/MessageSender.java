package com.minyazi.test.jms.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息发送者
 * 
 * @author minyazi
 */
public class MessageSender {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址
    
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null; // 连接工厂
        Connection connection = null; // 连接
        Session session = null; // 会话
        Queue queue = null; // 消息队列
        MessageProducer messageProducer = null; // 消息生产者
        try {
            // 实例化连接工厂
            connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
            // 通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建会话
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            // 创建消息队列
            queue = session.createQueue("MESG_QUEUE");
            // 创建消息生产者
            messageProducer = session.createProducer(queue);
            // 创建消息
            TextMessage textmessage = session.createTextMessage("Hello World");
            System.out.println("发送消息：Hello World");
            // 发送消息
            messageProducer.send(textmessage);
            // 提交事务
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (messageProducer != null) {
                    messageProducer.close();
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
        }
    }
}
