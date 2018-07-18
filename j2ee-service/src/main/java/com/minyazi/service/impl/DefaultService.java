package com.minyazi.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;

import com.minyazi.service.CommonService;
import com.minyazi.service.PagingServiceAdapter;

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
