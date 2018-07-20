package com.minyazi.j2ee.service.impl;

import org.springframework.stereotype.Service;

import com.minyazi.j2ee.core.util.DateUtil;
import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.service.CommonService;
import com.minyazi.j2ee.service.ServiceException;

/**
 * 公共Service
 * 
 * @author minyazi
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {
    public CommonServiceImpl() {}
    
    @Override
    public void testTask() throws ServiceException {
        LogUtil.info(DateUtil.getISODateTime());
    }
}
