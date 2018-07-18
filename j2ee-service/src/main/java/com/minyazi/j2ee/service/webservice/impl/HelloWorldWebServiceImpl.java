package com.minyazi.j2ee.service.webservice.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.service.CommonService;
import com.minyazi.j2ee.service.ServiceException;
import com.minyazi.j2ee.service.webservice.HelloWorldWebService;

@Component("helloWorldWebService")
public class HelloWorldWebServiceImpl implements HelloWorldWebService {
    private CommonService commonService;
    
    public CommonService getCommonService() {
        return commonService;
    }
    
    @Required
    @Resource
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }
    
    @Override
    public String sayHelloWorld(String text) {
        try {
            commonService.testTask();
        } catch (ServiceException e) {
            LogUtil.exception(e);
        }
        return text;
    }
}
