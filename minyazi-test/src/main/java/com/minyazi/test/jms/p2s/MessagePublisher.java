package com.minyazi.test.jms.p2s;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息发布者
 * 
 * @author minyazi
 */
public class MessagePublisher {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // ActiveMQ的默认用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // ActiveMQ的默认密码
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL; // ActiveMQ的默认连接地址
    
    private AtomicInteger counter; // 计数器
    private ConnectionFactory connectionFactory; // 连接工厂
    private Connection connection; // 连接
    private Session session; // 会话
    private Topic topic; // 消息主题
    
    public MessagePublisher(String topicName) {
        try {
            // 实例化计数器
            counter = new AtomicInteger(1);
            // 实例化连接工厂
            connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
            // 通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建会话
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            // 创建消息主题
            topic = session.createTopic(topicName);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    public void publishMessage(String message) {
        MessageProducer messageProducer = null; // 消息生产者
        try {
            // 创建消息生产者
            messageProducer = session.createProducer(topic);
            // 创建消息
            TextMessage mesg = session.createTextMessage(message);
            // 发送消息
            messageProducer.send(mesg);
            // 提交事务
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (messageProducer != null) {
                    messageProducer.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void close() {
        try {
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
    
    private class MessagePublisherThread implements Runnable {
        private MessagePublisher messagePublisher;
        
        public MessagePublisherThread(MessagePublisher messagePublisher) {
            this.messagePublisher = messagePublisher;
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    String message = Thread.currentThread().getName() + " -> 发布消息：Hello World，发布消息计数：" + counter.getAndIncrement();
                    messagePublisher.publishMessage(message);
                    System.out.println(message);
                    
                    int sleepTime = new Random().nextInt(10);
                    System.out.println(Thread.currentThread().getName() + " -> 休眠时间：" + sleepTime + "s");
                    Thread.sleep(sleepTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        MessagePublisher messagePublisher = new MessagePublisher("MESG_TOPIC");
        new Thread(messagePublisher.new MessagePublisherThread(messagePublisher), "消息发布者1").start();
        new Thread(messagePublisher.new MessagePublisherThread(messagePublisher), "消息发布者2").start();
        new Thread(messagePublisher.new MessagePublisherThread(messagePublisher), "消息发布者3").start();
        new Thread(messagePublisher.new MessagePublisherThread(messagePublisher), "消息发布者4").start();
        new Thread(messagePublisher.new MessagePublisherThread(messagePublisher), "消息发布者5").start();
    }
}
