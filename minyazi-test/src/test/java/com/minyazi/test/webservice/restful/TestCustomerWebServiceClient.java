package com.minyazi.test.webservice.restful;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

import com.minyazi.core.util.LogUtil;

public class TestCustomerWebServiceClient {
    @Test
    public void test() {
        go("http://localhost:9000/ws/jaxrs/customer/1/info");
        go("http://localhost:9000/ws/jaxrs/customer/search?name=zhangsan");
    }
    
    private void go(String url) {
        try {
            HttpClient client = new HttpClient();
            GetMethod method = new GetMethod(url);
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                LogUtil.error("Method failed: " + method.getStatusLine());
            }
            byte[] responseBody = method.getResponseBody();
            LogUtil.info(new String(responseBody));
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
