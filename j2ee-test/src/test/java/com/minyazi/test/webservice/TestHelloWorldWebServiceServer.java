package com.minyazi.test.webservice;

import javax.xml.ws.Endpoint;

import org.junit.Test;

import com.minyazi.core.util.LogUtil;

public class TestHelloWorldWebServiceServer {
    @Test
    public void test() {
        LogUtil.info("发布服务");
        String address = "http://localhost:9000/ws/HelloWorldWS";
        Endpoint.publish(address, new HelloWorldWebServiceImpl());
        /*
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setAddress(address);
        factory.setServiceClass(HelloWorldWebService.class);
        factory.setServiceBean(new HelloWorldWebServiceImpl());
        factory.create();
        */
        LogUtil.info("发布成功");
        
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        LogUtil.info("停止服务");
    }
}
