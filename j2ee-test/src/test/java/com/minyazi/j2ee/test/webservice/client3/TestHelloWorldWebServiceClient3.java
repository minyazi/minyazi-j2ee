package com.minyazi.j2ee.test.webservice.client3;

import org.junit.Test;

import com.minyazi.j2ee.test.webservice.client3.HelloWorldWebService;
import com.minyazi.j2ee.test.webservice.client3.HelloWorldWebServiceImplService;

public class TestHelloWorldWebServiceClient3 {
    @Test
    public void test() {
        HelloWorldWebServiceImplService factory = new HelloWorldWebServiceImplService();
        HelloWorldWebService service = factory.getHelloWorldWebServiceImplPort();
        service.sayHelloWorld("Hello World");
    }
}
