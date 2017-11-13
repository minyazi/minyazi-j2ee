package com.minyazi.test.cxfdemo;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import com.minyazi.core.util.LogUtil;

public class TestEndpoint {
    private static final String ADDRESS = "http://localhost:9000/cxfdemo";
    
    @Before
    public void publishService() throws Exception {
        LogUtil.info("发布服务");
        CXFDemoImpl demo = new CXFDemoImpl();
        Endpoint.publish(ADDRESS, demo);
        LogUtil.info("发布成功");
    }
    
    @Test
    public void testSayHelloWorld() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(CXFDemo.class);
        factory.setAddress(ADDRESS);
        CXFDemo client = (CXFDemo) factory.create();
        
        LogUtil.info(client.sayHelloWorld("Hello World"));
    }
}
