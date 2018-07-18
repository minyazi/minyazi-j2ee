package com.minyazi.j2ee.test.webservice.client2;

import javax.xml.ws.Endpoint;

import org.junit.Test;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.test.webservice.DataTypeWebServiceImpl;

public class TestDataTypeWebServiceServer {
    @Test
    public void test() {
        LogUtil.info("发布服务");
        Endpoint.publish("http://localhost:9000/ws/DataTypeWS", new DataTypeWebServiceImpl());
        LogUtil.info("发布成功");
        
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        LogUtil.info("停止服务");
    }
}
