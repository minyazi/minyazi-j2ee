package com.minyazi.test.webservice;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.minyazi.core.util.LogUtil;

public class TestHelloWorldWebServiceClient {
    @Test
    public void test() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setServiceClass(HelloWorldWebService.class);
        factory.setAddress("http://localhost:9000/ws/HelloWorldWS");
        HelloWorldWebService client = (HelloWorldWebService) factory.create();
        LogUtil.info(client.sayHelloWorld("Hello World"));
    }
}
