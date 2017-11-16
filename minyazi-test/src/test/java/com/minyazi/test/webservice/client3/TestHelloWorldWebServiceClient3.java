package com.minyazi.test.webservice.client3;

import org.junit.Test;

public class TestHelloWorldWebServiceClient3 {
    @Test
    public void test() {
        HelloWorldWebServiceImplService factory = new HelloWorldWebServiceImplService();
        HelloWorldWebService service = factory.getHelloWorldWebServiceImplPort();
        service.sayHelloWorld("Hello World");
    }
}
