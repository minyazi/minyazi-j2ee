package com.minyazi.j2ee.test.webservice;

public class HelloWorldWebServiceImpl implements HelloWorldWebService {
    @Override
    public String sayHelloWorld(String text) {
        return text;
    }
}
