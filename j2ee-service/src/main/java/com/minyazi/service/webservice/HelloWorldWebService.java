package com.minyazi.service.webservice;

import javax.jws.WebService;

@WebService
public interface HelloWorldWebService {
    String sayHelloWorld(String text);
}
