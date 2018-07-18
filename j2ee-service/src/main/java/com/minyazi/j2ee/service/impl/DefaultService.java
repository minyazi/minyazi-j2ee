package com.minyazi.j2ee.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.minyazi.j2ee.service.CommonService;
import com.minyazi.j2ee.service.PagingServiceAdapter;

/**
 * 默认Service
 * 
 * @author minyazi
 */
public class DefaultService<T> extends PagingServiceAdapter<T> implements ApplicationContextAware {
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
    
    @Required
    @Resource
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }
}
