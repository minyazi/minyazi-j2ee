package com.minyazi.j2ee.service.webservice;

import javax.jws.WebService;

@WebService
public interface HelloWorldWebService {
    String sayHelloWorld(String text) throws WebServiceException;
}
