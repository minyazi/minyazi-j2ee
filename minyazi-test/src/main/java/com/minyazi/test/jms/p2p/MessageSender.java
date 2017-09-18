package com.minyazi.test.jms.p2p;

import java.util.concurrent.atomic.AtomicInteger;

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
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // ActiveMQ的默认用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // ActiveMQ的默认密码
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL; // ActiveMQ的默认连接地址
    
    private AtomicInteger counter = new AtomicInteger(0); // 计数器
    private ConnectionFactory connectionFactory; // 连接工厂
    private Connection connection; // 连接
    private Session session; // 会话
    private ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<MessageProducer>();
    
    public MessageSender() {
        try {
            // 实例化连接工厂
            connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
            // 通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建会话
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    public void sendMessage(String queueName) {
        Queue queue = null; // 消息队列
        MessageProducer messageProducer = null; // 消息生产者
        try {
            // 创建消息队列
            queue = session.createQueue(queueName);
            if (threadLocal.get() != null) {
                messageProducer = threadLocal.get();
            } else {
                // 创建消息生产者
                messageProducer = session.createProducer(queue);
                threadLocal.set(messageProducer);
            }
            
            while (true) {
                Thread.sleep(1000);
                // 创建消息
                String message = Thread.currentThread().getName() + " -> 发送消息：Hello World，计数：" + counter.getAndIncrement();
                System.out.println(message);
                TextMessage mesg = session.createTextMessage(message);
                // 发送消息
                messageProducer.send(mesg);
                // 提交事务
                session.commit();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void close() {
        try {
            if (threadLocal.get() != null) {
                threadLocal.get().close();
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
    
    private class MessageSenderThread implements Runnable {
        private MessageSender messageSender;
        
        public MessageSenderThread(MessageSender messageSender) {
            this.messageSender = messageSender;
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    messageSender.sendMessage("MESG_QUEUE");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        MessageSender messageSender = new MessageSender();
        new Thread(messageSender.new MessageSenderThread(messageSender)).start();
        new Thread(messageSender.new MessageSenderThread(messageSender)).start();
        new Thread(messageSender.new MessageSenderThread(messageSender)).start();
        new Thread(messageSender.new MessageSenderThread(messageSender)).start();
        new Thread(messageSender.new MessageSenderThread(messageSender)).start();
    }
}
