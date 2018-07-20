package com.minyazi.j2ee.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * 默认Service
 * 
 * @author minyazi
 */
public abstract class DefaultService<T> extends PagingServiceAdapter<T> implements ApplicationContextAware {
    protected ApplicationContext applicationContext;
    protected CommonService commonService;
    
    public DefaultService() {}
    
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    public CommonService getCommonService() {
        return commonService;
    }
    
    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }
}
