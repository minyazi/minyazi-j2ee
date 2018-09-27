package com.minyazi.j2ee.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Abstract Service
 * 
 * @author minyazi
 */
public abstract class AbstractService implements Service, ApplicationContextAware {
    protected ApplicationContext applicationContext;
    @Autowired
    protected CommonService commonService;
    
    public AbstractService() {}
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
