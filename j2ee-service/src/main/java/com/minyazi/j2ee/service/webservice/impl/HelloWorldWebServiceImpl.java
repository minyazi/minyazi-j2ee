package com.minyazi.j2ee.service.webservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minyazi.j2ee.service.CommonService;
import com.minyazi.j2ee.service.ServiceException;
import com.minyazi.j2ee.service.webservice.HelloWorldWebService;
import com.minyazi.j2ee.service.webservice.WebServiceException;

@Component("helloWorldWebService")
public class HelloWorldWebServiceImpl implements HelloWorldWebService {
    private CommonService commonService;
    
    public CommonService getCommonService() {
        return commonService;
    }
    
    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }
    
    @Override
    public String sayHelloWorld(String text) throws WebServiceException {
        try {
            commonService.testTask();
        } catch (ServiceException e) {
            throw new WebServiceException(e.getMessage(), e);
        }
        return text;
    }
}
