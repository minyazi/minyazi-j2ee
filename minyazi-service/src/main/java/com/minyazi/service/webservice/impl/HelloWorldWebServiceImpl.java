package com.minyazi.service.webservice.impl;

import javax.jws.WebService;

import com.minyazi.service.webservice.HelloWorldWebService;

@WebService()
public class HelloWorldWebServiceImpl implements HelloWorldWebService {
    @Override
    public String sayHelloWorld(String helloWorld) {
        return helloWorld;
    }
}
