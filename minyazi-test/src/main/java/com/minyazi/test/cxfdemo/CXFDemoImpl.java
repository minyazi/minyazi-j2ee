package com.minyazi.test.cxfdemo;

import javax.jws.WebService;

@WebService()
public class CXFDemoImpl implements CXFDemo {
    @Override
    public String sayHelloWorld(String helloWorld) {
        return helloWorld;
    }
}
