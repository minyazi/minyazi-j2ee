package com.minyazi.j2ee.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;

import com.minyazi.j2ee.service.CommonService;
import com.minyazi.j2ee.service.PagingServiceAdapter;

/**
 * 默认Service
 * 
 * @author minyazi
 */
public class DefaultService<T> extends PagingServiceAdapter<T> {
    private CommonService commonService;
    
    public DefaultService() {}
    
    public CommonService getCommonService() {
        return commonService;
    }
    
    @Required
    @Resource
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }
}
