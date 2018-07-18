package com.minyazi.j2ee.test.webservice.restful;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.junit.Test;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.test.webservice.restful.CustomerWebServiceImpl;

public class TestCustomerWebServiceServer {
    @Test
    public void test() {
        LogUtil.info("发布服务");
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.getInInterceptors().add(new LoggingInInterceptor());
        factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
        factoryBean.setResourceClasses(CustomerWebServiceImpl.class);
        factoryBean.setAddress("http://localhost:9000/ws/jaxrs");
        factoryBean.create();
        LogUtil.info("发布成功");
        
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        LogUtil.info("停止服务");
    }
}
