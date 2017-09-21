package com.minyazi.test.jms.p2s;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
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
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // ActiveMQ的默认用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // ActiveMQ的默认密码
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL; // ActiveMQ的默认连接地址
    
    private AtomicInteger counter; // 计数器
    private ConnectionFactory connectionFactory; // 连接工厂
    private Connection connection; // 连接
    private Session session; // 会话
    private Topic topic; // 消息主题
    
    public MessageSubscriber(String topicName) {
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
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建消息主题
            topic = session.createTopic(topicName);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    public void subscribeMessage() {
        MessageConsumer messageConsumer = null; // 消息消费者
        try {
            // 创建消息消费者
            messageConsumer = session.createConsumer(topic);
            
            while (true) {
                // 订阅消息
                TextMessage mesg = (TextMessage) messageConsumer.receive(10000);
                if (mesg != null) {
                    String message = Thread.currentThread().getName() + " -> 订阅消息：[" + mesg.getText() + "]，订阅消息计数：" + counter.getAndIncrement();
                    System.out.println(message);
                } else {
                    System.out.println(Thread.currentThread().getName());
                    break;
                }
            }
            
            /*
            // 注册消息监听器
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message mesg) {
                    try {
                        String message = Thread.currentThread().getName() + " -> 订阅消息：[" + ((TextMessage) mesg).getText() + "]，订阅消息计数：" + counter.getAndIncrement();
                        System.out.println(message);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            */
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (messageConsumer != null) {
                    messageConsumer.close();
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
    
    private class MessageSubscriberThread implements Runnable {
        private MessageSubscriber messageSubscriber;
        
        public MessageSubscriberThread(MessageSubscriber messageSubscriber) {
            this.messageSubscriber = messageSubscriber;
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    messageSubscriber.subscribeMessage();
                    
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
        MessageSubscriber messageSubscriber = new MessageSubscriber("MESG_TOPIC");
        new Thread(messageSubscriber.new MessageSubscriberThread(messageSubscriber), "消息订阅者1").start();
        new Thread(messageSubscriber.new MessageSubscriberThread(messageSubscriber), "消息订阅者2").start();
        new Thread(messageSubscriber.new MessageSubscriberThread(messageSubscriber), "消息订阅者3").start();
        new Thread(messageSubscriber.new MessageSubscriberThread(messageSubscriber), "消息订阅者4").start();
        new Thread(messageSubscriber.new MessageSubscriberThread(messageSubscriber), "消息订阅者5").start();
    }
}
