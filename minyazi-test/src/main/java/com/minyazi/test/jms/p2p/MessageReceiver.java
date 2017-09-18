package com.minyazi.test.jms.p2p;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息接收者
 * 
 * @author minyazi
 */
public class MessageReceiver {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // ActiveMQ的默认用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // ActiveMQ的默认密码
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL; // ActiveMQ的默认连接地址
    
    private AtomicInteger counter = new AtomicInteger(0); // 计数器
    private ConnectionFactory connectionFactory; // 连接工厂
    private Connection connection; // 连接
    private Session session; // 会话
    private ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<MessageConsumer>();
    
    public MessageReceiver() {
        try {
            // 实例化连接工厂
            connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
            // 通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建会话
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    public void receiveMessage(String queueName) {
        Queue queue = null; // 消息队列
        MessageConsumer messageConsumer = null; // 消息消费者
        try {
            // 创建消息队列
            queue = session.createQueue("MESG_QUEUE");
            if (threadLocal.get() != null) {
                messageConsumer = threadLocal.get();
            } else {
                // 创建消息消费者
                messageConsumer = session.createConsumer(queue);
                threadLocal.set(messageConsumer);
            }
            
            while (true) {
                Thread.sleep(1000);
                // 接收消息
                TextMessage mesg = (TextMessage) messageConsumer.receive();
                if (mesg != null) {
                    String message = Thread.currentThread().getName() + " -> 接收消息：" + mesg.getText() + "，计数：" + counter.getAndIncrement();
                    System.out.println(message);
                } else {
                    break;
                }
            }
            
            /*
            // 注册消息监听器
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message mesg) {
                    try {
                        String message = Thread.currentThread().getName() + " -> 接收消息：" + ((TextMessage) mesg).getText() + "，计数：" + counter.getAndIncrement();
                        System.out.println(message);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            */
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
    
    private class MessageReceiverThread implements Runnable {
        private MessageReceiver messageReceiver;
        
        public MessageReceiverThread(MessageReceiver messageReceiver) {
            this.messageReceiver = messageReceiver;
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    messageReceiver.receiveMessage("MESG_QUEUE");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        MessageReceiver messageReceiver = new MessageReceiver();
        new Thread(messageReceiver.new MessageReceiverThread(messageReceiver)).start();
        new Thread(messageReceiver.new MessageReceiverThread(messageReceiver)).start();
        new Thread(messageReceiver.new MessageReceiverThread(messageReceiver)).start();
        new Thread(messageReceiver.new MessageReceiverThread(messageReceiver)).start();
        new Thread(messageReceiver.new MessageReceiverThread(messageReceiver)).start();
    }
}
