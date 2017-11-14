package com.minyazi.test.webservice;

import javax.jws.WebService;

@WebService
public interface HelloWorldWebService {
    String sayHelloWorld(String text);
}
