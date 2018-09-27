package com.minyazi.j2ee.service.webservice.impl;

import org.springframework.stereotype.Component;

import com.minyazi.j2ee.service.AbstractService;
import com.minyazi.j2ee.service.webservice.HelloWorldWebService;

@Component("helloWorldWebService")
public class HelloWorldWebServiceImpl extends AbstractService implements HelloWorldWebService {
    @Override
    public String sayHelloWorld(String text) {
        commonService.testTask();
        return text;
    }
}
