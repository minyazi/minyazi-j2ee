package com.minyazi.service.webservice.impl;

import com.minyazi.service.webservice.HelloWorldWebService;

public class HelloWorldWebServiceImpl implements HelloWorldWebService {
    @Override
    public String sayHelloWorld(String text) {
        return text;
    }
}
